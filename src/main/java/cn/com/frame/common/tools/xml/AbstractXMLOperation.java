/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.common.tools.xml;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author 张奇(Sirius Zhang)
 * 
 * Date : 2010-11-30
 */
@SuppressWarnings( { "all" })
public abstract class AbstractXMLOperation {

    private static XMLTools tools = new XMLTools();
    /**
     * 
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public abstract XMLInstance readConfig () throws ParserConfigurationException,IOException,SAXException ;

    /**
     * 读取指定xml文件
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public abstract XMLInstance readConfig (String xmlfile) throws ParserConfigurationException,IOException,SAXException ;

    /**
     * 将XML实体写入指定XML文件
     * @param xmlInstance   XML实体
     * @param xmlfile       XML文件路径
     * @return
     * @throws Exception
     */
    public abstract XMLInstance writeConfig(XMLInstance xmlInstance , String xmlfile) throws Exception ;
    
    /**
     * 读取资源文件
     * @param filename
     * @return	资源map
     * @throws Exception
     */
    public abstract Map readProperty(String filename) throws Exception ;
    
    /**
     * 写入资源文件
     * @param property
     * @throws Exception
     */
    public abstract void writeProperty(String filename , Map property) throws Exception ;
    
    /**
     * 读取自定义语法适配器
     * @param pattern
     * @return
     */
	public abstract List readString(String pattern);

	/**
	 * 通过标记读取自定义语法适配器
	 * @param pattern
	 * @param target
	 * @return
	 */
	public abstract Map readString(String pattern, String target);

	/**
	 * 删除标记
	 * @param pattern
	 * @param target
	 * @return
	 */
	public abstract String deleteTarget(String pattern, String target);
	
	/**
	 * 删除标记内所有内容
	 * @param pattern
	 * @param target
	 * @return
	 */
	public abstract String deleteAllTarget(String pattern , String target);
	
	/**
	 * 切割嵌套标记
	 * @param params
	 * @return
	 */
//	public abstract Stack doStackTarget(Map params) ;
}
