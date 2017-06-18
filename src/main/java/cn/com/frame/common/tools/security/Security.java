/*
 * Copyright (c) 1989-2011 山西泰森科技股份有限公司 版权所有
 */
package cn.com.frame.common.tools.security;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author 张奇(Sirius Zhang)
 * 
 *         Date : 2012-8-3
 */
public class Security extends AbstractSecurityTools {

	private SecretKeySpec key;
	private String iv = "iSc(qd)J^dEd$sa<";

	Security() {
		try {
			key = new SecretKeySpec("Sji@zd(=HUisdO(#".getBytes(), "AES");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.system.common.tools.security.AbstractSecurityTools#md5Security(java
	 * .lang.String)
	 */
	@Override
	public String md5Security(String source) {
		// TODO Auto-generated method stub
		MD5 md5 = new MD5();
		return md5.toMD5(source);
	}

	@Override
	public String desEncrypt(String source) {
		// TODO Auto-generated method stub
		byte[] byteMi = null;
		byte[] byteMing = null;
		String strMi = "EncrygtError";
		BASE64Encoder base64en = new BASE64Encoder();
		try {
			byteMing = source.getBytes("UTF8");
			byteMi = this.getEncCode(byteMing);
			strMi = base64en.encode(byteMi);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			base64en = null;
			byteMing = null;
			byteMi = null;
		}
		return strMi;

	}

	@Override
	public String desDecrypt(String source) {
		// TODO Auto-generated method stub
		BASE64Decoder base64De = new BASE64Decoder();
		byte[] byteMing = null;
		byte[] byteMi = null;
		String strMing = "DecrygtError";

		try {
			byteMi = base64De.decodeBuffer(source);
			byteMing = this.getDesCode(byteMi);
			strMing = new String(byteMing, "UTF8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			base64De = null;
			byteMing = null;
			byteMi = null;
		}
		return strMing;

	}

	/**
	 * 加密以byte[]明文输入,byte[]密文输出
	 * 
	 * @param byteS
	 * @return
	 */
	private byte[] getEncCode(byte[] byteS) {
		byte[] byteFina = null;
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("AES/CBC/ISO10126Padding");
			IvParameterSpec ivps = new IvParameterSpec(iv.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
			cipher.init(Cipher.ENCRYPT_MODE, key, ivps);
			byteFina = cipher.doFinal(byteS);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	/**
	 * 解密以byte[]密文输入,以byte[]明文输出
	 * 
	 * @param byteD
	 * @return
	 */
	private byte[] getDesCode(byte[] byteD) {
		Cipher cipher;
		byte[] byteFina = null;
		try {
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec ivps = new IvParameterSpec(iv.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
			cipher.init(Cipher.DECRYPT_MODE, key, ivps);
			byteFina = cipher.doFinal(byteD);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	public static void main(String args[]) {
		// bJkB7Vs66IDZz/Z1PAI5IvjSdvop3C/9NrN28o5fNmU=
		Security s = new Security();
		System.out.println(s
				.desDecrypt("9eaqfKBygimbLePsqLCc9xsexP8dQTW/LvP/ULPLJvk="));
	}

}
