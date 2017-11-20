package top.starrysea.sql.genertor;

import top.starrysea.sql.SqlWithParams;
import top.starrysea.sql.ISqlGenerator;

import static top.starrysea.common.Common.pojo2table;

public class TableGenerator extends Generator {

	public TableGenerator(ISqlGenerator sqlGenerator) {
		super(sqlGenerator);
	}

	@Override
	public SqlWithParams generate(SqlWithParams sqlWithParams) {
		StringBuffer sqlBuffer = new StringBuffer(sqlWithParams.getSql());
		sqlBuffer.append("FROM ");
		for (int i = 0; i < sqlGenerator.getTableClauses().size(); i++) {
			sqlBuffer.append(pojo2table(sqlGenerator.getTableClauses().get(i).getTable().getSimpleName()));
			if (sqlGenerator.getTableClauses().get(i).getAlias() != null) {
				sqlBuffer.append(" AS " + sqlGenerator.getTableClauses().get(i).getAlias() + ",");
			}
		}
		if (sqlBuffer.charAt(sqlBuffer.length() - 1) == ',') {
			sqlBuffer.deleteCharAt(sqlBuffer.length() - 1);
		}
		if (getNextGenerator() != null) {
			return getNextGenerator().generate(new SqlWithParams(sqlBuffer.append(" ").toString(), null));
		} else {
			return new SqlWithParams(sqlBuffer.append(" ").toString(), null);
		}
	}
}
