package cn.com.frame.common.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImgProcess {
	// path 路径 ,旧文件名称 ,新文件名称,n 改变倍数
	public boolean changeImage(String path, String oldimg, String newimg, Double maxW) {
		try {
			File file = new File(path + oldimg);
			Image img = ImageIO.read(file);
			// 构造Image对象
			int width = img.getWidth(null); // 得到源图宽
			int height = img.getHeight(null); // 得到源图长
			Double hw = new Double(height) / new Double(width);
			int widthn = 0;
			int heightn = 0;
			if(maxW < width){
				widthn = new Double(maxW).intValue();
				heightn = new Double(widthn * hw).intValue();
			}else{
				widthn = width;
				heightn = height;
			}
			BufferedImage tag = new BufferedImage(widthn, heightn,
					BufferedImage.TYPE_INT_RGB);
			tag.getGraphics()
					.drawImage(img, 0, 0, widthn, heightn, null); // 绘制后的图
			FileOutputStream out = new FileOutputStream(path + newimg);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(tag);
			param.setQuality(1f, true);
			encoder.encode(tag,param); // 近JPEG编码
			out.close();
			return true;
		} catch (IOException e) {
			System.out.println("处理文件出现异常");
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) {
		ImgProcess jc = new ImgProcess();
		jc.changeImage("E://", "1.png", "2.png", 600.0);
	}
}
