package edu.xpu.hcp.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**                                                                                ____________________
      _                _                                                           < 神兽护体，永无bug! >
    | |__  _   _  ___| |__   ___ _ __   __ _ _ __   ___ _ __   __ _                --------------------
   | '_ \| | | |/ __| '_ \ / _ \ '_ \ / _` | '_ \ / _ \ '_ \ / _` |                       \   ^__^
  | | | | |_| | (__| | | |  __/ | | | (_| | |_) |  __/ | | | (_| |                        \  (oo)\_______
 |_| |_|\__,_|\___|_| |_|\___|_| |_|\__, | .__/ \___|_| |_|\__, |                           (__)\       )\/\
                                   |___/|_|                |___/                                ||----w |
                                                                                                ||     ||
 * @author: huchengpeng
 * @date: 2020/11/15 14:58
 * @description: MD5 加密工具类
 */
public class MD5Utils {

	/**
	 * 
	 * @Title: MD5Utils.java
	 * @Package com.imooc.utils
	 * @Description: 对字符串进行md5加密
	 */
	public static String getMD5Str(String strValue) {
		String md5Str = "";
		try{
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5Str = Base64.encodeBase64String(md5.digest(strValue.getBytes()));
		}catch (NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		return md5Str;
	}

	public static void main(String[] args) {
		try {
			String md5 = getMD5Str("huchegnpeng");
			System.out.println(md5);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
