// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CommonReflect.java

package cn.com.frame.common.tools.reflect;

import cn.com.frame.common.util.ParamUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;

// Referenced classes of package org.connect:
//            ParamUtil

public class CommonReflect
{

    public CommonReflect()
    {
    }

    public void setTableName(Object obj)
    {
        if(obj instanceof String)
            tableMap = (TableInstance) ParamUtil.TABLEINSTANCE.get(obj);
        else
            tableMap = (TableInstance)ParamUtil.TABLEINSTANCE.get(obj.getClass().getName());
    }

    public Object invokeGetMethod(Object obj, String field)
    {
        if(tableMap.getFieldsgetMethod().get(field) != null)
            try
            {
                Method getmethod = (Method)tableMap.getFieldsgetMethod().get(field);
                return getmethod.invoke(obj, null);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        return null;
    }

    public void invokeSetMethod(Object obj, String field, Object params)
    {
        if(tableMap.getFieldssetMethod().get(field) != null)
            try
            {
                Method setmethod = (Method)tableMap.getFieldssetMethod().get(field);
                if(params != null)
                    setmethod.invoke(obj, new Object[] {
                        params
                    });
                else
                    setmethod.invoke(obj, new Object[1]);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
    }

    public TableInstance getTableMap()
    {
        return tableMap;
    }

    public Object createObject(Object params[])
    {
        if(params == null)
            return null;
        try
        {
            Class classarray[] = new Class[params.length];
            for(int i = 0; i < params.length; i++)
                classarray[i] = params[i].getClass();

            Constructor cons = tableMap.getClassInstance().getConstructor(classarray);
            return cons.newInstance(params);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public String[] findFieldArray()
    {
        return tableMap.getFieldsName();
    }

    public Map findFieldMap()
    {
        return tableMap.getFields();
    }

    private TableInstance tableMap;
}
