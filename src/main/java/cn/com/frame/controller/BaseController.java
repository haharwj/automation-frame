package cn.com.frame.controller;

import cn.com.frame.common.center.builder.Sys;
import cn.com.frame.common.center.builder.SystemBuilder;
import cn.com.frame.common.tools.reflect.CommonReflect;
import cn.com.frame.common.util.ParamUtil;
import cn.com.frame.model.SfBCommonChild;
import cn.com.frame.service.BaseService;
import cn.com.frame.service.CommonChildManagerService;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by 王晨 on 2017/4/12.
 * updated by 彭斌 on 2017/4/20.
 */
public class BaseController {
    protected BaseService service;
    @Autowired
    protected CommonChildManagerService commonChildService;
    public Map displayMap;
    public boolean success;
    public int start;
    public int limit;
    public int count;
    protected Object commonInstance;
    public CommonReflect commonMainReflect;
    public CommonReflect commonReflect;
    public List displayList;

    /**
     * 通用方法saveOrUpdate，用于对数据进行添加或者修改
     * *
     * Params:
     * id               id为-1或为空则为添加，其他情况为数据库其他
     * uuid             uuid为空则自动生成UUID
     * 其它数据库字段     数据库中包含的其它字段
     * securityField    自定义需要解密的字段（逗号分隔）
     * clean            自定义需要置空的字段（逗号分隔）
     * subfield         需要保存的子表字段（逗号分隔各字段名）
     * subsubmit        需要保存的子表数据（下划线分隔多条数据，"|"分隔各个字段数据）
     * type             需要保存的子表类型（a01的值）
     * （注：type，subsubmit，subfield均不为空时有效）
     * createday        创建时间（自动记录）
     * updateday        创建时间（自动记录）
     * ower             记录创建者（自动记录）
     * updater          记录更新者（自动记录）
     * effective        有效值（自动记录）默认值1
     * *
     * Returns:
     * Map对象，其中包括：
     * success        true/false是否执行成功
     * instance       添加或修改的实体
     */
    @RequestMapping(value = "saveOrUpdate")
    @ResponseBody
    public Map saveOrUpdate(HttpServletRequest request) throws Exception {
        //初始化instance（主表实体）其它变量
        commonInstance = commonInstance.getClass().newInstance();
        displayList = new ArrayList();
        success = false;
        start = 0;
        limit = 0;
        //获取params
        Map resultMap = new HashMap();
        Map params = request.getParameterMap();
        Object osecurityField = params.get("securityField");
        String securityField = null;
        Object oclean = params.get("clean");
        Object osubsubmit = params.get("subsubmit");
        Object osubfield = params.get("subfield");
        Object otype = params.get("type");

        Object oid = params.get("id");
        boolean continueRun = true;
        Long ids = Long.valueOf(0L);
        // id不为空，得到原数据，更新原数据
        if (SystemBuilder.getBuilder().getToolsFactory().getCheckTools().isParamNull(oid)) {
            String id = ((String[]) oid)[0];
            try {
                ids = Long.valueOf(Long.parseLong(id));
            } catch (Exception e) {
                continueRun = false;
            }
            if (!continueRun)
                return null;
            Object instance = service.findByCondition("id=" + ids, 0, 0, null).get(0);
            if (instance != null) {
                commonInstance = instance;
            }
        }
        //将params赋值给instance
        for (Iterator it = params.keySet().iterator(); it.hasNext(); ) {
            String fieldName = (String) it.next();
            String[] fieldValue = (String[]) params.get(fieldName);
            if (fieldName.lastIndexOf(".") != -1) {
                fieldName = fieldName.substring(fieldName.indexOf(",") + 1, fieldName.length());
            }
            try {
                if (!fieldName.equals("id")) {
                    String obj = ((String[]) params.get(fieldName))[0];
                    Field field = (Field) commonMainReflect.findFieldMap().get(fieldName);
                    if (field.getType().getName().equals("java.lang.String"))
                        commonMainReflect.invokeSetMethod(commonInstance, fieldName, obj);
                    if (field.getType().getName().equals("java.lang.Integer"))
                        commonMainReflect.invokeSetMethod(commonInstance, fieldName, Integer.valueOf(Integer.parseInt(obj)));
                    if (field.getType().getName().equals("java.lang.Long"))
                        commonMainReflect.invokeSetMethod(commonInstance, fieldName, Long.valueOf(Long.parseLong(obj)));
                    if (field.getType().getName().equals("java.lang.Double"))
                        commonMainReflect.invokeSetMethod(commonInstance, fieldName, Double.valueOf(Double.parseDouble(obj)));
                    if (field.getType().getName().equals("java.util.Date")) {
                        Date date = SystemBuilder.getBuilder().getToolsFactory().getConverter().formatDate(obj, null);
                        Timestamp time = new Timestamp(date.getTime());
                        commonMainReflect.invokeSetMethod(commonInstance, fieldName, time);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //解密securityField字段，securityField是定义需要解密的字段
        if (SystemBuilder.getBuilder().getToolsFactory().getCheckTools().isParamNull(osecurityField)) {
            securityField = ((String[]) osecurityField)[0];
            String fields[] = securityField.split(",");
            for (int i = 0; i < fields.length; i++) {
                String field = fields[i];
                try {
                    String fieldValue = (String) commonReflect.invokeGetMethod(commonInstance, field);
                    commonReflect.invokeSetMethod(commonInstance, field, Sys.getSecurityTools().desDecrypt(fieldValue));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        //对clean字段进行置空操作
        if (SystemBuilder.getBuilder().getToolsFactory().getCheckTools().isParamNull(oclean)) {
            String clean = Sys.getSecurityTools().desDecrypt(((String[]) oclean)[0]);
            String[] cleanArr = clean.split(",");
            for (int i = 0; i < cleanArr.length; i++) {
                try {
                    commonReflect.invokeSetMethod(commonInstance, cleanArr[i], null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (commonInstance != null) {
            //uuid为空，自动生成uuid
            if (commonReflect.invokeGetMethod(commonInstance, "uuid") == null || ((String) commonReflect.invokeGetMethod(commonInstance, "uuid")).equals(""))
                try {
                    commonReflect.invokeSetMethod(commonInstance, "uuid", SystemBuilder.getBuilder().getToolsFactory().getConverter().getUUID());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            //新增或修改instance
            success = service.saveOrUpdate(commonInstance);

            //如果subfield（子表字段）,subsubmit（子表数据）,type（子表类型）不为空，对子表进行操作
            if (SystemBuilder.getBuilder().getToolsFactory().getCheckTools().isParamNull(osubsubmit) && SystemBuilder.getBuilder().getToolsFactory().getCheckTools().isParamNull(osubfield) && SystemBuilder.getBuilder().getToolsFactory().getCheckTools().isParamNull(otype)) {
                String subfield = Sys.getSecurityTools().desDecrypt(((String[]) osubfield)[0]);
                String subsubmit = Sys.getSecurityTools().desDecrypt(((String[]) osubsubmit)[0]);
                String type = Sys.getSecurityTools().desDecrypt(((String[]) otype)[0]);
                String fieldsplit[] = subfield.split(",");
                CommonReflect commonSubReflect = new CommonReflect();
                commonSubReflect.setTableName("cn.com.frame.model.SfBCommonChild");
                String rowssubmit[] = subsubmit.split("_");
                try {
                    //删除子表parentuuid等于当前uuid的数据
                    commonChildService.delete(" parentuuid='" + commonReflect.invokeGetMethod(commonInstance, "uuid") + "'");
                    //创建子表数据
                    for (int i = 0; i < rowssubmit.length; i++) {
                        SfBCommonChild subinstance = new SfBCommonChild();
                        subinstance.setParentuuid((String) commonReflect.invokeGetMethod(commonInstance, "uuid"));
                        subinstance.setUuid(SystemBuilder.getBuilder().getToolsFactory().getConverter().getUUID());
                        subinstance.setA01(type);
                        String datasub[] = rowssubmit[i].split("\\|");
                        for (int j = 0; j < fieldsplit.length; j++) {
                            fieldsplit[j] = fieldsplit[j].trim();
                            Field field = (Field) commonSubReflect.findFieldMap().get(fieldsplit[j]);
                            String temp = datasub[j];
                            if (field.getType().getName().equals("java.lang.String"))
                                commonSubReflect.invokeSetMethod(subinstance, fieldsplit[j], temp);
                            if (field.getType().getName().equals("java.lang.Integer"))
                                commonSubReflect.invokeSetMethod(subinstance, fieldsplit[j], Integer.valueOf(Integer.parseInt(temp)));
                            if (field.getType().getName().equals("java.lang.Long"))
                                commonSubReflect.invokeSetMethod(subinstance, fieldsplit[j], Long.valueOf(Long.parseLong(temp)));
                            if (field.getType().getName().equals("java.lang.Double"))
                                commonSubReflect.invokeSetMethod(subinstance, fieldsplit[j], Double.valueOf(Double.parseDouble(temp)));
                        }
                        commonChildService.saveOrUpdate(subinstance);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        resultMap.put("success", success);
        resultMap.put("instance", commonInstance);
        return resultMap;
    }

    /**
     * 通用方法list
     * 可以按条件进行分页查询
     * *
     * Params:
     * id            查询指定id的数据(优先执行)
     * condition     查询条件
     * nopage        是否分页，true/false
     * sort/orderby  排序条件
     * clean         需要置空的字段
     * start         分页开始位置
     * limit         分页每页条数
     * child         是否查询子表数据 true/false(仅按id查询时生效)
     * sortchild     子表排序条件(仅按id查询时生效)
     * *
     * Returns:
     * Map类型，其中包括：
     * success       true/false是否执行成功
     * instance      按id查询后的实体
     * list          按条件查询后的结果list
     * count         查询数据总数
     * children      子表查询结果list(仅按id查询时生效)
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public Map list(HttpServletRequest request) throws Exception {
        displayList = new ArrayList();
        success = false;
        start = 0;
        limit = 0;
        count = 0;
        //获取params
        Map params = request.getParameterMap();
        displayMap = new HashMap();
        Object oid = params.get("id");
        Object ocondition = params.get("condition");
        Object onopage = params.get("nopage");
        Object osort = params.get("sort");
        Object oorderby = params.get("orderby");
        Object ochild = params.get("child");
        Object osortchild = params.get("sortchild");
        Object oclean = params.get("clean");
        Object ostart = params.get("start");
        Object olimit = params.get("limit");
        if (SystemBuilder.getBuilder().getToolsFactory().getCheckTools().isParamNull(ostart)) {
            start = Integer.parseInt(Sys.getSecurityTools().desDecrypt(((String[]) ostart)[0]));
        }
        if (SystemBuilder.getBuilder().getToolsFactory().getCheckTools().isParamNull(olimit)) {
            limit = Integer.parseInt(Sys.getSecurityTools().desDecrypt(((String[]) olimit)[0]));
        }
        //是否分页
        if (SystemBuilder.getBuilder().getToolsFactory().getCheckTools().isParamNull(onopage) && Sys.getSecurityTools().desDecrypt(((String[]) onopage)[0]).equals("true")) {
            start = 0;
            limit = 0;
        }
        //id不为空，按id查询
        if (SystemBuilder.getBuilder().getToolsFactory().getCheckTools().isParamNull(oid)) {
            String id = Sys.getSecurityTools().desDecrypt(((String[]) oid)[0]);
            displayList = service.findByCondition(" id=" + id, 0, 1, null);
            if (displayList.size() > 0) {
                commonInstance = displayList.get(0);
            }
            count = 1;
            displayMap.put("instance", commonInstance);
            String orderbychild = "";
            if (SystemBuilder.getBuilder().getToolsFactory().getCheckTools().isParamNull(osortchild))
                orderbychild = Sys.getSecurityTools().desDecrypt(((String[]) osortchild)[0]);
            //child不为空，查询子表parentuuid=id的数据
            if (SystemBuilder.getBuilder().getToolsFactory().getCheckTools().isParamNull(ochild)) {
                CommonReflect commonReflect = new CommonReflect();
                commonReflect.setTableName(commonInstance);
                if (Sys.getSecurityTools().desDecrypt(((String[]) ochild)[0]).equals("true")) {
                    List childrenList = (List) commonChildService.findByCondition((new StringBuilder(" parentuuid = '")).append(commonReflect.invokeGetMethod(commonInstance, "uuid")).append("' ").toString(), 0, 0, orderbychild);
                    displayMap.put("children", childrenList);
                }
            }
            success = true;
            //condition不为空，按条件查询
        } else if (SystemBuilder.getBuilder().getToolsFactory().getCheckTools().isParamNull(ocondition)) {
            String condition = Sys.getSecurityTools().desDecrypt(((String[]) ocondition)[0]);
            if (ParamUtil.DB_TYPE.equals("mysql"))
                condition = condition.replaceAll("len\\(", "length\\(");
            String sort = null;
            if (SystemBuilder.getBuilder().getToolsFactory().getCheckTools().isParamNull(osort))
                sort = Sys.getSecurityTools().desDecrypt(((String[]) osort)[0]);
            if (SystemBuilder.getBuilder().getToolsFactory().getCheckTools().isParamNull(oorderby))
                sort = Sys.getSecurityTools().desDecrypt(((String[]) oorderby)[0]);
            displayList = service.findByCondition(condition, start, limit, sort);
            //对clean中的字段置空(不操作数据库)
            if (Sys.getCheckTools().isParamNull(oclean)) {
                String clean = Sys.getSecurityTools().desDecrypt(((String[]) oclean)[0]);
                String splitclean[] = clean.split(",");
                for (int x = 0; x < displayList.size(); x++) {
                    Object temp = displayList.get(x);
                    for (int i = 0; i < splitclean.length; i++) {
                        String field = splitclean[i].trim();
                        try {
                            Method method = (Method) commonMainReflect.getTableMap().getFieldssetMethod().get(field);
                            method.invoke(temp, new Object[]{
                                    ""
                            });
                        } catch (Exception exception) {
                        }
                    }
                }
            }
            displayMap.put("list", displayList);
            count = service.findCountByCondition(condition);
            success = true;
        }
        displayMap.put("count", count);
        displayMap.put("success", true);
        return displayMap;
    }

    /**
     * 通用方法delete
     * 可以按条件进行删除
     * *
     * Params:
     * id            删除指定id的数据(优先执行)
     * condition     按条件删除
     * *
     * Returns:
     * Map类型，其中包括：
     * success       true/false是否执行成功
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public Map delete(HttpServletRequest request) throws Exception {
        displayMap = new HashMap();
        Map params = request.getParameterMap();
        Object oid = params.get("id");
        Object ocondition = params.get("condition");
        String id = "";
        String condition = "";
        if (SystemBuilder.getBuilder().getToolsFactory().getCheckTools().isParamNull(oid)) {
            id = Sys.getSecurityTools().desDecrypt(((String[]) oid)[0]);
        }
        if (SystemBuilder.getBuilder().getToolsFactory().getCheckTools().isParamNull(ocondition)) {
            condition = Sys.getSecurityTools().desDecrypt(((String[]) ocondition)[0]);
        }
        String[] ids = id.split(",");
        if (SystemBuilder.getBuilder().getToolsFactory().getCheckTools().isParamNull(oid)) {
            if (ids.length > 0) {
                for (int i = 0; i < ids.length; i++) {
                    success = service.delete(" id=" + ids[i]);
                }
            }
        } else if (SystemBuilder.getBuilder().getToolsFactory().getCheckTools().isParamNull(ocondition)) {
            success = service.delete(condition);
        } else {
            success = false;
        }
        displayMap.put("success", success);
        return displayMap;
    }
}