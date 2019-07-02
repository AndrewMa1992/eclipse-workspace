package com.itheima.crm.utils;

import java.util.UUID;

public class UploadUtils {

	public static String getUuidFileName(String fileName) {
		int idx = fileName.lastIndexOf(".");
		String extions = fileName.substring(idx);
		return UUID.randomUUID().toString().replace("-","") + extions;
	}
	
	public static String getPath(String uuidFileName) {
		int code1 = uuidFileName.hashCode();
		int d1 = code1 & 0xf;
		int code2 = code1 >>> 4;
		int d2 = code2 & 0xf;
		return "//"+d1+"//"+d2;
	}
}
