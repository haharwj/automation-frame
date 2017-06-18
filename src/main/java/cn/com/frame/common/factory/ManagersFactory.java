/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.common.factory;

import cn.com.frame.common.util.AbstractUtilManager;
import cn.com.frame.common.util.UtilManager;

import java.lang.reflect.Constructor;

/**
 * @author 张奇(Sirius Zhang)
 * 
 * Factory生成类
 * 
 * Date : 2010-11-30
 */
@SuppressWarnings( { "all" })
class ManagersFactory extends AbstractManagersFactory{

	/**
	 * 封装构造子，通过反射来新建类
	 */
	ManagersFactory(){
		try{
			//XMLTools创建子
			Constructor cts = Class.forName("com.system.common.util.UtilManager").getDeclaredConstructor(null);
			cts.setAccessible(true);
			Object xmlManagersInstance = cts.newInstance(null);
			utilManager = (UtilManager)xmlManagersInstance;
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public AbstractUtilManager getUtilManager() {
		// TODO Auto-generated method stub
		return utilManager;
	}


}
