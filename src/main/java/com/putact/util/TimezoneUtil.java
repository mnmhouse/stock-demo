package com.putact.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 */
public class TimezoneUtil {

	private static String dateFormat = "yyyy-MM-dd HH:mm:ss";

	public static String string2TimezoneFormat(String srcFormat, String srcDateTime, String dstFormat)
			throws ParseException {
		SimpleDateFormat srcSdf = new SimpleDateFormat(srcFormat);
		SimpleDateFormat dstSdf = new SimpleDateFormat(dstFormat);
		Date date = srcSdf.parse(srcDateTime.replaceAll("[\\s\\u00A0]+", " ").trim());// 去掉160和32空格
		return dstSdf.format(new Date(date.getTime()));
	}

	public static String string2TimezoneFormat(String srcFormat, String srcDateTime, String srcTimeZoneId,
			String dstFormat, String dstTimeZoneId) throws ParseException {
		return string2TimezoneFromat(srcFormat, srcDateTime, srcTimeZoneId, dstFormat, dstTimeZoneId);
	}

	public static String string2TimezoneDefaultFormat(String srcDateTime, String srcTimeZoneId, String dstTimeZoneId)
			throws ParseException {
		return string2TimezoneFromat(dateFormat, srcDateTime, srcTimeZoneId, dateFormat, dstTimeZoneId);
	}

	public static String string2TimezoneFormat(String srcFormat, String srcDateTime, String srcLocale,
			String srcTimeZoneId, String dstFormat, String dstLocale, String dstTimeZoneId) throws ParseException {
		return string2TimezoneForLocale(srcFormat, srcDateTime, srcLocale, srcTimeZoneId, dstFormat, dstLocale,
				dstTimeZoneId);
	}

	public static String string2TimezoneFromat(String srcFormater, String srcDateTime, String srcTimeZoneId,
			String dstFormater, String dstTimeZoneId) throws ParseException {
		return string2TimezoneForLocale(srcFormater, srcDateTime, null, srcTimeZoneId, dstFormater, null,
				dstTimeZoneId);
	}

	public static String string2TimeFromat(String srcFormater, String srcDateTime, String dstFormater, String dstLocale,
			String dstTimeZoneId) throws ParseException {
		return string2TimezoneForLocale(srcFormater, srcDateTime, null, null, dstFormater, dstLocale, dstTimeZoneId);
	}

	public static String string2TimezoneForLocale(String srcFormater, String srcDateTime, String srcLocale,
			String srcTimeZoneId, String dstFormater, String dstLocale, String dstTimeZoneId) throws ParseException {
		if (srcFormater == null || "".equals(srcFormater))
			return null;
		if (srcDateTime == null || "".equals(srcDateTime))
			return null;
		if (dstFormater == null || "".equals(dstFormater))
			return null;
		if (dstTimeZoneId == null || "".equals(dstTimeZoneId))
			return null;
		if (srcTimeZoneId == null || "".equals(srcTimeZoneId))
			srcTimeZoneId = TimeZone.getDefault().getID();

		SimpleDateFormat srcSdf = new SimpleDateFormat(srcFormater);
		if (srcLocale != null) {
			srcSdf = new SimpleDateFormat(srcFormater, new Locale(srcLocale));
		}
		srcSdf.setTimeZone(TimeZone.getTimeZone(srcTimeZoneId));

		SimpleDateFormat dstSdf = new SimpleDateFormat(dstFormater);
		if (dstLocale != null) {
			dstSdf = new SimpleDateFormat(dstFormater, new Locale(dstLocale));
		}
		dstSdf.setTimeZone(TimeZone.getTimeZone(dstTimeZoneId));

		Date date = srcSdf.parse(srcDateTime.replaceAll("[\\s\\u00A0]+", " ").trim());// 去掉160和32空格
		return dstSdf.format(new Date(date.getTime()));
	}

	/**
	 * 日期(时间)转化为字符串.
	 * 
	 * @param formater
	 *            日期或时间的格式.
	 * @param aDate
	 *            java.util.Date类的实例.
	 * @return 日期转化后的字符串.
	 */
	public static String date2String(String formater, Date aDate) {
		if (formater == null || "".equals(formater))
			return null;
		if (aDate == null)
			return null;
		return (new SimpleDateFormat(formater)).format(aDate);
	}

	public static void main(String[] args) throws ParseException {

		Locale[] ls = Locale.getAvailableLocales();

		for (Locale locale : ls) {
			System.out.println("locale :" + locale);
		}

		// String[] time =TimeZone.getAvailableIDs();
		// for (String iem:time) {
		// System.out.println("locale-zone :"+iem);
		// }
		System.out.println("2016-04-22 16:21:10");
		System.out.println(string2TimezoneDefaultFormat("2016-04-22 16:21:10", "Asia/Shanghai", "IST"));
		System.out.println(string2TimezoneDefaultFormat("2016-04-22 16:21:10", "IST", "Asia/Shanghai"));

		System.out.println("May 9, 2016 21:50 IST");
		System.out.println(string2TimezoneForLocale("MMM dd, yyyy HH:mm", "May 9, 2016 21:50 IST", "Hindi", "IST",
				"yyyy-MM-dd HH:mm:ss", Locale.CHINA.toString(), "Asia/Shanghai"));
	}
}
