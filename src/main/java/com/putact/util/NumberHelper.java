package com.putact.util;

import com.google.gson.Gson;

public class NumberHelper {

	public static int getRamdom(int i) {

		java.util.Random random = new java.util.Random();// 定义随机类
		int result = random.nextInt(i);// 返回[0,10)集合中的整数，注意不包括10
		return result + 1;
	}

}
