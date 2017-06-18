/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.common.util;

@SuppressWarnings( { "all" })
public abstract class AbstractUtilManager {

	private static UtilManager utilManager = new UtilManager();
	
	/**
	 * ParamUtil中全局分页数
	 * @param pageCount
	 */
	public abstract void setParamUtilPageCount(int pageCount);

}
