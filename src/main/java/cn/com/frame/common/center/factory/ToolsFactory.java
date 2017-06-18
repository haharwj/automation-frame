/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.common.center.factory;

import cn.com.frame.common.tools.check.AbstractCheckTools;
import cn.com.frame.common.tools.converter.AbstractConverter;
import cn.com.frame.common.tools.file.AbstractFileOperator;
import cn.com.frame.common.tools.json.AbstractJSONTools;
import cn.com.frame.common.tools.reflect.AbstractReflectClass;
import cn.com.frame.common.tools.xml.AbstractXMLOperation;

import java.lang.reflect.Constructor;
/**
 * @author 张奇(Sirius Zhang)
 * 
 *         Factory生成类
 * 
 *         Date : 2010-11-30
 */
@SuppressWarnings({ "all" })
class ToolsFactory extends AbstractToolsFactory {

    /**
     * 封装构造子，通过反射来新建类
     */
    ToolsFactory() {
        try {
            // XMLTools创建子
            Constructor cts = Class.forName(
                    "cn.com.frame.common.tools.xml.XMLTools")
                    .getDeclaredConstructor(null);
            cts.setAccessible(true);
            Object xmlToolsInstance = cts.newInstance(null);
            xmlTools = (AbstractXMLOperation) xmlToolsInstance;
            // JSONTools创建子
            cts = Class.forName("cn.com.frame.common.tools.json.JSONTools")
                    .getDeclaredConstructor(null);
            cts.setAccessible(true);
            Object jsonToolsInstance = cts.newInstance(null);
            jsonTools = (AbstractJSONTools) jsonToolsInstance;
            // Converter创建子
            cts = Class.forName("cn.com.frame.common.tools.converter.Converter")
                    .getDeclaredConstructor(null);
            cts.setAccessible(true);
            Object converterInstance = cts.newInstance(null);
            converter = (AbstractConverter) converterInstance;
            // CheckTools创建子
            cts = Class.forName("cn.com.frame.common.tools.check.CheckTools")
                    .getDeclaredConstructor(null);
            cts.setAccessible(true);
            Object checkToolsInstance = cts.newInstance(null);
            checkTools = (AbstractCheckTools) checkToolsInstance;
            // ReflectClass创建子
            cts = Class.forName("cn.com.frame.common.tools.reflect.ReflectClass")
                    .getDeclaredConstructor(null);
            cts.setAccessible(true);
            Object reflectClassInstance = cts.newInstance(null);
            reflectClass = (AbstractReflectClass) reflectClassInstance;
//            // WorkflowTools创建子
//            cts = Class.forName(
//                    "cn.com.frame.common.tools.workflow.WorkflowTools")
//                    .getDeclaredConstructor(null);
//            cts.setAccessible(true);
            // FileOperator创建子
            cts = Class.forName("cn.com.frame.common.tools.file.FileOperation")
                    .getDeclaredConstructor(null);
            cts.setAccessible(true);
            Object fileOperatorClassInstance = cts.newInstance(null);
            fileOperator = (AbstractFileOperator) fileOperatorClassInstance;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public AbstractXMLOperation getXMLTools() {
        // TODO Auto-generated method stub
        return xmlTools;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.com.frame.center.factory.AbstractToolsFactory#getJsonTools()
     */
    @Override
    public AbstractJSONTools getJsonTools() {
        // TODO Auto-generated method stub
        return jsonTools;
    }

    @Override
    public AbstractConverter getConverter() {
        // TODO Auto-generated method stub
        return converter;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.frame.center.factory.AbstractToolsFactory#getCheckTools()
     */
    @Override
    public AbstractCheckTools getCheckTools() {
        // TODO Auto-generated method stub
        return checkTools;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.frame.center.factory.AbstractToolsFactory#getReflectTools()
     */
    @Override
    public AbstractReflectClass getReflectTools() {
        // TODO Auto-generated method stub
        return reflectClass;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.frame.center.factory.AbstractToolsFactory#getFileOperator()
     */
    @Override
    public AbstractFileOperator getFileOperator() {
        // TODO Auto-generated method stub
        return fileOperator;
    }

}
