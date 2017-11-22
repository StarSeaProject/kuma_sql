package top.starrysea.kql.genertor;

import top.starrysea.kql.ISqlGenerator;
import top.starrysea.kql.SqlWithParams;
import top.starrysea.kql.UpdateSqlGenerator;
import top.starrysea.kql.clause.UpdateSetClause;
import top.starrysea.kql.clause.WhereClause;
import top.starrysea.kql.handler.HandleResult;
import top.starrysea.kql.handler.IUpdateSetHandler;
import top.starrysea.kql.handler.IWhereHandler;

import static top.starrysea.kql.common.Common.pojo2table;

import java.util.ArrayList;
import java.util.List;

public class UpdateGenerator extends Generator {

	public UpdateGenerator(ISqlGenerator updateSqlGenerator) {
		super(updateSqlGenerator);
	}

	@Override
	public SqlWithParams generate(SqlWithParams sqlWithParams) {
		StringBuilder updateBuilder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		updateBuilder.append("UPDATE " + pojo2table(sqlGenerator.getTable().getSimpleName()) + " SET ");
		for (UpdateSetClause updateSetClause : sqlGenerator.getUpdateSetClauses()) {
			IUpdateSetHandler handler=UpdateSqlGenerator.getUpdateSetHandlerMap().get(updateSetClause.getUpdateSetType());
			HandleResult result = handler.handleUpdateBuffer(updateSetClause, updateBuilder, params);
			updateBuilder = result.getBuffer();
			params = result.getPreParams();
		}
		updateBuilder.deleteCharAt(updateBuilder.length() - 1);
		updateBuilder.append("WHERE ");
		for (WhereClause whereClause : sqlGenerator.getWhereClauses()) {
			IWhereHandler handler = UpdateSqlGenerator.getWhereHandlerMap().get(whereClause.getWhereType());
			HandleResult result = handler.handleWhereBuffer(whereClause, updateBuilder, params);
			updateBuilder = result.getBuffer();
			params = result.getPreParams();
		}
		updateBuilder.delete(updateBuilder.length() - 5, updateBuilder.length());
		return new SqlWithParams(updateBuilder.toString(), params.toArray());
	}
}
