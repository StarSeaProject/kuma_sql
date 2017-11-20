package top.starrysea.sql.genertor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import top.starrysea.sql.SqlWithParams;
import top.starrysea.sql.clause.SelectClause;
import top.starrysea.sql.ISqlGenerator;

public class SelectGenerator extends Generator {

	public SelectGenerator(ISqlGenerator sqlGenerator) {
		super(sqlGenerator);
	}

	@Override
	public SqlWithParams generate(SqlWithParams sqlWithParams) {
		List<String> select = new ArrayList<>();
		select.add("SELECT");
		select.add(String.join(",", sqlGenerator.getSelectClauses().stream().map(SelectClause::getColunmName)
				.collect(Collectors.toList())));
		if (getNextGenerator() != null) {
			return getNextGenerator().generate(new SqlWithParams(String.join(" ", select) + " ", null));
		} else {
			return new SqlWithParams(String.join(" ", select) + " ", null);
		}
	}

}
