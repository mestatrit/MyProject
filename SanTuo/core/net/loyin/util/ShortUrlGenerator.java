package net.loyin.util;

import net.loyin.util.safe.MD5;

/**
 * 短URL创建
 * 
 * @author 刘声凤 2012-7-26 下午4:21:36
 */
public class ShortUrlGenerator {
	/** 可以自定义生成 MD5 加密字符传前的混合 KEY*/
	private static String key = "fjkadshfqu3hrkjnfdkafweq53";
	/** 要使用生成 URL 的字符*/
	private static String[] chars = new String[] { "a", "b", "c", "d", "e",
			"f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
			"s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4",
			"5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
			"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
			"V", "W", "X", "Y", "Z" };
//public static void main(String[] args){
//	System.out.println(ShortUrlGenerator.shortUrl("bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3bafasdfrewrr2trewytruytruytfggfdsh6784g3"));
//}
	public static String shortUrl(String url) {
		// 对传入网址进行 MD5 加密
		String hex = MD5.getMD5ofStr(key + url);
		// 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
		String sTempSubString = hex.substring(0, 8);//0可以替换成0和8的倍数 8 可以替换为n+1倍数
		// 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用
		// long ，则会越界
		long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
		StringBuffer outChars =new StringBuffer();
		for (int j = 0; j <6; j++) {
			// 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
			long index = 0x0000003D & lHexLong;
			// 把取得的字符相加
			outChars.append(chars[(int) index]);
			// 每次循环按位右移 5 位
			lHexLong = lHexLong >> 5;
		}
		// 把字符串存入对应索引的输出数组
		return outChars.toString();
	}
}
