/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.common.tools.check;

import java.io.File;

/**
 * @author 张奇(Sirius Zhang)
 * 
 *         Date : 2011-5-18
 */
class CheckTools extends AbstractCheckTools {

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.system.common.tools.check.AbstractCheckTools#isParamNull(java.lang
     * .Object)
     */
    @Override
    public boolean isParamNull(Object obj) {
        // TODO Auto-generated method stub
        if (obj == null)
            return false;
        if (obj instanceof String[]) {
            String[] array = (String[]) obj;
            if (array.length <= 0)
                return false;
            if (array[0] == null || array[0].equals(""))
                return false;
            return true;
        }
        if (obj instanceof File[]) {
            File[] array = (File[]) obj;
            if (array.length <= 0)
                return false;
            return true;
        }
        return false ;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.system.common.tools.check.AbstractCheckTools#isDecimal(java.lang.
     * String)
     */
    @Override
    public boolean isDecimal(String obj) {
        // TODO Auto-generated method stub
        if (obj == null)
            return false;
        try {
            Integer.parseInt(obj);
            return true;
        } catch (Exception e) {

        }
        return false;
    }

}
