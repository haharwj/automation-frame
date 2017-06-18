/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.common.tools.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 张奇(Sirius Zhang)
 * 
 *         Date : 2010-12-1
 */
@SuppressWarnings({ "all" })
class XMLTools extends AbstractXMLOperation {
	/**
	 * 默认变量
	 */
	private String xmlfile = null;

	/**
	 * 封装构造子
	 */
	XMLTools() {
		// String path = this.getClass().getClassLoader().getResource("/")
		// .getPath();
		// xmlfile = path + "excelconfig.xml";

	}

	@Override
	public XMLInstance readConfig(String xmlfile) throws ParserConfigurationException, IOException, SAXException {
		// TODO Auto-generated method stub
		// （1）得到DOM解析器的工厂实例
		DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();
		// 得到javax.xml.parsers.DocumentBuilderFactory;类的实例就是我们要的解析器工厂
		try {
			// （2）从DOM工厂获得DOM解析器
			DocumentBuilder dombuilder = domfac.newDocumentBuilder();
			// 通过javax.xml.parsers.DocumentBuilderFactory实例的静态方法newDocumentBuilder()得到DOM解析器
			// （3）把要解析的XML文档转化为输入流，以便DOM解析器解析它
			InputStream is = new FileInputStream(xmlfile);
			// （4）解析XML文档的输入流，得到一个Document
			Document doc = dombuilder.parse(is);
			// 由XML文档的输入流得到一个org.w3c.dom.Document对象，以后的处理都是对Document对象进行的
			// （5）得到XML文档的根节点
			Element root = doc.getDocumentElement();
			// 在DOM中只有根节点是一个org.w3c.dom.Element对象。
			// （6）得到节点的子节点
			NodeList books = root.getChildNodes();

			if (books != null) {
				XMLInstance result = new XMLInstance();
				for (int i = 0; i < books.getLength(); i++) {
					Node book = books.item(i);
					if (book.getNodeType() == Node.ELEMENT_NODE) {
						readNode(book, result);
					}
				}
				return result;
			}
		}
		catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (SAXException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private XMLInstance readNode(Node book, XMLInstance result) throws ParserConfigurationException, IOException,
			SAXException {

		if (book.getNodeType() == Node.ELEMENT_NODE) {
			// 如果是节点，取得节点属性值
			String nodeName = book.getNodeName();
			String nodeValue = book.getNodeValue();
			if (nodeName == null || nodeName.equals("null")) {
				return result;
			}
			int n = 0;
			Map nodeMap = new HashMap();
			while (book.hasAttributes()) {
				Node node = book.getAttributes().item(n);
				if (node == null || node.getNodeName() == null || node.getNodeName().equals("null"))
					break;
				// System.out.println(node.getNodeName());
				nodeMap.put(node.getNodeName(), node.getNodeValue() == null ? "" : node.getNodeValue());
				n++;
			}
			result.setNodeName(nodeName);
			result.setAttributeMap(nodeMap);
			result.setNodeValue(nodeValue);
			for (Node node = book.getFirstChild(); node != null; node = node.getNextSibling()) {
				// XMLInstance child = new XMLInstance();
				XMLInstance child = readNode(node, new XMLInstance());
				if (child.getNodeName() != null) {
					result.getChildNodes().add(child);
				}
			}
		}
		return result;
	}

	@Override
	public XMLInstance readConfig() throws ParserConfigurationException, IOException, SAXException {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String args[]) {
		try {
			// XMLTools tools = new XMLTools();
			// long beginTime = new Date().getTime();
			// XMLInstance instance = tools
			// .readConfig("D:\\J2EE Workplaces\\workspace\\sign\\WebRoot\\WEB-INF\\src\\com\\system\\services\\config\\module\\permissions\\template.xml");
			// long endTime = new Date().getTime();
			// System.out.println((endTime - beginTime));
			// int i = 0;
			// print(instance, i);
			XMLTools tools = new XMLTools();
			String temp = tools
					.deleteAllTarget(
							"<#S_CATE id=\"2\" field=\"a11\"><#S_IF content=\"a11等于'百件实事网上办'\">true<#S_ELSE>false</S_ELSE#></S_IF#></S_CATE#>",
							"S_ELSE");
			System.out.println(temp);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void print(XMLInstance xml, int level) {
		if (xml == null)
			return;
		StringBuffer sb = new StringBuffer("");
		for (int n = 0; n < level; n++) {
			sb.append("　　");
		}
		System.out.print(sb.toString() + "node:" + xml.getNodeName() + " attribute : ");
		int i = 0;
		Set<String> key = xml.getAttributeMap().keySet();
		for (Iterator it = key.iterator(); it.hasNext();) {
			String s = (String) it.next();
			System.out.print(s + "=");
			System.out.print(xml.getAttributeMap().get(s) + ";");
		}
		System.out.println();
		System.out.println(sb.toString() + "Children:");
		level++;
		for (Iterator it = xml.getChildNodes().iterator(); it.hasNext();) {
			XMLInstance xmlNode = (XMLInstance) it.next();
			print(xmlNode, level);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.system.common.tools.xml.AbstractXMLOperation#writeConfig(com.system .common.tools.xml.XMLInstance,
	 * java.lang.String)
	 */
	@Override
	public XMLInstance writeConfig(XMLInstance xmlInstance, String xmlfile) throws Exception {
		// TODO Auto-generated method stub
		if (xmlInstance == null || xmlfile == null) {
			return null;
		}
		// XML操作工厂
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// 获取操作类
		DocumentBuilder builder = factory.newDocumentBuilder();
		// 创建一个文档
		Document document = builder.newDocument();
		Element root = document.createElement(xmlInstance.getNodeName());
		for (Iterator<String> it = xmlInstance.getAttributeMap().keySet().iterator(); it.hasNext();) {
			String key = it.next();
			root.setAttribute(key, xmlInstance.getAttributeMap().get(key).toString());
		}
		root.setNodeValue(xmlInstance.getNodeValue());
		document.appendChild(root);
		// 遍历子节点
		writeInto(xmlInstance, root, document);
		// 写入XML文件
		TransformerFactory tfactory = TransformerFactory.newInstance();
		Transformer transformer = tfactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(new File(xmlfile));
		transformer.transform(source, result);
		return null;
	}

	/**
	 * 递归写入
	 * 
	 * @param xmlInstance
	 * @param document
	 */
	private void writeInto(XMLInstance xmlInstance, Element root, Document document) {
		for (Iterator<XMLInstance> it = xmlInstance.getChildNodes().iterator(); it.hasNext();) {
			XMLInstance temp = it.next();
			Element node = document.createElement(temp.getNodeName());
			for (Iterator<String> its = temp.getAttributeMap().keySet().iterator(); its.hasNext();) {
				String key = its.next();
				node.setAttribute(key, temp.getAttributeMap().get(key).toString());
			}
			node.setNodeValue(temp.getNodeValue());
			root.appendChild(node);
			writeInto(temp, node, document);
		}
	}

	@Override
	public Map readProperty(String filename) throws Exception {
		// TODO Auto-generated method stub
		Properties props = new Properties();
		InputStream in = new BufferedInputStream(new FileInputStream(filename));
		props.load(in);
		in.close();
		Map resultMap = new HashMap();
		for (Iterator it = props.keySet().iterator(); it.hasNext();) {
			String key = (String) it.next();
			String value = props.getProperty(key);
			resultMap.put(key, value);
		}
		return resultMap;
	}

	@Override
	public void writeProperty(String filename, Map property) throws Exception {
		// TODO Auto-generated method stub
		Properties prop = new Properties();
		File file = new File(filename);
		if (!file.exists())
			file.createNewFile();
		InputStream fis = new FileInputStream(file);
		prop.load(fis);
		fis.close();
		OutputStream fos = new FileOutputStream(filename);
		for (Iterator it = property.keySet().iterator(); it.hasNext();) {
			String key = (String) it.next();
			String value = (String) property.get(key);
			prop.setProperty(key, value);
			prop.store(fos, "update");
		}
		fos.close();
	}

	@Override
	public List readString(String pattern) {
		// TODO Auto-generated method stub
		// 正则表达式获取<>格式内容
		Pattern p = Pattern.compile("<#" + "[^>]*?(>)");
		Matcher m = p.matcher(pattern);
		List resultList = new ArrayList();
		while (m.find()) {
			// 获取第一个
			String thisstring = m.group();
			Map attrMap = new HashMap();
			// 正则表但是获取x="x"内容
			Pattern px = Pattern.compile("(\\w+)\\s*=\\s*\"([^\"]+)\"");
			Matcher mx = px.matcher(thisstring);
			// 找到值
			while (mx.find()) {
				String findtemp = mx.group();
				String[] attrtemp = findtemp.split("=");
				attrMap.put(attrtemp[0].toLowerCase().trim(), attrtemp[1].trim().replaceAll("\"", ""));
			}
			// 将属性放入map
			String attrstring = thisstring.substring(2, thisstring.indexOf(" "));
			attrMap.put("ATTR_NAME", attrstring);
			resultList.add(attrMap);
			break;
		}
		// 返回值
		return resultList;
	}

	@Override
	public Map readString(String pattern, String target) {
		// TODO Auto-generated method stub
		Pattern p = Pattern.compile("<#" + target + "\\s[^>]*?(>)");
		Matcher m = p.matcher(pattern);
		String thisstring = m.find() ? m.group() : "";
		p = Pattern.compile("(\\w+)\\s*=\\s*\"([^\"]+)\"");
		m = p.matcher(thisstring);
		// 找到值
		Map attrMap = new HashMap();
		while (m.find()) {
			String findtemp = m.group();
			String[] attrtemp = findtemp.split("=");
			attrMap.put(attrtemp[0].toLowerCase().trim(), attrtemp[1].trim().replaceAll("\"", ""));
		}
		return attrMap;
	}

	@Override
	public String deleteTarget(String pattern, String target) {
		// TODO Auto-generated method stub
		String result = pattern;
		Pattern p = Pattern.compile("<#" + target + "\\s[^>]*?(>)|(<#" + target + ">)");
		Matcher m = p.matcher(pattern);
		String headstring = m.find() ? m.group() : "";
		p = Pattern.compile("((</" + target + "#>)|(/#>))");
		m = p.matcher(pattern);
		String tailstring = m.find() ? m.group() : "";
		return result.replaceAll(headstring, "").replaceAll(tailstring, "");
	}

	@Override
	public String deleteAllTarget(String pattern, String target) {
		// TODO Auto-generated method stub
		String result = pattern;
		Pattern p = Pattern.compile("<#" + target + "\\s[^>]*?(>)|(<#" + target + ">)");
		Matcher m = p.matcher(pattern);
		String headstring = m.find() ? m.group() : "";
		p = Pattern.compile("((</" + target + "#>)|(/#>))");
		m = p.matcher(pattern);
		String tailstring = m.find() ? m.group() : "";
		if (headstring.equals("") || tailstring.equals("")) {
			return result;
		}
		while (result.indexOf(headstring) != -1 && result.indexOf(tailstring) != -1) {
			String temp = result
					.substring(result.indexOf(headstring), result.indexOf(tailstring) + tailstring.length());
			result = result.replace(temp, "");
		}
		return result;
	}

	// @Override
	// public Stack doStackTarget(Map params) {
	// // TODO Auto-generated method stub
	// Stack sk = null;
	// if (params.get("stack") == null) {
	// sk = new Stack();
	// params.put("stack", sk);
	// }
	// else {
	// sk = (Stack) params.get("stack");
	// }
	// Map result = null;
	// String instance = (String) params.get("instance");
	// String target = (String) params.get("target");
	// if (instance.indexOf(target) != -1) {
	// // 属性入栈
	// sk.push(Sys.getXmlTools().readString(instance, target));
	// // 切割本层标记
	// instance = Sys.getXmlTools().deleteTarget(instance, target);
	// params.put("instance", instance);
	// params.put("stack", sk);
	// doStackTarget(params);
	// }
	// return sk;
	// }
}
