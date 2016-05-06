package com.findproject.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

public class File2StringUtil {
	/**
	 * 将文件转为字符串流
	 * @param 上传的文件
	 * @return 文件的字符串的流
	 */
	public static String file2String(File file) {
		FileInputStream fis = null;
		ByteArrayOutputStream bous = null;
		byte[] buffer = null, result = null;
		if (file == null)
			return null;
		try {
			fis = new FileInputStream(file);
			bous = new ByteArrayOutputStream();
			buffer = new byte[4 * 1024];
			/* 循环读取图片文件 */
			while (fis.read(buffer) != -1)
				bous.write(buffer);
			result = bous.toByteArray();
			fis.close();
			bous.close();
			return byte2hex(result);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 二进制转字符串
	 * @param b 字节数据
	 * @return 字符串
	 */
	public static String byte2hex(byte[] b) // 浜岃繘鍒惰浆瀛楃涓�
	{
		StringBuffer sb = new StringBuffer();
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0XFF);
			if (stmp.length() == 1) {
				sb.append("0" + stmp);
			} else {
				sb.append(stmp);
			}
		}
		return sb.toString();
	}

	/**
	 * 字符串转二进制
	 * @param str
	 * @return
	 */
	public static byte[] hex2byte(String str) { // 瀛楃涓茶浆浜岃繘鍒�
		if (str == null)
			return null;
		str = str.trim();
		int len = str.length();
		if (len == 0 || len % 2 == 1)
			return null;
		byte[] b = new byte[len / 2];
		try {
			for (int i = 0; i < str.length(); i += 2) {
				b[i / 2] = (byte) Integer
						.decode("0X" + str.substring(i, i + 2)).intValue();
			}
			return b;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 将字节数组转为图片
	 * @param bytes
	 * @return
	 */
	public static void byte2Image(String strImage, String time) {
		// String time=getDate();F:\A\参考项目\我的村\MyCountry(服务端）\WebRoot\image\20150525091300.jpg
		String src = "F://软件开发软体/apache-tomcat-7.0.59/webapps/MyCountry/image";// Police椤圭洰鐨刟larmImage鎵�湪鐨勬枃浠跺す
		File file = new File(src, time + ".jpg"); 
		try {
			byte[] imageByte = hex2byte(strImage);
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(imageByte);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getDate() {
		Calendar calendar = Calendar.getInstance();

		String year = calendar.get(Calendar.YEAR) + "";
		String month = (calendar.get(Calendar.MONTH) + 1) + "";
		String day = calendar.get(Calendar.DAY_OF_MONTH) + "";
		String minute = calendar.get(Calendar.MINUTE) + "";
		String hour = calendar.get(Calendar.HOUR_OF_DAY) + "";
		String second = calendar.get(Calendar.SECOND) + "";
		// String
		// date=year+(month+1)+(day)+((hour<10)?"0":"")+hour+((minute<10)?"0":"")+minute+((second<10)?"0":"")+second;
		String date = year + month + (day) + hour + minute + second;
		return date;

	}
}
