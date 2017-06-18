/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.common.tools.json;

import cn.com.frame.common.center.builder.SystemBuilder;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

/**
 * @author 张奇(Sirius Zhang)
 * 
 *         Date : 2011-1-8
 */
@SuppressWarnings("all")
class JSONTools extends AbstractJSONTools {

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.system.common.tools.json.AbstractJSONTools#convertJSONArray(java.
     * util.List)
     */
    @Override
    public String convertListToJson(List source) {
        // TODO Auto-generated method stub
        String result = JSONArray.fromObject(source).toString();
        return result;
    }

    @Override
    public List convertJSONToList(String json, Class object) {
        // TODO Auto-generated method stub
        JSONArray ja = JSONArray.fromObject(json);
        if(object == null){
            return (List)JSONArray.toList(ja);
        }
        return JSONArray.toList(ja, object);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.system.common.tools.json.AbstractJSONTools#convertListToDisplay(java
     * .util.List)
     */
    @Override
    public String convertListToDisplay(List source) {
        // TODO Auto-generated method stub
        StringBuffer strBuffer = new StringBuffer("[");
        if (source.size() <= 0)
            return "[]";
        Class instanceClass = source.get(0).getClass();
        Field[] classFields = instanceClass.getDeclaredFields();
        int j = 0;
        try {
            for (Iterator it = source.iterator(); it.hasNext();) {
                Object obj = it.next();
                strBuffer.append("[");
                for (int i = 0; i < classFields.length; i++) {
                    String fieldName = classFields[i].getName();
                    String getMethodString = "get"
                            + fieldName.substring(0, 1).toUpperCase()
                            + fieldName.substring(1);
                    Method methodObj = instanceClass.getMethod(getMethodString,
                            null);
                    Object instanceValue = methodObj.invoke(obj, null);
                    String getValues = SystemBuilder.getBuilder()
                            .getToolsFactory().getConverter()
                            .formatForceToString(instanceValue);
                    strBuffer.append("'" + getValues + "'");
                    if (i < classFields.length - 1) {
                        strBuffer.append(",");
                    }
                }

                strBuffer.append("]");
                if (j < source.size() - 1) {
                    strBuffer.append(",");
                }
                j++;
            }
        } catch (Exception e) {
            // e.printStackTrace();
        }
        strBuffer.append("]");
        return strBuffer.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.system.common.tools.json.AbstractJSONTools#convertListToJson(java
     * .util.List, char)
     */
    @Override
    public String convertListToJson(List source, String x) {
        // TODO Auto-generated method stub
        StringBuffer sb = new StringBuffer("[");
        int i = 0;
        for (Iterator it = source.iterator(); it.hasNext(); i++) {
            sb.append(x);
            sb.append((String) it.next());
            sb.append(x);
            if (i < source.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /* (non-Javadoc)
     * @see com.system.common.tools.json.AbstractJSONTools#convertObjectToJson(java.lang.Object)
     */
    @Override
    public String convertObjectToJson(Object source) {
        // TODO Auto-generated method stub
        return JSONObject.fromObject(source).toString();
    }
    
    public static void main(String args[]){
        
        String a = "[{a:'x' , b:'y'}]";
        JSONTools tools = new JSONTools();
        List list = tools.convertJSONToList(a, null);
        System.out.println("run");
    }

}
