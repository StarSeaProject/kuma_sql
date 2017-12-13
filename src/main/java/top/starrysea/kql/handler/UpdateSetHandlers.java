package top.starrysea.kql.handler;

public class UpdateSetHandlers {

	private UpdateSetHandlers() {}
	
	public static final IUpdateSetHandler assignHandler = (updateSetClause, updateBuilder, params) -> {
		updateBuilder.append(updateSetClause.getColumnName() + " = ? ");
		updateBuilder.append(",");
		params.add(updateSetClause.getValue());
		return new HandleResult(updateBuilder, params);
	};

	public static final IUpdateSetHandler addHandler = (updateSetClause, updateBuilder, params) -> {
		updateBuilder.append(updateSetClause.getColumnName() + " = " + updateSetClause.getColumnName() + " + ? ");
		updateBuilder.append(",");
		params.add(updateSetClause.getValue());
		return new HandleResult(updateBuilder, params);
	};

	public static final IUpdateSetHandler reduceHandler = (updateSetClause, updateBuilder, params) -> {
		updateBuilder.append(updateSetClause.getColumnName() + " = " + updateSetClause.getColumnName() + " - ? ");
		updateBuilder.append(",");
		params.add(updateSetClause.getValue());
		return new HandleResult(updateBuilder, params);
	};
}
