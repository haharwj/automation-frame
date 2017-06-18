// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TableInstance.java

package cn.com.frame.common.tools.reflect;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class TableInstance
{

    public TableInstance()
    {
        fieldsgetMethod = new HashMap();
        fieldssetMethod = new HashMap();
        fields = new HashMap();
    }

    public TableInstance(int length, Class classInstance)
    {
        this.classInstance = classInstance;
        fieldsName = new String[length];
        fieldsType = new Type[length];
        fieldsgetMethod = new HashMap();
        fieldssetMethod = new HashMap();
        fields = new HashMap();
    }

    public String[] getFieldsName()
    {
        return fieldsName;
    }

    public void setFieldsName(String fieldsName[])
    {
        this.fieldsName = fieldsName;
    }

    public Type[] getFieldsType()
    {
        return fieldsType;
    }

    public void setFieldsType(Type fieldsType[])
    {
        this.fieldsType = fieldsType;
    }

    public Map getFieldsgetMethod()
    {
        return fieldsgetMethod;
    }

    public void setFieldsgetMethod(Map fieldsgetMethod)
    {
        this.fieldsgetMethod = fieldsgetMethod;
    }

    public Map getFieldssetMethod()
    {
        return fieldssetMethod;
    }

    public void setFieldssetMethod(Map fieldssetMethod)
    {
        this.fieldssetMethod = fieldssetMethod;
    }

    public Class getClassInstance()
    {
        return classInstance;
    }

    public void setClassInstance(Class classInstance)
    {
        this.classInstance = classInstance;
    }

    public Map getFields()
    {
        return fields;
    }

    public void setFields(Map fields)
    {
        this.fields = fields;
    }

    private String fieldsName[];
    private Map fields;
    private Type fieldsType[];
    private Map fieldsgetMethod;
    private Map fieldssetMethod;
    private Class classInstance;
}
