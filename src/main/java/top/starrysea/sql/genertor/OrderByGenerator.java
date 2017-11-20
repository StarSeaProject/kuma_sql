package top.starrysea.sql.genertor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import top.starrysea.sql.SqlWithParams;
import top.starrysea.sql.clause.OrderClause;
import top.starrysea.sql.ISqlGenerator;

public class OrderByGenerator extends Generator {

	public OrderByGenerator(ISqlGenerator sqlGenerator) {
		super(sqlGenerator);
	}

	@Override
	public SqlWithParams generate(SqlWithParams sqlWithParams) {
		if (sqlGenerator.getOrderByClauses().size() == 0) {
			return sqlWithParams;
		}
		List<String> orderBy = new ArrayList<>();
		orderBy.add("ORDER BY");
		orderBy.add(String.join(",", sqlGenerator.getOrderByClauses().stream().map(OrderClause::getColumnName)
				.collect(Collectors.toList())));
		if (getNextGenerator() != null) {
			return getNextGenerator().generate(new SqlWithParams(
					sqlWithParams.getSql() + String.join(" ", orderBy) + " ", sqlWithParams.getParams()));
		} else {
			return new SqlWithParams(sqlWithParams.getSql() + String.join(" ", orderBy) + " ",
					sqlWithParams.getParams());
		}
	}

}
