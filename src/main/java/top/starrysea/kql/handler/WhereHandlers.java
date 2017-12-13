package top.starrysea.kql.handler;

public class WhereHandlers {
	
	private WhereHandlers() {}
	
	private static final String WHERE = "WHERE";
	
	public static final IWhereHandler equalsHandler = (where, whereBuilder, params) -> {
		whereBuilder.insert(whereBuilder.indexOf(WHERE) + 5, " " + where.getColumnName() + " = ? AND");
		params.add(where.getValue());
		return new HandleResult(whereBuilder, params);
	};

	public static final IWhereHandler frontFuzzyHandler = (where, whereBuilder, params) -> {
		whereBuilder.insert(whereBuilder.indexOf(WHERE) + 5, " " + where.getColumnName() + " LIKE ? AND");
		params.add("%" + where.getValue());
		return new HandleResult(whereBuilder, params);
	};

	public static final IWhereHandler backFuzzyHandler = (where, whereBuilder, params) -> {
		whereBuilder.insert(whereBuilder.indexOf(WHERE) + 5, " " + where.getColumnName() + " LIKE ? AND");
		params.add(where.getValue() + "%");
		return new HandleResult(whereBuilder, params);
	};

	public static final IWhereHandler fuzzyHandler = (where, whereBuilder, params) -> {
		whereBuilder.insert(whereBuilder.indexOf(WHERE) + 5, " " + where.getColumnName() + " LIKE ? AND");
		params.add("%" + where.getValue() + "%");
		return new HandleResult(whereBuilder, params);
	};

	public static final IWhereHandler greaterHandler = (where, whereBuilder, params) -> {
		whereBuilder.insert(whereBuilder.indexOf(WHERE) + 5, " " + where.getColumnName() + " > ? AND");
		params.add("%" + where.getValue() + "%");
		return new HandleResult(whereBuilder, params);
	};

	public static final IWhereHandler greaterEqualHandler = (where, whereBuilder, params) -> {
		whereBuilder.insert(whereBuilder.indexOf(WHERE) + 5, " " + where.getColumnName() + " >= ? AND");
		params.add("%" + where.getValue() + "%");
		return new HandleResult(whereBuilder, params);
	};

	public static final IWhereHandler lessHandler = (where, whereBuilder, params) -> {
		whereBuilder.insert(whereBuilder.indexOf(WHERE) + 5, " " + where.getColumnName() + " < ? AND");
		params.add("%" + where.getValue() + "%");
		return new HandleResult(whereBuilder, params);
	};

	public static final IWhereHandler lessEqualHandler = (where, whereBuilder, params) -> {
		whereBuilder.insert(whereBuilder.indexOf(WHERE) + 5, " " + where.getColumnName() + " <= ? AND");
		params.add("%" + where.getValue() + "%");
		return new HandleResult(whereBuilder, params);
	};
}
