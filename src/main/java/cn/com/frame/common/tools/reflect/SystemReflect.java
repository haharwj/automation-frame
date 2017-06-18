/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.common.tools.reflect;

import cn.com.frame.common.util.ParamUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;


/**
 * 反射工厂，用于解决外联数据问题
 * 
 */
public class SystemReflect {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.system.services.config.module.common.ConfigModel#readConfig()
	 */
//	public Map readConfig() {
//		// TODO Auto-generated method stub
//		String path = configPath;
//		String reflectArray[];
//		String reflectDir[];
//		try {
//			Map configMap = new HashMap();
//
//			XMLInstance xmlInstance = Sys.getXmlTools().readConfig(configPath);
//			if (xmlInstance.getNodeName().equals("REFLECT")) {
//				List childNode = xmlInstance.getChildNodes();
//				reflectArray = new String[childNode.size()];
//				for (int i = 0; i < childNode.size(); i++) {
//					XMLInstance temp = xmlInstance.getChildNodes().get(i);
//					String attr = (String) temp.getAttributeMap().get(
//							"classname");
//					if (attr.substring(attr.length() - 2).equals(".*")) {
//						String[] wholePackage = attr.substring(0,
//								attr.length() - 2).split("[.]");
//						StringBuffer dirString = new StringBuffer(
//								ParamUtil.CLASSES_PATH);
//						for (int j = 0; j < wholePackage.length; j++) {
//							dirString.append(ParamUtil.FILE_SEPRATOR
//									+ wholePackage[j]);
//						}
//						File fileDir = new File(dirString.toString());
//						if (fileDir.isDirectory()) {
//							String[] tempDir = fileDir.list();
//							reflectDir = new String[tempDir.length];
//							for (int j = 0; j < tempDir.length; j++) {
//								reflectDir[j] = attr.substring(0,
//										attr.length() - 2)
//										+ "."
//										+ tempDir[j]
//												.substring(
//														0,
//														tempDir[j]
//																.lastIndexOf(".") < 0 ? tempDir[j]
//																.length()
//																: tempDir[j]
//																		.indexOf("."));
//							}
//							reflect(reflectDir);
//							SystemBuilder.getBuilder().getToolsFactory()
//									.getReflectTools().reflectClass(reflectDir);
//						}
//					} else {
//						reflectArray[i] = attr;
//					}
//				}
//				if (reflectArray.length == 0 || reflectArray[0] == null) {
//					return configMap;
//				}
//				reflect(reflectArray);
//				SystemBuilder.getBuilder().getToolsFactory().getReflectTools()
//						.reflectClass(reflectArray);
//			}
//			System.out.println("读取反射配置文件成功");
//
//			return configMap;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("读取反射配置文件失败-------------------");
//
//		return new HashMap();
//	}

	/**
	 * 相关反射类
	 * 
	 * @param tableList
	 *            类名称数组
	 */
	public void reflect(String[] tableList) {
		Map classMap = new HashMap();
		try {
			for (int i = 0; i < tableList.length; i++) {
				if (classMap.get(tableList[i]) == null) {
					classMap.put(tableList[i], tableList[i]);
				} else {
					continue;
				}
				String tableName = tableList[i];
				System.out.println("++++ 反射位置：" + tableList[i]);
				Class tableClass = Class.forName(tableName);
				Field[] fields = tableClass.getDeclaredFields();
				String[] fieldsName = new String[fields.length];
				Method[] methodget = new Method[fields.length];
				Method[] methodset = new Method[fields.length];
				Type[] fieldstype = new Type[fields.length];
				TableInstance tableInstance = new TableInstance();
				for (int j = 0; j < fields.length; j++) {
					fieldsName[j] = fields[j].getName();
					fieldstype[j] = fields[j].getType();
					String getName = "get"
							+ fieldsName[j].substring(0, 1).toUpperCase()
							+ fieldsName[j].substring(1);
					String setName = "set"
							+ fieldsName[j].substring(0, 1).toUpperCase()
							+ fieldsName[j].substring(1);
					tableInstance.getFieldsgetMethod().put(
							fieldsName[j],
							tableClass.getDeclaredMethod(getName,
									new Class[] {}));
					tableInstance.getFieldssetMethod().put(
							fieldsName[j],
							tableClass.getDeclaredMethod(setName,
									new Class[] { fields[j].getType() }));
					tableInstance.getFields().put(fieldsName[j], fields[j]);
				}
				tableInstance.setFieldsName(fieldsName);
				tableInstance.setClassInstance(tableClass);
				tableInstance.setFieldsType(fieldstype);
				ParamUtil.TABLEINSTANCE.put(tableName,
						tableInstance);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
