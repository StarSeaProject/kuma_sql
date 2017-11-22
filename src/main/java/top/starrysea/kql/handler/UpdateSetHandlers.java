package top.starrysea.kql.handler;

public class UpdateSetHandlers {

	public final static IUpdateSetHandler assignHandler = (updateSetClause, updateBuilder, params) -> {
		updateBuilder.append(updateSetClause.getColumnName() + " = ? ");
		updateBuilder.append(",");
		params.add(updateSetClause.getValue());
		return new HandleResult(updateBuilder, params);
	};

	public final static IUpdateSetHandler addHandler = (updateSetClause, updateBuilder, params) -> {
		updateBuilder.append(updateSetClause.getColumnName() + " = " + updateSetClause.getColumnName() + " + ? ");
		updateBuilder.append(",");
		params.add(updateSetClause.getValue());
		return new HandleResult(updateBuilder, params);
	};

	public final static IUpdateSetHandler reduceHandler = (updateSetClause, updateBuilder, params) -> {
		updateBuilder.append(updateSetClause.getColumnName() + " = " + updateSetClause.getColumnName() + " - ? ");
		updateBuilder.append(",");
		params.add(updateSetClause.getValue());
		return new HandleResult(updateBuilder, params);
	};
}
