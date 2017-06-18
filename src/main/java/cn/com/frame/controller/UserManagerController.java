package cn.com.frame.controller;

import cn.com.frame.common.center.builder.Sys;
import cn.com.frame.common.center.builder.SystemBuilder;
import cn.com.frame.common.tools.converter.AbstractConverter;
import cn.com.frame.common.tools.converter.Converter;
import cn.com.frame.common.tools.reflect.CommonReflect;
import cn.com.frame.common.tools.security.VerifyCodeUtils;
import cn.com.frame.model.*;
import cn.com.frame.service.*;
import cn.com.frame.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by 王晨 on 2017/4/13.
 */
@Controller
@RequestMapping("userManager")
public class UserManagerController extends BaseController {
    @Autowired
    private UserManagerService userManagerService;
    @Autowired
    private UserRoleManagerService userRoleService;
    @Autowired
    private RolePermManagerService rolePermService;
    @Autowired
    private UserGroupManagerService userGroupService;
    @Autowired
    private RoleManagerService roleManagerService;

    @Autowired
    public void setService(UserManagerService service) {
        super.service = service;
    }
    public UserManagerController() {
        commonInstance = new SfSUser();
        commonMainReflect = new CommonReflect();
        commonMainReflect
                .setTableName("cn.com.frame.model.SfSUser");
        commonReflect = new CommonReflect();
        commonReflect.setTableName("cn.com.frame.model.SfSUser");
    }
   /* @Autowired
    public void setService(UserManagerService service){
        super.service = service;
    }*/

   /* public UserManagerController() {
        commonInstance = new SfSUser();
        commonMainReflect = new CommonReflect();
        commonMainReflect
                .setTableName("cn.com.frame.model.SfSUser");
        commonReflect = new CommonReflect();
        commonReflect.setTableName("cn.com.frame.model.SfSUser");
    }*/

    /*判断用户名是否重复   success为true 则重复*/
    @RequestMapping("/isDuplicateUsername")
    @ResponseBody
    public Map isDuplicateUsername(String username) {
        boolean success = userManagerService.isDuplicate(username);
        System.out.println("userManagerController中的判断用户名是否重复" + success);
        Map map = new HashMap();
        map.put("success", success);
        return map;
    }

