package com.test;

import java.io.IOException;

public class Tests {
	public static void main(String[] args) {
		String str = "cmd /c start chrome http://blog.csdn.net/powmxypow ";
						//2.chrome
						
						try {
							Runtime.getRuntime().exec(str);
						} catch (IOException e) {
							e.printStackTrace();
						}
	}
}
