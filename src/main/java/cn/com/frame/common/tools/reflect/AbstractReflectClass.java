/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.common.tools.reflect;

/**
 * 将类反射到相关结构体中
 * 反射后的类存储在com.system.common.util.ParamUtil.reflectClass中
 * @author 张奇(Sirius Zhang)
 * 
 *         Date : 2012-1-21
 */
public abstract class AbstractReflectClass {

    /**
     * 将指定类得字符串数组反射到特定结构体
     * Param.classReflect变量
     * 
     * @param classArray 类完全路径的字符串数组
     */
    public abstract void reflectClass(String[] classArray);
    
}
