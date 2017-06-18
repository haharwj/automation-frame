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
class DataDescribe extends AbstractDataDescribe {
    @Override
    public SfSUser replaceUserByKey(String key, String value) {
        return null;
    }

    @Override
    public SfSRole replaceRoleByKey(String key, String value) {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.system.common.tools.datadescribe.AbstractDataDescribe#replaceUserByKey
     * (java.lang.String, java.lang.String)
     */
//    @Override
//    public SfSUser replaceUserByKey(String key, String value) {
//        // TODO Auto-generated method stub
//        SfSUserDAO dao = (SfSUserDAO) SpringContext.getContext().getBean(
//                "SfSUserDAO");
//        List list = dao.findByProperty(key, value);
//        if(list.size() <= 0){
//            return null ;
//        }
//        return (SfSUser)list.get(0);
//    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.system.common.tools.datadescribe.AbstractDataDescribe#replaceRoleByKey
     * (java.lang.String, java.lang.String)
     */
//    @Override
//    public SfSRole replaceRoleByKey(String key, String value) {
//        // TODO Auto-generated method stub
//        SfSRoleDAO dao = (SfSRoleDAO) SpringContext.getContext().getBean(
//                "SfSRoleDAO");
//        List list = dao.findByProperty(key, value);
//        if(list.size() <= 0){
//            return null ;
//        }
//        return (SfSRole)list.get(0);
//    }

}
