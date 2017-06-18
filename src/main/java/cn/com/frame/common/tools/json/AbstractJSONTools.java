/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.common.tools.json;

import java.util.List;

/**
 * @author 张奇(Sirius Zhang)
 * 
 * Date : 2011-1-8
 */
@SuppressWarnings("all")
public abstract class AbstractJSONTools {

    private static JSONTools tools = new JSONTools();
    
    /**
     * 强制将List对象转为Json String
     * @param source
     * @return
     */
    public abstract String convertListToJson(List source);
    
    /**
     * 强制将JSONString转换为List
     * @param json      JsonString
     * @param object    List对象中的元素对象的class
     * @return
     */
    public abstract List convertJSONToList(String json,Class object);
    
    /**
     * 转换List为该格式javascript数组[['a','b']['a','b']]
     * @param source    
     * @return
     */
    public abstract String convertListToDisplay(List source);
    
    /**
     * 转换List为该格式['a','b','c'];
     * @param source
     * @param x     
     * @return
     */
    public abstract String convertListToJson(List source , String x);
    
    /**
     * 强制将对象转换为JSONString
     * @param source
     * @return
     */
    public abstract String convertObjectToJson(Object source);

}