    /*获取验证码*/
    @RequestMapping("/getVcode")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //生成随机字符串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        //存入会话session
        HttpSession session = request.getSession();
        //删除以前
        session.removeAttribute("TEMP_USER_CODE");
        session.setAttribute("TEMP_USER_CODE", verifyCode.toLowerCase());
        //生成图片
        int w = 150, h = 50;
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
    }

    /*用户注册  */
    @RequestMapping("/regedit")
    public Map register(String username, String password, String vcode, HttpServletRequest request) {
        Map map = new HashMap();
        boolean success = false;
        HttpSession session = request.getSession();
        String tempusercode = (String) session.getAttribute("TEMP_USER_CODE");
        if ((vcode.toLowerCase().equals(tempusercode)) || (vcode.toUpperCase().equals(tempusercode))) {
            success = userManagerService.register(username, password);
            map.put("success", success);
        } else {
            map.put("success", success);
        }
        return map;
    }

    /**
     * 验证用户的登录情况
     * username        用户名
     * password     	密码
     * encode			密码是否进行md5加密。true加密，false或不填不加密
     * vcode			验证码
     * swap            切换用户的uuid，swap不为空时进行用户切换。swap为"auto"则自动将session中的useruuid与grantoruuid交换
     */
    @RequestMapping("/login")
    @ResponseBody
    public Map login(String username, String password, String encode, String vcode, String swap, HttpServletRequest request) {
        /*
        * success :  true/false*/
        boolean success = false;
        Map map = new HashMap();
        map.put("success", false);
        map.put("errorcode", 1);
        try {

          /*
          验证码的验证
          * */
            HttpSession session = request.getSession();
            String tempusercode = (String) session.getAttribute("TEMP_USER_CODE");
            if (vcode.toUpperCase().equals(tempusercode) || vcode.toLowerCase().equals(tempusercode)) {
                success = true;
            } else {
                map.put("errorcode", 2);
                return map;
            }
            List result = new ArrayList();
            SfSUser user = null;
            //* 通过用户名和密码查询是否正确  是否MD5加密
            if ("true".equals(encode) || "".equals(encode) || encode == null) {
//            password = Sys.getSecurityTools().md5Security(password);
                result = userManagerService.findByCondition("a11 = '" + username + "'and a12 = '" + password + "'", 0, 0, null);
            } else {
                result = userManagerService.findByCondition("a11 = '" + username + "'and a12 = '" + password + "'", 0, 0, null);
            }
            if (result.size() > 0) {
                success = true;
                user = (SfSUser) result.get(0);
                map.put("user", user);
//            request.getSession().setAttribute("SYS_USER_USERNAME", username);
//            request.getSession().setAttribute("SYS_USER_UUID", user.getUuid());
                this.pushSessionByUser(user, request);
            } else {
                map.put("success", false);
                map.put("errorcode", 1);
                return map;
            }

          /*切换用户的uuid，swap不为空时进行用户切换。swap为"auto"则自动将session中的useruuid与grantoruuid交换
          * */
            if (swap != null || "auto".equals(swap)) {
                String uuuid = (String) request.getSession().getAttribute("useruuid");
                String guuid = (String) request.getSession().getAttribute("grantoruuid");
                request.getSession().setAttribute("useruuid", guuid);
                request.getSession().setAttribute("grantoruuid", uuuid);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("success", success);
        map.put("errorcode", 0);
        return map;
    }

    /*根据用户设置session*/
    private Map pushSessionByUser(SfSUser user, HttpServletRequest request) {
        Map displayMap = new HashMap();
    /*SYS_USER               	用户信息Map          	user
    SYS_USER_ID             	用户id					user.id
    SYS_USER_UUID			用户uuid				user.uuid
    SYS_USER_USERNAME			用户名					user.a11
    SYS_USER_REALNAME			用户姓名				user.a10
    SYS_USER_SEX			用户性别				user.a13
    SYS_USER_USERLEVEL			用户级别				user.a14
    SYS_USER_TYPE			用户类型				user.a20*/
        HttpSession session = request.getSession();
        displayMap = new HashMap();
        session.setAttribute("SYS_USER", user);
        displayMap.put("SYS_USER_ID", user.getId());
        session.setAttribute("SYS_USER_ID", user.getId());
        displayMap.put("SYS_USER_UUID", user.getUuid());
        session.setAttribute("SYS_USER_UUID", user.getUuid());
        displayMap.put("SYS_USER_USERNAME", user.getA11());
        session.setAttribute("SYS_USER_USERNAME", user.getA11());
        displayMap.put("SYS_USER_REALNAME", user.getA10());
        session.setAttribute("SYS_USER_REALNAME", user.getA10());
        displayMap.put("SYS_USER_SEX", user.getA13());
        session.setAttribute("SYS_USER_SEX", user.getA13());
        displayMap.put("SYS_USER_USERLEVEL", user.getA14());
        session.setAttribute("SYS_USER_USERLEVEL", user.getA14());
        displayMap.put("SYS_USER_TYPE", user.getA20());
        session.setAttribute("SYS_USER_TYPE", user.getA20());
        // 保存该部门所有用户的uuid,用户可以查看该部门所有文档
        List<String> useruuids = userGroupService.saveAllGroupUser((String) user.getUuid());
        displayMap.put("SYS_SAMEORG_USERUUID", useruuids);
        session.setAttribute("SYS_SAMEORG_USERUUID", useruuids);

        //Grantorid			代理人，工作流必须变量-------------------------------------------------------------------------
        //SYS_ROLE_NAME			用户角色名
//        List<SfSUserRole> userroles = userRoleService.findByCondition("useruuid = '" + (String) user.getUuid() + "'", 0, 0, null);
//        String roleuuid = userroles.get(0).getRoleuuid();
//        List<SfSRole> roles = roleManagerService.findByCondition("roleuuid='" + roleuuid + "'", 0, 0, null);
//        String rolename = roles.get(0).getRolename();
//        displayMap.put("SYS_ROLE_NAME", rolename);
//        session.setAttribute("SYS_ROLE_NAME", rolename);
//        //保存该用户所有的权限
//        //SYS_ACCESS			用户权限，分号分隔
//        List<SfSRolePerm> lists = rolePermService.findByCondition("a01='" + roleuuid + "'", 0, 0, null);
//        String rights = "";
//        if (lists.size() > 0) {
//            rights = lists.get(0).getA10();
//        }
//        displayMap.put("SYS_ACCESS", rights);
//        session.setAttribute("SYS_ACCESS", rights);
////    获取用户组织机构对象  添加groupUUid groupname到session中
//        List<SfSUserGroup> usergroups = userManagerService.findByCondition("useruuid='" + (String) user.getUuid() + "'", 0, 0, null);
//        String groupUUid = usergroups.get(0).getGroupuuid();
//        String groupname = usergroups.get(0).getGroupname();
//        displayMap.put("SYS_ORG_ID", groupUUid);
//        session.setAttribute("SYS_ORG_ID", groupUUid);
//        displayMap.put("SYS_ORG_NAME", groupname);
//        session.setAttribute("SYS_ORG_NAME", groupname);

        List<SfSUser> userList = userManagerService.findByCondition("id>0", 0, 0, null);
        Map userMap = new HashMap();
        for (SfSUser u : userList) {
            if (userMap.get(u.getUuid()) == null) {
                userMap.put(u.getUuid(), u);
            }
        }
        session.setAttribute("SYS_USERMAP", userMap);
        return displayMap;
    }

    /**
     * 退出登录
     */
    @RequestMapping("/logout")
    @ResponseBody
    public Map logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("SYS_USER");
        session.removeAttribute("SYS_USER_ID");
        session.removeAttribute("SYS_USER_UUID");
        session.removeAttribute("SYS_USER_USERNAME");
        session.removeAttribute("SYS_USER_REALNAME");
        session.removeAttribute("SYS_USER_TYPE");
        session.removeAttribute("SYS_USER_SEX");
        session.removeAttribute("SYS_USER_USERLEVEL");
        session.removeAttribute("SYS_ACCESS");
        session.removeAttribute("SYS_ACCESS_JSON");
        session.removeAttribute("SYS_ROLE_NAME");
        session.removeAttribute("grantorid");
        session.removeAttribute("SYS_ORG_ID");
        session.removeAttribute("SYS_ORG_NAME");
        Map result = new HashMap();
        result.put("success", true);
        return result;
    }

    /**
     * 修改用户信息
     */
    @ResponseBody
    @RequestMapping(value = "/saveOrUpdateUser", method = RequestMethod.POST)
    public Map saveOrUpdateUser(HttpServletRequest request) {
        boolean success = false;
        Map resultMap = new HashMap();
        HttpSession session = request.getSession();
        AbstractConverter converter = new Converter();
        Timestamp time = new Timestamp(new Date().getTime());
        Long id = Long.parseLong(request.getParameter("id"));
        String a01 = request.getParameter("a01");
        String a10 = request.getParameter("a10");
        String a11 = request.getParameter("a11");
        String a13 = request.getParameter("a13");
        String a14 = request.getParameter("a14");
        String a15 = request.getParameter("a15");

        SfSUser user = null;
        List<SfSUser> users = userManagerService.findByCondition("id = '" + id + "'", 0, 0, null);
        if (users.size() > 0) {
            user = users.get(0);
            user.setA01(a01);
            user.setA10(a10);
            user.setA13(a13);
            user.setA14(a14);
            user.setA15(a15);
            user.setUpdater((String) session.getAttribute("SYS_USER_UUID"));
            user.setUpdateday(time);
            user.setUpdatetime(converter.getNowDateL());

            if (user.getA11().equals(a11)) {
                success = userManagerService.saveOrUpdate(user);
            } else {
                if (userManagerService.isDuplicate(a11)) {
                    success = false;
                } else {
                    user.setA11(a11);
                    success = userManagerService.saveOrUpdate(user);
                }
            }
        } else {
            user = new SfSUser();
            user.setA01(a01);
            user.setA10(a10);
            user.setA11(a11);
            user.setA13(a13);
            user.setA14(a14);
            user.setA15(a15);
            user.setOwner((String) session.getAttribute("SYS_USER_UUID"));
            user.setCreateday(time);
            user.setUuid(UUID.randomUUID() + "");
            success = userManagerService.saveOrUpdate(user);
        }
        resultMap.put("success", success);
        resultMap.put("instance", user);
        return resultMap;
    }


    /**
     * 用户信息列表
     */
    @RequestMapping(value = "/listUser", method = RequestMethod.POST)
    @ResponseBody
    public Map userList(HttpServletRequest request) {
        List<SfSUser> displayList = new ArrayList<SfSUser>();
        boolean success = false;
        String condition = request.getParameter("condition");
        String start = request.getParameter("start");
        String limit = request.getParameter("limit");
        String orderby = request.getParameter("orderby");
        String nopage = request.getParameter("nopage");

        if (!condition.equals("")) {
            condition = Sys.getSecurityTools().desDecrypt(condition);
        }
        if (!start.equals("")) {
            start = Sys.getSecurityTools().desDecrypt(start);
        }
        if (!limit.equals("")) {
            limit = Sys.getSecurityTools().desDecrypt(limit);
        }
        if (!orderby.equals("")) {
            orderby = (String) Sys.getSecurityTools().desDecrypt(orderby);
        }
        if (nopage.equals("") && Sys.getSecurityTools().desDecrypt(nopage).equals("true")) {
            start = "0";
            limit = "0";
        }
        displayList = userManagerService.findByCondition(condition, Integer.parseInt(start), Integer.parseInt(limit), orderby);
        int total = userManagerService.findCountByCondition(condition);
        if (total >= 0) {
            success = true;
        }
        Map resultMap = new HashMap();
        resultMap.put("success", success);
        resultMap.put("list", displayList);
        resultMap.put("count", total);
        return resultMap;

    }

    /**
     * 删除用户信息
     * 传入id或condition  使用base64加密
     * 返回true/false
     */
    @ResponseBody
    @RequestMapping(value = "deleteUser", method = RequestMethod.POST)
    public Map deleteUser(HttpServletRequest request) {
        Map resultMap = new HashMap();
        boolean success = false;
        String id = request.getParameter("id");
        String condition = request.getParameter("condition");
        System.out.println("---id:" + id + "---condition:" + condition);
        if (id.length() > 0 || id != null) {
            id = Sys.getSecurityTools().desDecrypt(id);
            if (id.contains(",")) {
                String[] ids = id.split(",");
                for (int i = 0; i < ids.length; i++) {
                    success = userManagerService.delete("id='" + ids[i] + "'");
                }
            } else {
                success = userManagerService.delete("id='" + id + "'");
            }
        } else if (condition.length() > 0 || condition != null) {
            condition = Sys.getSecurityTools().desDecrypt(condition);
            success = userManagerService.delete(condition);
        } else {
            success = false;
        }
        resultMap.put("success", success);
        return resultMap;
    }


    /**
     * 插入和更新用户角色  更新或插入user_role表中相关的数据
     *
     * @Param useruuid        用户uuid
     * rolename		用户角色名
     * @Return success:  true/false
     */
    @RequestMapping("/configUserRole")
    @ResponseBody
    public Map configUserRole(String useruuid, String rolename) {
        Map map = new HashMap();
        boolean success = userRoleService.configUserRole(useruuid, rolename);
        map.put("success", success);
        return map;
    }


    /**
     * 获取用户的权限
     *
     * @Params： roleuuid        用户uuid
     * rolename		用户角色名
     * @return： success ：true/false
     * displayList
     */
    @ResponseBody
    @RequestMapping("/findRolePermission")
    public Map findRolePermission(String roleuuid, String rolename) {
        Map map = new HashMap();
        boolean success = false;
        List<String> rights = rolePermService.findRolePermission(roleuuid, rolename);
        if (rights.size() > 0)
            success = true;
        map.put("success", success);
        map.put("displayList", rights);
        return map;
    }

    /**
     * 查询角色列表
     * condition : B64:  a01 = ‘_role’
     * orderby:  B64 : 排序方式
     * start:	第几条开始
     * limit:   每页多少条
     * nopage: 1不分页，0分页
     */
    @ResponseBody
    @RequestMapping("/rolelist")
    public Map roleList(String condition, String orderby, int start, int limit, int nopage) {
        Map map = new HashMap();
        List<SfSRole> roleList = new ArrayList<SfSRole>();
        boolean success = false;
        int total = 0;
        if (nopage == 1) {
            roleList = userManagerService.findByCondition(condition, 0, 0, null);
            total = userManagerService.findCountByCondition(condition);
            map.put("total", total);
        } else {
            roleList = userManagerService.findByCondition(condition, start, limit, orderby);
        }
        if (roleList.size() > 0) success = true;
        map.put("success", success);
        map.put("list", roleList);
        return map;
    }

    /**
     * 删除用户角色
     *
     * @return success : true/false
     * @Params useruuid        用户uuid
     */
    @ResponseBody
    @RequestMapping("/deteleUserRole")
    public Map deteleUserRole(String useruuid) {
        Map map = new HashMap();
        boolean success = userRoleService.deleteUserRole(useruuid);
        map.put("success", success);
        return map;
    }

    /**
     * 更新角色权限（写入xml）
     *
     * @return success : true/false
     * @Params roleuuid:		角色uuid
     * key:			权限，分号分隔
     */
    @ResponseBody
    @RequestMapping("/saveOrUpdateRolePermission")
    public Map saveOrUpdateRolePermission(String roleuuid, String key) {
        Map map = new HashMap();
        boolean success = rolePermService.saveOrUpdateRolePermission(roleuuid, key);
        map.put("success", success);
        return map;
    }

    /**
     * 插入或更新用户组织机构
     *
     * @return success : true/false
     * @Params useruuid        用户uuid
     * username 		用户名
     * groupuuid		组织机构uuid
     * groupname		组织机构名
     * groupid			组织机构id
     */
    @ResponseBody
    @RequestMapping("/saveOrUpdateUserGroup")
    public Map saveOrUpdateUserGroup(String useruuid, String username, String groupuuid, String groupname, String groupid) {
        Map map = new HashMap();
        boolean success = userGroupService.saveOrUpdateUserGroup(useruuid, username, groupuuid, groupid, groupname);
        map.put("success", success);
        return map;
    }

    /**
     * 删除用户组织机构
     *
     * @return success : true/false
     * @Params String useruuid
     */
    @ResponseBody
    @RequestMapping("/deleteUserGroup")
    public Map deleteUserGroup(String useruuid) {
        Map map = new HashMap();
        boolean success = userGroupService.deleteUserGroup(useruuid);
        map.put("success", success);
        return map;
    }

    /**
     * 获取到相同组织机构的所有用户的uuid
     *
     * @Params String useruuid
     * @Return List: displayList
     */
    @ResponseBody
    @RequestMapping("/getSameGroupUseruuid")
    public Map getSameGroupUseruuid(String useruuid) {
        Map<String, Object> map = new HashMap();
        List<String> uuids = userGroupService.saveAllGroupUser(useruuid);
        map.put("displayList", uuids);
        return map;
    }
}
