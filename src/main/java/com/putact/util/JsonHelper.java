package com.putact.util;

import com.google.gson.Gson;

public class JsonHelper {
	private static Gson gson = new Gson();

	public static Object fromJson(String src, Class t) {
		return gson.fromJson(src, t);
	}

	public static String toJson(Object object) {
		return gson.toJson(object);
	}

}
