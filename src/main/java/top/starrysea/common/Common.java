package top.starrysea.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import top.starrysea.entity.Entity;

public class Common {

	private static SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat timeSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// 私有构造器防止外部创建新的Util对象
	private Common() {
	}

	public static String date2String(Date date) {
		return dateSdf.format(date);
	}

	public static String time2String(Date date) {
		return timeSdf.format(date);
	}

	public static String getNowDate() {
		return date2String(new Date());
	}

	public static String getNowTime() {
		return time2String(new Date());
	}

	public static String getBefore(long time) {
		return time2String(new Date(System.currentTimeMillis() - time));
	}

	public static String getCharId() {
		return getCharId(new String(), 10);
	}

	public static String getCharId(int size) {
		return getCharId(new String(), size);
	}

	public static String getCharId(String pre, int size) {
		StringBuffer theResult = new StringBuffer();
		theResult.append(pre);
		String a = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i = 0; i < size - pre.length(); i++) {
			int rand = (int) (Math.random() * a.length());
			theResult.append(a.charAt(rand));
		}
		return theResult.toString();
	}

	public static short getRandom(int randomRange) {
		Random random = new Random();
		return (short) random.nextInt(randomRange);
	}

	public static boolean isNotNull(Object object) {
		boolean result = false;
		if (object == null)
			return result;
		if (object instanceof String) {
			String temp = (String) object;
			if (temp != null && !temp.equals(""))
				result = true;
			else
				result = false;
		} else if (object instanceof Entity) {
			result = (object != null ? true : false);
		} else if (object instanceof Integer) {
			int i = (int) object;
			if (i == 0)
				result = false;
			else
				result = true;
		}
		return result;
	}
	
	public static String pojo2table(String pojoName) {
		List<Integer> uppercaseIndex = new ArrayList<>();
		StringBuilder tableName = new StringBuilder();
		for (int i = 1; i < pojoName.length(); i++) {
			if (Character.isUpperCase(pojoName.charAt(i))) {
				uppercaseIndex.add(i);
			}
		}
		tableName.append(Character.toLowerCase(pojoName.charAt(0)));
		if (uppercaseIndex.size() > 0) {
			for (Integer index : uppercaseIndex) {
				tableName.append(pojoName.substring(1, index));
				tableName.append("_" + Character.toLowerCase(pojoName.charAt(index)));
			}
			tableName.append(pojoName.substring(uppercaseIndex.get(uppercaseIndex.size() - 1) + 1));
		} else {
			tableName.append(pojoName.substring(1));
		}
		return tableName.toString();
	}
}
