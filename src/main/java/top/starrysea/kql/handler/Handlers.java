package top.starrysea.kql.handler;

public class Handlers {
	public final static IWhereHandler equalsHandler = (where, whereBuffer, preParams) -> {
		whereBuffer.insert(whereBuffer.indexOf("WHERE") + 5, " " + where.getColumnName() + " = ? AND");
		preParams.add(where.getValue());
		return new HandleResult(whereBuffer, preParams);
	};

	public final static IWhereHandler frontFuzzyHandler = (where, whereBuffer, preParams) -> {
		whereBuffer.insert(whereBuffer.indexOf("WHERE") + 5, " " + where.getColumnName() + " LIKE ? AND");
		preParams.add("%" + where.getValue());
		return new HandleResult(whereBuffer, preParams);
	};

	public final static IWhereHandler backFuzzyHandler = (where, whereBuffer, preParams) -> {
		whereBuffer.insert(whereBuffer.indexOf("WHERE") + 5, " " + where.getColumnName() + " LIKE ? AND");
		preParams.add(where.getValue() + "%");
		return new HandleResult(whereBuffer, preParams);
	};

	public final static IWhereHandler fuzzyHandler = (where, whereBuffer, preParams) -> {
		whereBuffer.insert(whereBuffer.indexOf("WHERE") + 5, " " + where.getColumnName() + " LIKE ? AND");
		preParams.add("%" + where.getValue() + "%");
		return new HandleResult(whereBuffer, preParams);
	};

	public final static IWhereHandler greaterHandler = (where, whereBuffer, preParams) -> {
		whereBuffer.insert(whereBuffer.indexOf("WHERE") + 5, " " + where.getColumnName() + " > ? AND");
		preParams.add("%" + where.getValue() + "%");
		return new HandleResult(whereBuffer, preParams);
	};

	public final static IWhereHandler greaterEqualHandler = (where, whereBuffer, preParams) -> {
		whereBuffer.insert(whereBuffer.indexOf("WHERE") + 5, " " + where.getColumnName() + " >= ? AND");
		preParams.add("%" + where.getValue() + "%");
		return new HandleResult(whereBuffer, preParams);
	};

	public final static IWhereHandler lessHandler = (where, whereBuffer, preParams) -> {
		whereBuffer.insert(whereBuffer.indexOf("WHERE") + 5, " " + where.getColumnName() + " < ? AND");
		preParams.add("%" + where.getValue() + "%");
		return new HandleResult(whereBuffer, preParams);
	};

	public final static IWhereHandler lessEqualHandler = (where, whereBuffer, preParams) -> {
		whereBuffer.insert(whereBuffer.indexOf("WHERE") + 5, " " + where.getColumnName() + " <= ? AND");
		preParams.add("%" + where.getValue() + "%");
		return new HandleResult(whereBuffer, preParams);
	};
}
