/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.common.tools.file;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author 张奇(Sirius Zhang)
 * 
 *         Date : 2011-8-15
 */
class FileOperation extends AbstractFileOperator {

	public List readFile(String path) throws Exception {
		File file = new File(path);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = null;
		List result = new ArrayList();
		while ((line = br.readLine()) != null) {
			result.add(line);
		}
		br.close();
		return result;
	}

	public boolean writeFile(String path, List content, String type) throws Exception {
		if (type == null) {
			type = "override";
		}
		if (type.equals("override")) {
			File file = new File(path);
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file, false));
			for (Iterator it = content.iterator(); it.hasNext();) {
				String temp = (String) it.next();
				bos.write((temp + "\n").getBytes());
			}
			bos.close();
			return true;
		}
		else if (type.equals("append")) {
			File file = new File(path);
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file, true));
			for (Iterator it = content.iterator(); it.hasNext();) {
				String temp = (String) it.next();
				bos.write((temp + "\n").getBytes());
			}
			bos.close();
			return true;
		}
		return false;
	}

	public void setZipFile(String fileName, String FilePath) throws Exception {
		try {
			ZipOutputStream zo = new ZipOutputStream(new FileOutputStream(FilePath));
			File file = new File(fileName);
			FileInputStream fileStream = new FileInputStream(file);
			BufferedOutputStream bfo = new BufferedOutputStream(zo);
			BufferedInputStream bfi = new BufferedInputStream(fileStream);
			// 没有文件夹则创建
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdir();
			}
			// 不存在则创建文件
			if (!file.exists()) {
				file.createNewFile();
			}
			if (file.exists()) {
				String file2Name = file.getName();
				zo.putNextEntry(new ZipEntry(file2Name));
				int i;
				byte[] b = new byte[1024 * 10];
				// 进行写入
				while ((i = bfi.read(b)) != -1) {
					bfo.write(b, 0, i);
				}
				bfo.flush();
				bfo.close();
				bfi.close();
				zo.close();
				fileStream.close();
			}
			zo = null;
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void copyfile(String fileName, String FilePath) throws Exception {
		FileOutputStream zo = null;
		FileInputStream fileStream = null;
		BufferedOutputStream bfo = null;
		BufferedInputStream bfi = null;
		try {
			File outfile = new File(FilePath);
			File file = new File(fileName);
			String path = FilePath;

			if (FilePath.lastIndexOf(".") == -1) {
				if (!outfile.exists()) {
					outfile.mkdirs();
				}
				if (fileName.lastIndexOf(".") != -1) {
					path = FilePath + "/" + fileName.substring(fileName.lastIndexOf("/") + 1);
					outfile = new File(path);
				}
			}
			else {
				if (!outfile.getParentFile().exists()) {
					outfile.getParentFile().mkdirs();
				}
				if (!outfile.exists()) {
					outfile.createNewFile();
				}
			}

			// 没有文件夹则创建
			if (!outfile.getParentFile().exists()) {
				outfile.getParentFile().mkdirs();
			}
			// 不存在则创建文件
			if (!outfile.exists()) {
				outfile.createNewFile();
			}

			if (file.exists()) {
				if (file.isDirectory()) {
					// 递归拷贝文件
					File[] files = file.listFiles();
					for (int i = 0; i < files.length; i++) {
						File fileexsits = new File(fileName + "/" + files[i].getName());
						String copyfile = FilePath + "/" + fileName.substring(fileName.lastIndexOf("/")) + "/"
								+ files[i].getName();
						if (fileexsits.isDirectory()) {
							copyfile = FilePath + "/" + fileName.substring(fileName.lastIndexOf("/")) + "/"
									+ files[i].getName();
						}
						else {
							copyfile = FilePath + "/" + files[i].getName();
						}
						copyfile(fileName + "/" + files[i].getName(), copyfile);
					}
				}
				else {
					fileStream = new FileInputStream(file);
					zo = new FileOutputStream(path);
					bfo = new BufferedOutputStream(zo);
					bfi = new BufferedInputStream(fileStream);

//					System.out.println("开始拷贝");
					byte[] b = new byte[1024 * 10];
					int len;
					while ((len = bfi.read(b)) != -1) {
						bfo.write(b, 0, len);
					}
					bfo.flush();
					bfi.close();
					bfo.close();
					zo.close();
					fileStream.close();
//					System.out.println("结束拷贝");
				}

			}
			else {
				System.out.println("文件不存在");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (bfo != null) {
				bfo.close();
				bfo = null;
			}
			if (bfi != null) {
				bfi.close();
				bfi = null;
			}
			if (fileStream != null) {
				fileStream.close();
				fileStream = null;
			}
			if (zo != null) {
				zo.close();
				zo = null;
			}
		}
	}

	public ZipFileList releaseZipFile(String path, String truePath, String tail) {
		ZipFileList result = new ZipFileList();

		ZipInputStream zip = null;
		File outFile = null;
		ZipFile zipFile = null;
		String pathClass = null;
		File fileInput = null;
		long now = 0;
		if (truePath == null) {
			pathClass = getClass().getClassLoader().getResource("/").getPath();

		}
		now = System.currentTimeMillis();
		try {
			fileInput = new File(path);
			zip = new ZipInputStream(new BufferedInputStream(new FileInputStream(fileInput)));
			zipFile = new ZipFile(path);
			ZipEntry zipEntry = null;
			while ((zipEntry = zip.getNextEntry()) != null) {
				// System.out.println("解压缩:" + zipEntry.getName() + " 文件");
				String prefix = String.valueOf(now + 1);
				String pathCreate = null;

				if (truePath == null) {
					pathCreate = pathClass + File.separator + prefix + "." + tail;
				}
				else {
					pathCreate = truePath + File.separator + prefix + "." + tail;
				}
				if (pathCreate == null)
					break;
				outFile = new File(pathCreate);
				// 没有文件夹则创建
				if (!outFile.getParentFile().exists()) {
					outFile.getParentFile().mkdir();
				}
				// 不存在则创建文件
				if (!outFile.exists()) {
					outFile.createNewFile();
				}

				BufferedOutputStream bfo = new BufferedOutputStream(new FileOutputStream(outFile));
				// BufferedInputStream bfi = new
				// BufferedInputStream(zipFile.getInputStream(zipEntry));

				int temp = 0;
				int buffer = 1024 * 10;
				byte[] b = new byte[buffer];
				while ((temp = zip.read(b, 0, buffer)) != -1) {
					// 输出内容
					bfo.write(b, 0, temp);

				}
				// 关闭输出流
				bfo.close();
				result.addNoCheck(pathCreate, prefix + "." + tail);
			}
			// 关闭输入流
			zip.close();

			// zip.finish();

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (zip != null) {
				try {
					zip.close();

				}
				catch (Exception e) {
				}
			}
		}
		return result;
	}

	public void deleteFolder(String dir) {
		File delfolder = new File(dir);
		File oldFile[] = delfolder.listFiles();
		try {
			for (int i = 0; i < oldFile.length; i++) {
				if (oldFile[i].isDirectory()) {
					deleteFolder(dir + oldFile[i].getName() + "\\");
				}
				oldFile[i].delete();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.system.common.tools.file.AbstractFileOperator#copyfile(java.io.File, java.lang.String)
	 */
	@Override
	public void copyfile(File file, String FilePath) throws Exception {
		// TODO Auto-generated method stub
		// 重试3次，不要出现问题。
		for (int i = 0; i < 3; i++) {
			FileOutputStream zo = null;
			FileInputStream fileStream = null;
			BufferedOutputStream bfo = null;
			BufferedInputStream bfi = null;
			try {
				File outfile = new File(FilePath);
				if (outfile.isDirectory()) {
					if (!outfile.exists()) {
						outfile.mkdirs();
					}
				}
				else {
					if (!outfile.getParentFile().exists()) {
						outfile.getParentFile().mkdirs();
					}
					if (!outfile.exists()) {
						outfile.createNewFile();
					}
				}
				zo = new FileOutputStream(FilePath);
				fileStream = new FileInputStream(file);
				bfo = new BufferedOutputStream(zo);
				bfi = new BufferedInputStream(fileStream);
				// 没有文件夹则创建
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdir();
				}
				// 不存在则创建文件
				if (!file.exists()) {
					file.createNewFile();
				}
				if (file.exists()) {
					System.out.println("开始拷贝");
					byte[] b = new byte[1024 * 10];
					int len;
					while ((len = bfi.read(b)) != -1) {
						bfo.write(b, 0, len);
					}
					System.out.println("拷贝结束");
					bfo.flush();
					bfi.close();
					bfo.close();
					zo.close();
					fileStream.close();
				}
				else {
					System.out.println(file.getName() + "文件不存在");
				}
			}
			catch (Exception e) {
				// e.printStackTrace();
				e.printStackTrace();
			}
			finally {
				if (zo != null) {
					zo.close();
					zo = null;
				}
				if (fileStream != null) {
					fileStream.close();
					fileStream = null;
				}
				if (bfo != null) {
					bfo.close();
					bfo = null;
				}
				if (bfi != null) {
					bfi.close();
					bfi = null;
				}
			}
			// 读取文档大小，文档大小为0代表复制失败，重新复制。
			try {
				File fileCheck = new File(FilePath);
				if (!fileCheck.exists()) {
					System.out.println("复制失败。");
					Thread.sleep(2000);
					continue;
				}
				if (fileCheck.length() == 0L) {
					System.out.println("复制失败。重试次数操作：" + (i + 1) + "次");
					Thread.sleep(2000);
					continue;
				}
				break;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String addMarkLogoText(Map params) {
		// TODO Auto-generated method stub
		InputStream is = null;
		OutputStream os = null;
		try {
			String picpath = (String) params.get("picpath");
			Double degree = params.get("degree") == null ? null : (Double) params.get("degree");
			int fontsize = params.get("fontsize") == null ? 25 : (Integer) params.get("fontsize");
			float alpha = params.get("alpha") == null ? 0.5f : (Float) params.get("alpha");
			String logtext = (String) params.get("logtext");
			String outputpath = (String) params.get("outputpath");

			Color color = Color.GRAY;
			Font font = new Font("宋体", Font.BOLD + Font.ITALIC, fontsize);
			// 1、源图片
			Image srcImg = ImageIO.read(new File(picpath));
			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null),
					BufferedImage.TYPE_INT_RGB);

			// 2、得到画笔对象
			Graphics2D g = buffImg.createGraphics();
			// 3、设置对线段的锯齿状边缘处理
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.drawImage(
					srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0,
					null);
			// 4、设置水印旋转
			if (null != degree) {
				g.rotate(Math.toRadians(degree), (double) buffImg.getWidth() / 2, (double) buffImg.getHeight() / 2);
			}
			// 5、设置水印文字颜色
			g.setColor(color);
			// 6、设置水印文字Font
			g.setFont(font);
			// 7、设置水印文字透明度
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			// 8、第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y)
			g.drawString(logtext, srcImg.getWidth(null) - (logtext.length() * fontsize + 20), srcImg.getHeight(null)
					- (fontsize + 20));
			// 9、释放资源
			g.dispose();
			// 10、生成图片
			os = new FileOutputStream(outputpath);
			ImageIO.write(buffImg, "JPG", os);

			System.out.println("图片完成添加水印文字");
			return outputpath;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (null != is)
					is.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (null != os)
					os.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String zoomCompressImage(Map params) {
		// TODO Auto-generated method stub
		String picpath = (String) params.get("picpath");
		String outputpath = (String) params.get("outputpath");
		FileOutputStream fileoutput = null;
		try {
			Image img = ImageIO.read(new File(picpath));
			int maxSize = params.get("maxsize") == null ? 400 : (Integer) params.get("maxsize");
			int srcWidth = img.getWidth(null);
			int srcHeight = img.getHeight(null);
			if (srcWidth == -1) {
				System.out.println("图片格式错误。");
				return null;
			}
			// 全部小于
			if (srcWidth <= maxSize && srcHeight <= maxSize) {
				return null;
			}
			int dstWidth = img.getWidth(null);
			int dstHeight = img.getHeight(null);

			// 目标缩略图的最大宽度/高度，宽度与高度将按比例缩写
			float scale = 0f;
			// 计算缩放比
			if (srcWidth > maxSize) {
				dstWidth = maxSize;
				scale = (float) srcWidth / (float) maxSize;
				dstHeight = Math.round((float) srcHeight / scale);
			}
			srcHeight = dstHeight;
			if (srcHeight > maxSize) {
				dstHeight = maxSize;
				scale = (float) srcHeight / (float) maxSize;
				dstWidth = Math.round((float) srcWidth / scale);
			}
			// 生成缩略图
			BufferedImage tagImage = new BufferedImage(dstWidth, dstHeight, BufferedImage.TYPE_INT_RGB);
			tagImage.getGraphics().drawImage(img, 0, 0, dstWidth, dstHeight, null);
			fileoutput = new FileOutputStream(outputpath);
			ImageIO.write(tagImage, "JPG", fileoutput);
			tagImage = null;
			img = null;
			return outputpath;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (fileoutput != null) {
				try {
					fileoutput.close();
				}
				catch (IOException e) {
					// TODO Auto-generated catch block
				}
				fileoutput = null;
			}
		}
		return null;
	}

	@Override
	public File getRecentFloder(String path) {
		File recentFile = null;
		File[] files = new File(path).listFiles();
		if (files != null && files.length >= 1) {
			Arrays.sort(files, new Comparator<File>() {
				@Override
				public int compare(File o1, File o2) {
					return (int) (o2.lastModified() - o1.lastModified());
				}
			});
			recentFile = files[0];
		}
		return recentFile;
	}

	@Override
	public File getRecentFile(File floder) {
		File recentFile = null;
		File[] files = floder.listFiles();
		if (files != null && files.length >= 1) {
			Arrays.sort(files, new Comparator<File>() {
				@Override
				public int compare(File o1, File o2) {
					return (int) (o2.lastModified() - o1.lastModified());
				}
			});
			recentFile = files[0];
		}
		return recentFile;
	}

	@Override
	/** 
	 * 解压到指定目录 
	 * @param zipPath 
	 * @param descDir 
	 * @author rwj 
	 */
	public boolean unZipFiles(String zipPath, String descDir) {
		OutputStream out = null;
		InputStream in = null;
		try {
			File pathFile = new File(descDir);
			if (!pathFile.exists()) {
				pathFile.mkdirs();
			}
			ZipFile zip = new ZipFile(new File(zipPath));
			for (Enumeration entries = zip.entries(); entries.hasMoreElements();) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				String zipEntryName = entry.getName();
				System.out.println("解压文件 ：" + zipEntryName);
				in = zip.getInputStream(entry);
				String outPath = (descDir + zipEntryName).replaceAll("\\*", "/");
				;
				// 判断路径是否存在,不存在则创建文件路径
				File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
				if (!file.exists()) {
					file.mkdirs();
				}
				// 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
				if (new File(outPath).isDirectory()) {
					continue;
				}
				// 输出文件路径信息
				System.out.println("文件输出路径 ： " + outPath);

				out = new FileOutputStream(outPath);
				byte[] buf1 = new byte[1024];
				int len;
				while ((len = in.read(buf1)) > 0) {
					out.write(buf1, 0, len);
				}
				in.close();
				out.close();
			}
			System.out.println("******************解压完毕********************");
			return true;

		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if (in!=null){
				try {
					in.close();
				}
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (out!=null){
				try {
					out.close();
				}
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}
}
