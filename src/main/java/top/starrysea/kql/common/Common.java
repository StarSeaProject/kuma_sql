package top.starrysea.kql.common;

import java.util.ArrayList;
import java.util.List;

import top.starrysea.kql.entity.Entity;

public class Common {

	// 私有构造器防止外部创建新的Util对象
	private Common() {
	}

	public static boolean isNotNull(Object object) {
		boolean result = false;
		if (object == null)
			return result;
		if (object instanceof String) {
			String temp = (String) object;
			if (!temp.equals(""))
				result = true;
			else
				result = false;
		} else if (object instanceof Entity) {
			result = true;
		} else if (object instanceof Integer) {
			int i = (int) object;
			if (i == 0)
				result = false;
			else
				result = true;
		} else if (object instanceof Short) {
			short i = (short) object;
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
		if (!uppercaseIndex.isEmpty()) {
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
