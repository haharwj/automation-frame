/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.common.tools.reflect;

import cn.com.frame.common.util.ParamUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;



/**
 * @author 张奇(Sirius Zhang)
 * 
 *         Date : 2012-1-21
 */
class ReflectClass extends AbstractReflectClass {

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.system.common.tools.reflect.AbstractReflectClass#reflectClass(java
     * .lang.String[])
     */
    @Override
    public void reflectClass(String[] classArray) {
        // TODO Auto-generated method stub
        String[] tableList = classArray;
        try {
            for (int i = 0; i < tableList.length; i++) {
                String tableName = tableList[i];
                Class tableClass = Class.forName(tableName);
                Field[] fields = tableClass.getDeclaredFields();
                String[] fieldsName = new String[fields.length];
                Method[] methodget = new Method[fields.length];
                Method[] methodset = new Method[fields.length];
                Type[] fieldstype = new Type[fields.length];
                ReflectInstance tableInstance = new ReflectInstance();
                for (int j = 0; j < fields.length; j++) {
                    fieldsName[j] = fields[j].getName();
                    fieldstype[j] = fields[j].getType();
                    String getName = "get"
                            + fieldsName[j].substring(0, 1).toUpperCase()
                            + fieldsName[j].substring(1);
                    String setName = "set"
                            + fieldsName[j].substring(0, 1).toUpperCase()
                            + fieldsName[j].substring(1);
                    tableInstance.getFieldsgetMethod().put(
                            fieldsName[j],
                            tableClass.getDeclaredMethod(getName,
                                    new Class[] {}));
                    tableInstance.getFieldssetMethod().put(
                            fieldsName[j],
                            tableClass.getDeclaredMethod(setName,
                                    new Class[] { fields[j].getType() }));
                    tableInstance.getFields().put(fieldsName[j], fields[j]);
                }
                tableInstance.setFieldsName(fieldsName);
                tableInstance.setClassInstance(tableClass);
                tableInstance.setFieldsType(fieldstype);
                ParamUtil.reflectClass.put(tableName, tableInstance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
