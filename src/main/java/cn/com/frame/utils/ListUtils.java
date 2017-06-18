package cn.com.frame.utils;

import cn.com.frame.common.center.builder.SystemBuilder;
import cn.com.frame.common.tools.reflect.CommonReflect;
import cn.com.frame.model.SfSUser;
import net.sf.json.JSONArray;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by pengbin on 2017/5/16.
 */
public class ListUtils {

    public  List updateList(List list, HttpSession session, String format) throws ParseException {
        List mapList = new ArrayList();
        List resutList = new ArrayList();
        if (format == null) {
            format = "yyyy-MM-dd";
        }
        DateFormat sdf = new SimpleDateFormat(format);
        if (list != null && list.size() > 0) {
            Map userMap = (Map) session.getAttribute("SYS_USERMAP");
            if (userMap == null) {
                return list;
            }
            for (int i = 0; i < list.size(); i++) {
                Object instance = list.get(i);
                CommonReflect commonReflect = new CommonReflect();
                commonReflect.setTableName(instance.getClass().getName());
                Map fieldMap = commonReflect.findFieldMap();
                Map instanceMap = new HashMap();
                for (Iterator it = fieldMap.entrySet().iterator(); it.hasNext(); ) {
                    Map.Entry instanceEntry = (Map.Entry) it.next();
                    String fieldName = (String) instanceEntry.getKey();
                    Field field = (Field) fieldMap.get(fieldName);
                    if (commonReflect.invokeGetMethod(instance, fieldName) != null) {
                        if (field.getType().getName().equals("java.lang.String")) {
                            instanceMap.put(fieldName, commonReflect.invokeGetMethod(instance, fieldName));
                        } else if (field.getType().getName().equals("java.lang.Integer")) {
                            String fieldValue = String.valueOf(commonReflect.invokeGetMethod(instance, fieldName));
                            instanceMap.put(fieldName, fieldValue);
                        } else if (field.getType().getName().equals("java.lang.Long")) {
                            String fieldValue = String.valueOf(commonReflect.invokeGetMethod(instance, fieldName));
                            instanceMap.put(fieldName, fieldValue);
                        } else if (field.getType().getName().equals("java.lang.Double")) {
                            String fieldValue = String.valueOf(commonReflect.invokeGetMethod(instance, fieldName));
                            instanceMap.put(fieldName, fieldValue);
                        } else if (field.getType().getName().equals("java.util.Date")) {
                            instanceMap.put(fieldName, commonReflect.invokeGetMethod(instance, fieldName));
                        } else {
                            System.out.println(field.getType().getName());
                        }
                    }
                }
                mapList.add(instanceMap);
            }
            for (int i = 0; i < mapList.size(); i++) {
                Map instanceMap = (Map) mapList.get(i);
                if (instanceMap.get("owner") != null) {
                    String owner = (String) instanceMap.get("owner");
                    if (userMap.get(owner) != null) {
                        SfSUser ownerUser = (SfSUser) userMap.get(owner);
                        instanceMap.put("owner", ownerUser.getA10());
                    }
                }
                if (instanceMap.get("updater") != null) {
                    String updater = (String) instanceMap.get("updater");
                    if (userMap.get(updater) != null) {
                        SfSUser updaterUser = (SfSUser) userMap.get(updater);
                        instanceMap.put("updater", updaterUser.getA10());
                    }
                }
                if (instanceMap.get("createday") != null) {
                    Date date = (Date) instanceMap.get("createday");
                    String datestr = sdf.format(date);
                    instanceMap.put("createday", datestr);
                }
                if (instanceMap.get("updateday") != null) {
                    Date date = (Date) instanceMap.get("updateday");
                    String datestr = sdf.format(date);
                    instanceMap.put("updateday", datestr);
                }
                if (instanceMap.get("updatetime") != null) {
                    String datestr = (String) instanceMap.get("updatetime");
                    try {
                        Date date = sdf.parse(datestr);
                        datestr = sdf.format(date);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    instanceMap.put("updatetime", datestr);
                }
                resutList.add(instanceMap);
            }
        }
        return resutList;
    }
}
