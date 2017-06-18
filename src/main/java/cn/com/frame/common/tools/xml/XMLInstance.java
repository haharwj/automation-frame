/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.common.tools.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 张奇(Sirius Zhang)
 * 
 *         Date : 2011-12-8
 */
public class XMLInstance {

    private String nodeName;
    private String nodeValue ;
    private List<XMLInstance> childNodes;
    private Map<String, Object> attributeMap;
    private String xmlString ;

    public XMLInstance() {
        childNodes = new ArrayList<XMLInstance>();
        attributeMap = new HashMap<String , Object>();
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public List<XMLInstance> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<XMLInstance> childNodes) {
        this.childNodes = childNodes;
    }

    public Map<String, Object> getAttributeMap() {
        return attributeMap;
    }

    public void setAttributeMap(Map<String, Object> attributeMap) {
        this.attributeMap = attributeMap;
    }

    public String getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(String nodeValue) {
        this.nodeValue = nodeValue;
    }

	public String getXmlString() {
		return xmlString;
	}

	public void setXmlString(String xmlString) {
		this.xmlString = xmlString;
	}

}
