/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.common.factory;

import cn.com.frame.common.util.AbstractUtilManager;
import cn.com.frame.common.util.UtilManager;

/**
 * @author 张奇(Sirius Zhang)
 * 
 * Date : 2010-11-30
 */
public abstract class AbstractManagersFactory {

	protected static UtilManager utilManager ;
	
	public abstract AbstractUtilManager getUtilManager();
	
}
