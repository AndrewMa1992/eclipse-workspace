package com.itheima.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
	/*
	 * Encrypt using the algorithm of md5
	 */
	public static String md5(String plainText) {
		byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("md5").digest(
					plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("There are no md5 algorithm.");
		}
		String md5code = new BigInteger(1, secretBytes).toString(16);// hexadecimal number
		//If the generated number is less than 32 bits, you need to fill in the previous 0.
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}

	public static void main(String[] args) {
		System.out.println(md5("123"));
	}

}
