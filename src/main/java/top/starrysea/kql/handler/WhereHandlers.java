package top.starrysea.kql.handler;

public class WhereHandlers {
	public final static IWhereHandler equalsHandler = (where, whereBuilder, params) -> {
		whereBuilder.insert(whereBuilder.indexOf("WHERE") + 5, " " + where.getColumnName() + " = ? AND");
		params.add(where.getValue());
		return new HandleResult(whereBuilder, params);
	};

	public final static IWhereHandler frontFuzzyHandler = (where, whereBuilder, params) -> {
		whereBuilder.insert(whereBuilder.indexOf("WHERE") + 5, " " + where.getColumnName() + " LIKE ? AND");
		params.add("%" + where.getValue());
		return new HandleResult(whereBuilder, params);
	};

	public final static IWhereHandler backFuzzyHandler = (where, whereBuilder, params) -> {
		whereBuilder.insert(whereBuilder.indexOf("WHERE") + 5, " " + where.getColumnName() + " LIKE ? AND");
		params.add(where.getValue() + "%");
		return new HandleResult(whereBuilder, params);
	};

	public final static IWhereHandler fuzzyHandler = (where, whereBuilder, params) -> {
		whereBuilder.insert(whereBuilder.indexOf("WHERE") + 5, " " + where.getColumnName() + " LIKE ? AND");
		params.add("%" + where.getValue() + "%");
		return new HandleResult(whereBuilder, params);
	};

	public final static IWhereHandler greaterHandler = (where, whereBuilder, params) -> {
		whereBuilder.insert(whereBuilder.indexOf("WHERE") + 5, " " + where.getColumnName() + " > ? AND");
		params.add("%" + where.getValue() + "%");
		return new HandleResult(whereBuilder, params);
	};

	public final static IWhereHandler greaterEqualHandler = (where, whereBuilder, params) -> {
		whereBuilder.insert(whereBuilder.indexOf("WHERE") + 5, " " + where.getColumnName() + " >= ? AND");
		params.add("%" + where.getValue() + "%");
		return new HandleResult(whereBuilder, params);
	};

	public final static IWhereHandler lessHandler = (where, whereBuilder, params) -> {
		whereBuilder.insert(whereBuilder.indexOf("WHERE") + 5, " " + where.getColumnName() + " < ? AND");
		params.add("%" + where.getValue() + "%");
		return new HandleResult(whereBuilder, params);
	};

	public final static IWhereHandler lessEqualHandler = (where, whereBuilder, params) -> {
		whereBuilder.insert(whereBuilder.indexOf("WHERE") + 5, " " + where.getColumnName() + " <= ? AND");
		params.add("%" + where.getValue() + "%");
		return new HandleResult(whereBuilder, params);
	};
}
