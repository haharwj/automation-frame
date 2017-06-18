/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.common.builder;

import cn.com.frame.common.center.factory.AbstractManagersFactory;
import cn.com.frame.common.center.factory.AbstractToolsFactory;


/**
 * @author 张奇(Sirius Zhang)
 * 
 * Factory生成类
 * 
 * Date : 2010-11-30
 */
public abstract class AbstractBuilder implements SystemCreatable{
    
	protected static AbstractBuilder builder = new SystemBuilder();
	protected static AbstractToolsFactory toolsFactory;
	protected static AbstractManagersFactory managersFactory;

}
