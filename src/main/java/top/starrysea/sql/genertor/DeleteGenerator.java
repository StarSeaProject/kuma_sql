package top.starrysea.sql.genertor;

import java.util.ArrayList;
import java.util.List;

import top.starrysea.sql.ISqlGenerator;
import top.starrysea.sql.SqlWithParams;
import top.starrysea.sql.UpdateSqlGenerator;
import top.starrysea.sql.clause.WhereClause;
import top.starrysea.sql.handler.HandleResult;
import top.starrysea.sql.handler.IWhereHandler;

import static top.starrysea.common.Common.pojo2table;

public class DeleteGenerator extends Generator {

	public DeleteGenerator(ISqlGenerator sqlGenerator) {
		super(sqlGenerator);
	}

	@Override
	public SqlWithParams generate(SqlWithParams sqlWithParams) {
		StringBuilder deleteBuilder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		deleteBuilder.append("DELETE FROM " + pojo2table(sqlGenerator.getTable().getSimpleName()) + " ");
		if (sqlGenerator.getWhereClauses().size() > 0) {
			deleteBuilder.append("WHERE ");
			for (WhereClause whereClause : sqlGenerator.getWhereClauses()) {
				IWhereHandler handler = UpdateSqlGenerator.getHandlerMap().get(whereClause.getWhereType());
				HandleResult result = handler.handleWhereBuffer(whereClause, deleteBuilder, params);
				deleteBuilder = result.getWhereBuffer();
				params = result.getPreParams();
			}
			deleteBuilder.delete(deleteBuilder.length() - 5, deleteBuilder.length());
			return new SqlWithParams(deleteBuilder.toString(), params.toArray());
		}
		return new SqlWithParams(deleteBuilder.toString(), params.toArray());
	}

}
