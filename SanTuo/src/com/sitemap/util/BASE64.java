package com.sitemap.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/** * BASE64加密解密 */
public class BASE64 {
	/** * BASE64解密 * @param key * @return * @throws Exception */
	public static byte[] decryptBASE64(String key) throws Exception {
		return (new BASE64Decoder()).decodeBuffer(key);
	}
	
	public static String getKey(String key)throws Exception{
	return new String(BASE64.decryptBASE64(key),"UTF-8");	
	}
	
	/** * BASE64加密 * @param key * @return * @throws Exception */
	public static String encryptBASE64(byte[] key) throws Exception {
		return (new BASE64Encoder()).encodeBuffer(key);
	}
	public static void main(String[] args) throws Exception {
		System.out.println(new String(BASE64.decryptBASE64("eGlhb3lpbmc0MjQ="),"UTF-8"));
		System.out.println(BASE64.encryptBASE64("11".getBytes()).toString().trim());;
	}

}