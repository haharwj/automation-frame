package cn.com.frame.service;

import cn.com.frame.mapper.SfSRoleMapper;
import cn.com.frame.mapper.SfSUserRoleMapper;
import cn.com.frame.model.SfSRole;
import cn.com.frame.model.SfSUser;
import cn.com.frame.model.SfSUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 王晨 on 2017/4/11.
 */
@Service
public class UserRoleManagerService extends BaseService {

    @Autowired
    public void setMapper(SfSUserRoleMapper mapper) {
        super.mapper = mapper;
    }

    @Autowired
    private SfSUserRoleMapper userRoleMapper;
    @Autowired
    private SfSRoleMapper roleMapper;
    @Autowired
    private RoleManagerService roleService;
    @Autowired
    private UserManagerService userService;

    public UserRoleManagerService() {
        SfSUserRole sfSUserRole = new SfSUserRole();
        commonInstance = sfSUserRole;
    }

    /*配置用户的角色  存在则更新不存在就插入*/
    public boolean configUserRole(String useruuid, String rolename) {
        List<SfSRole> roles = roleService.findByCondition("rolename='" + rolename + "' ", 0, 0, null);
        List<SfSUser> users = userService.findByCondition("uuid='" + useruuid + "'", 0, 0, null);
        if (users.size() <= 0 || roles.size() <= 0) {
            return false;
        }
        SfSUser user = users.get(0);
        SfSRole role = roles.get(0);

        SfSUserRole userrole = null;
        List<SfSUserRole> userroles = this.findByCondition("roleuuid='" + role.getUuid() + "' and useruuid='" + useruuid + "' ", 0, 0, null);

        if (userroles.size() <= 0) {
            userrole = new SfSUserRole();
        } else {
            userrole = userroles.get(0);
        }
        userrole.setUseruuid(useruuid);
        userrole.setUsername(user.getA10());
        userrole.setRoleid(role.getRoleid());
        userrole.setRoleuuid(role.getUuid());
        return this.saveOrUpdate(userrole);
    }

    /**
     * 删除用户角色
     */
    public boolean deleteUserRole(String useruuid){
        SfSUserRole userRole = null;
        List<SfSUserRole> userroles = this.findByCondition("useruuid='"+useruuid+"'",0,0,null);
        if(userroles.size()<=0){
            return false;
        }
        userRole = userroles.get(0);
        return this.delete("id='"+userRole.getId()+"'");
    }

}
