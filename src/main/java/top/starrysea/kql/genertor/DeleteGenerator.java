package top.starrysea.kql.genertor;

import static top.starrysea.kql.common.Common.pojo2table;

import java.util.ArrayList;
import java.util.List;

import top.starrysea.kql.ISqlGenerator;
import top.starrysea.kql.SqlWithParams;
import top.starrysea.kql.UpdateSqlGenerator;
import top.starrysea.kql.clause.WhereClause;
import top.starrysea.kql.handler.HandleResult;
import top.starrysea.kql.handler.IWhereHandler;

public class DeleteGenerator extends Generator {

	public DeleteGenerator(ISqlGenerator sqlGenerator) {
		super(sqlGenerator);
	}

	@Override
	public SqlWithParams generate(SqlWithParams sqlWithParams) {
		StringBuilder deleteBuilder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		deleteBuilder.append("DELETE FROM " + pojo2table(sqlGenerator.getTable().getSimpleName()) + " ");
		if (!sqlGenerator.getWhereClauses().isEmpty()) {
			deleteBuilder.append("WHERE ");
			for (WhereClause whereClause : sqlGenerator.getWhereClauses()) {
				IWhereHandler handler = UpdateSqlGenerator.getWhereHandlerMap().get(whereClause.getWhereType());
				HandleResult result = handler.handleWhereBuffer(whereClause, deleteBuilder, params);
				deleteBuilder = result.getBuffer();
				params = result.getPreParams();
			}
			deleteBuilder.delete(deleteBuilder.length() - 5, deleteBuilder.length());
			return new SqlWithParams(deleteBuilder.toString(), params.toArray());
		}
		return new SqlWithParams(deleteBuilder.toString(), params.toArray());
	}

}
