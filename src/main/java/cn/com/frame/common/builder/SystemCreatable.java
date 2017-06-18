/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.common.builder;

import cn.com.frame.common.center.factory.AbstractManagersFactory;
import cn.com.frame.common.center.factory.AbstractToolsFactory;


/**
 * @author 张奇(Sirius Zhang)
 * 
 * Date : 2010-11-30
 */
public interface SystemCreatable {

	/**
	 * 得到工具工厂集合
	 * @return
	 */
    public AbstractToolsFactory getToolsFactory();
    
    /**
     * 得到管理工厂集合
     * @return
     */
    public AbstractManagersFactory getManagersFactory();
    
}
