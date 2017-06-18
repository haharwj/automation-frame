/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.common.tools.file;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author 张奇(Sirius Zhang)
 * 
 *         Date : 2011-5-18
 */
public abstract class AbstractFileOperator {

	/**
	 * 读取文件
	 * 
	 * @param path
	 *            路径
	 * @return
	 * @throws Exception
	 */
	public abstract List readFile(String path) throws Exception;

	/**
	 * 写入指定文件
	 * 
	 * @param path
	 *            文件名
	 * @param content
	 *            内容
	 * @param type
	 *            写入方式（override:覆盖,append:追加）
	 * @return
	 * @throws Exception
	 */
	public abstract boolean writeFile(String path, List content, String type)
			throws Exception;

	/**
	 * 压缩指定文件到指定路径
	 * 
	 * @param fileName
	 *            指定文件/文件夹
	 * @param FilePath
	 *            指定路径
	 * @throws Exception
	 */
	public abstract void setZipFile(String fileName, String FilePath)
			throws Exception;

	/**
	 * 拷贝文件
	 * 
	 * @param fileName
	 *            文件名
	 * @param FilePath
	 *            文件路径
	 * @throws Exception
	 */
	public abstract void copyfile(String fileName, String FilePath)
			throws Exception;

	/**
	 * 拷贝文件
	 * 
	 * @param file
	 *            文件对象
	 * @param FilePath
	 *            文件路径
	 * @throws Exception
	 */
	public abstract void copyfile(File file, String FilePath) throws Exception;

	/**
	 * 释放压缩文件
	 * 
	 * @param path
	 *            压缩文件路径
	 * @param truePath
	 *            缩放路径
	 * @param tail
	 *            解压文件后的指定后缀名
	 * @return
	 */
	public abstract ZipFileList releaseZipFile(String path, String truePath,
			String tail);

	/**
	 * 删除指定文件夹
	 * 
	 * @param dir
	 */
	public abstract void deleteFolder(String dir);

	/**
	 * 为指定图片添加水印文字
	 *
	 * @param params
	 * map picpath
	 * maplogoText
	 * Map outputfile
	 * @return
	 */
	public abstract String addMarkLogoText(Map params);
	
	/**
	 * 对图片进行等比压缩
	 * @param params
	 * @return
	 */
	public abstract String zoomCompressImage(Map params);
	
	/**
	 * 得到最近修改的文件夹
	 * */
	public abstract File getRecentFloder(String path);
	/**
	 * 得到最近修改的文件
	 * */
	public abstract File getRecentFile(File floder);

	/**
	 * 解压到指定目录
	 *  @param zipPath 
     * @param descDir 
	 * */
	public abstract boolean unZipFiles(String zipPath, String descDir) ;
	
}
