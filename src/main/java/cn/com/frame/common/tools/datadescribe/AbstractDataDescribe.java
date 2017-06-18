/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.common.tools.datadescribe;


import cn.com.frame.model.SfSRole;
import cn.com.frame.model.SfSUser;

/**
 * @author 张奇(Sirius Zhang)
 * 
 *         Date : 2012-3-6
 */
public abstract class AbstractDataDescribe {
    
    /**
     * 根据key获取User
     * @param key   key值
     * @param value 对应的value
     * @return
     */
    public abstract SfSUser replaceUserByKey(String key , String value);
    
    /**
     * 根据key获取Role
     * @param key   key值
     * @param value 对应的value
     * @return
     */
    public abstract SfSRole replaceRoleByKey(String key , String value);
    
}
