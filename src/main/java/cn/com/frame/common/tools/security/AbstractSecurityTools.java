/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.common.tools.security;

/**
 * @author 张奇(Sirius Zhang)
 * 
 *         Date : 2011-5-18
 */
@SuppressWarnings("all")
public abstract class AbstractSecurityTools {

	private static Security security = new Security();

	/**
	 * 获取字符串MD5加密信息
	 * 
	 * @param source
	 * @return
	 */
	public abstract String md5Security(String source);

	/**
	 * des加密传输
	 * 
	 * @param source
	 * @return
	 */
	public abstract String desEncrypt(String source);

	/**
	 * des解密传输
	 * 
	 * @param source
	 * @return
	 */
	public abstract String desDecrypt(String source);

}
