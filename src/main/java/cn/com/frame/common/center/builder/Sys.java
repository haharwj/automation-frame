// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Sys.java

package cn.com.frame.common.center.builder;

import cn.com.frame.common.tools.check.AbstractCheckTools;
import cn.com.frame.common.tools.converter.AbstractConverter;
import cn.com.frame.common.tools.datadescribe.AbstractDataDescribe;
import cn.com.frame.common.tools.file.AbstractFileOperator;
import cn.com.frame.common.tools.json.AbstractJSONTools;
import cn.com.frame.common.tools.reflect.AbstractReflectClass;
import cn.com.frame.common.tools.security.AbstractSecurityTools;
import cn.com.frame.common.tools.xml.AbstractXMLOperation;

import java.lang.reflect.Constructor;

public class Sys {
    protected static AbstractXMLOperation xmlTools;
    protected static AbstractJSONTools jsonTools;
    protected static AbstractConverter converter;
    protected static AbstractCheckTools checkTools;
    protected static AbstractReflectClass reflectClass;
    protected static AbstractFileOperator fileOperator;
    protected static AbstractDataDescribe dataDescribe;
    protected static AbstractSecurityTools securityTools;

    private Sys() {
    }

    public static AbstractXMLOperation getXmlTools() {
        try {
            if (xmlTools == null) {
                Constructor cts = Class.forName("cn.com.frame.common.tools.xml.XMLTools").getDeclaredConstructor(null);
                cts.setAccessible(true);
                Object xmlToolsInstance = cts.newInstance(null);
                xmlTools = (AbstractXMLOperation) xmlToolsInstance;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xmlTools;
    }

    public static AbstractJSONTools getJsonTools() {
        try {
            if (jsonTools == null) {
                Constructor cts = Class.forName("cn.com.frame.common.tools.json.JSONTools").getDeclaredConstructor(null);
                cts.setAccessible(true);
                Object jsonToolsInstance = cts.newInstance(null);
                jsonTools = (AbstractJSONTools) jsonToolsInstance;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonTools;
    }

    public static AbstractConverter getConverter() {
        try {
            if (converter == null) {
                Constructor cts = Class.forName("cn.com.frame.common.tools.converter.Converter").getDeclaredConstructor(null);
                cts.setAccessible(true);
                Object converterInstance = cts.newInstance(null);
                converter = (AbstractConverter) converterInstance;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return converter;
    }

    public static AbstractCheckTools getCheckTools() {
        try {
            if (checkTools == null) {
                Constructor cts = Class.forName("cn.com.frame.common.tools.check.CheckTools").getDeclaredConstructor(null);
                cts.setAccessible(true);
                Object checkToolsInstance = cts.newInstance(null);
                checkTools = (AbstractCheckTools) checkToolsInstance;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkTools;
    }

    public static AbstractReflectClass getReflectClass() {
        try {
            if (reflectClass == null) {
                Constructor cts = Class.forName("cn.com.frame.common.tools.reflect.ReflectClass").getDeclaredConstructor(null);
                cts.setAccessible(true);
                Object reflectClassInstance = cts.newInstance(null);
                reflectClass = (AbstractReflectClass) reflectClassInstance;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reflectClass;
    }

    public static AbstractFileOperator getFileOperator() {
        try {
            if (fileOperator == null) {
                Constructor cts = Class.forName("cn.com.frame.common.tools.file.FileOperation").getDeclaredConstructor(null);
                cts.setAccessible(true);
                Object fileOperatorClassInstance = cts.newInstance(null);
                fileOperator = (AbstractFileOperator) fileOperatorClassInstance;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileOperator;
    }

    public static AbstractSecurityTools getSecurityTools() {
        try {
            if (securityTools == null) {
                Constructor cts = Class.forName("cn.com.frame.common.tools.security.Security").getDeclaredConstructor(null);
                cts.setAccessible(true);
                Object securityToolsClassInstance = cts.newInstance(null);
                securityTools = (AbstractSecurityTools) securityToolsClassInstance;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return securityTools;
    }

}
