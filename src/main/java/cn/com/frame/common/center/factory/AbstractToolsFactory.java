/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.common.center.factory;

import cn.com.frame.common.tools.check.AbstractCheckTools;
import cn.com.frame.common.tools.converter.AbstractConverter;
import cn.com.frame.common.tools.datadescribe.AbstractDataDescribe;
import cn.com.frame.common.tools.file.AbstractFileOperator;
import cn.com.frame.common.tools.json.AbstractJSONTools;
import cn.com.frame.common.tools.reflect.AbstractReflectClass;
import cn.com.frame.common.tools.xml.AbstractXMLOperation;

/**
 * @author 张奇(Sirius Zhang)
 * 
 *         Date : 2010-11-30
 */
public abstract class AbstractToolsFactory {

    protected static AbstractXMLOperation xmlTools;
    protected static AbstractJSONTools jsonTools;
    protected static AbstractConverter converter;
    protected static AbstractCheckTools checkTools;
    protected static AbstractReflectClass reflectClass;
    protected static AbstractFileOperator fileOperator;
    protected static AbstractDataDescribe dataDescribe;

    public abstract AbstractXMLOperation getXMLTools();

    public abstract AbstractJSONTools getJsonTools();

    public abstract AbstractConverter getConverter();

    public abstract AbstractCheckTools getCheckTools();

    public abstract AbstractReflectClass getReflectTools();

    public abstract AbstractFileOperator getFileOperator();

}
