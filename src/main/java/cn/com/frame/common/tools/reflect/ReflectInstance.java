/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.common.tools.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 张奇(Sirius Zhang)
 * 
 * Date : 2011-4-28
 */
public class ReflectInstance {

    private String[] fieldsName ;
    private Map<String,Field> fields;
    private Type[] fieldsType;
    private Map<String,Method> fieldsgetMethod;
    private Map<String,Method> fieldssetMethod;
    private Class classInstance;
    
    public ReflectInstance(){
        fieldsgetMethod = new HashMap<String,Method>();
        fieldssetMethod = new HashMap<String,Method>();
        fields = new HashMap<String,Field>();
    }
    public ReflectInstance(int length , Class classInstance){
        this.classInstance = classInstance;
        fieldsName = new String[length];
        fieldsType = new Type[length];
        fieldsgetMethod = new HashMap<String,Method>();
        fieldssetMethod = new HashMap<String,Method>();
        fields = new HashMap<String,Field>();
    }

    public String[] getFieldsName() {
        return fieldsName;
    }

    public void setFieldsName(String[] fieldsName) {
        this.fieldsName = fieldsName;
    }

    public Type[] getFieldsType() {
        return fieldsType;
    }

    public void setFieldsType(Type[] fieldsType) {
        this.fieldsType = fieldsType;
    }

    public Map<String, Method> getFieldsgetMethod() {
        return fieldsgetMethod;
    }
    public void setFieldsgetMethod(Map<String, Method> fieldsgetMethod) {
        this.fieldsgetMethod = fieldsgetMethod;
    }
    public Map<String, Method> getFieldssetMethod() {
        return fieldssetMethod;
    }
    public void setFieldssetMethod(Map<String, Method> fieldssetMethod) {
        this.fieldssetMethod = fieldssetMethod;
    }
    public Class getClassInstance() {
        return classInstance;
    }

    public void setClassInstance(Class classInstance) {
        this.classInstance = classInstance;
    }
    public Map<String, Field> getFields() {
        return fields;
    }
    public void setFields(Map<String, Field> fields) {
        this.fields = fields;
    }

}
