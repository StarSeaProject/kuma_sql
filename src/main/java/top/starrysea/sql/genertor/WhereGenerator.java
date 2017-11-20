package top.starrysea.sql.genertor;

import static top.starrysea.common.Common.isNotNull;

import java.util.ArrayList;
import java.util.List;

import top.starrysea.sql.SqlWithParams;
import top.starrysea.sql.clause.WhereClause;
import top.starrysea.sql.ISqlGenerator;
import top.starrysea.sql.QuerySqlGenerator;
import top.starrysea.sql.handler.HandleResult;
import top.starrysea.sql.handler.IWhereHandler;

public class WhereGenerator extends Generator {

	public WhereGenerator(ISqlGenerator sqlGenerator) {
		super(sqlGenerator);
	}

	@Override
	public SqlWithParams generate(SqlWithParams sqlWithParams) {
		StringBuffer sqlBuffer = new StringBuffer(sqlWithParams.getSql());
		List<WhereClause> whereClauses = sqlGenerator.getWhereClauses();
		StringBuilder whereBuffer = new StringBuilder();
		List<Object> params = new ArrayList<>();
		whereBuffer.append("WHERE 1=1 ");

		for (WhereClause where : whereClauses) {
			if (isNotNull(where.getValue())) {
				IWhereHandler handler = QuerySqlGenerator.getHandlerMap().get(where.getWhereType());
				HandleResult result = handler.handleWhereBuffer(where, whereBuffer, params);
				whereBuffer = result.getWhereBuffer();
				params = result.getPreParams();
			}
		}

		if (getNextGenerator() != null) {
			return getNextGenerator().generate(new SqlWithParams(sqlBuffer.append(whereBuffer).toString(), params.toArray()));
		} else {
			return new SqlWithParams(sqlBuffer.append(whereBuffer).toString(), params.toArray());
		}
	}

}
