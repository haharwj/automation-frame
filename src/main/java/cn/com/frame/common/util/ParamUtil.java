/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.common.util;

import cn.com.frame.common.tools.reflect.ReflectInstance;
import cn.com.frame.common.tools.reflect.TableInstance;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author 张奇(Sirius Zhang)
 *         <p>
 *         Date : 2010-12-10
 * @modify 张奇(Sirius Zhang)
 * <p>
 * Date : 2012-02-03
 */
@SuppressWarnings("all")
public class ParamUtil {

    /**
     * 分页参数
     */
    private static int page_count = 18;

    public static String[] commonAccess = {};

    public static String[] commonType = {};
    public static String[] commonPage = {};

    /**
     * dao封装类型，1为封装为持久化类，2为封装为传输层
     */
    private static int package_model = 1;
    private static int package_dto = 2;

    /**
     * 系统数据字典配置
     */

    private static List commonLimits = new ArrayList();

    public static Map<String, ReflectInstance> reflectClass = new HashMap<String, ReflectInstance>();
    public static String FILE_SEPRATOR;

    /**
     * /**
     * 不可新建
     */
    private ParamUtil() {
        init();
    }

    public static void init() {

    }

    /**
     * 得到页数
     *
     * @return
     */
    public static int getPageCount() {
        return page_count;
    }

    /**
     * 生成器提供方法
     *
     * @param param
     */
    static void setPageCount(int param) {
        page_count = param;
    }

    /**
     * 封装类型持久化类
     *
     * @return
     */
    public static int getPackageModel() {
        return package_model;
    }

    /**
     * 封装类型DTO
     *
     * @return
     */
    public static int getPackageDto() {
        return package_dto;
    }


    public static List getCommonLimits() {
        return commonLimits;
    }



    /**
     * 直接使用的参数
     */
    public static int COMMON_PAGE_SIZE = 30;

    public static String CLASSES_PATH;
    public static String WEBROOT_PATH;
    public static String SYSTEM_NAME = "";
    public static String SYSTEM_ID = "";
    public static String SYSTEM_ORG_COM = "";
    public static String DB_NAME;
    public static String DB_TYPE = "mysql";
    public static String DB_USERNAME = "";
    public static String DB_PASSWORD = "";
    public static String DB_URL = "";
    public static String DB_DRIVER = "";
    public static String DB_TABLENAME = "";

    public static String PROJECTIDS = "";


    // 内置账户
    public static String SYS_USER_NAME = "";
    public static String SYS_USER_PASS = "";
    public static String SYS_USER_ROLE = "";

    public static Map<String, TableInstance> TABLEINSTANCE = new HashMap<String, TableInstance>();

    public static String getPopedomcode() {
        // TODO Auto-generated method stub
        return null;
    }

    public static String getFailedDelete() {
        // TODO Auto-generated method stub
        return null;
    }

}
