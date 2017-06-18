/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.common.tools.compiler;

import java.util.HashMap;
import java.util.Map;

/**
 * Java编译器操作
 * @author 张奇(Sirius Zhang)
 * 
 * Date : 2012-8-20
 */
public abstract class AbstractJavaCompiler extends ClassLoader{

    // 已经编译过的同步信息
    protected Map classMap = new HashMap();
    

    /**
     * 获取指定Class
     */
    @Override
    public abstract Class<?> findClass(String str) throws ClassNotFoundException ;
    
}
