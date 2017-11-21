package top.starrysea.kql.genertor;

import java.util.ArrayList;
import java.util.List;

import top.starrysea.kql.QuerySqlGenerator;
import top.starrysea.kql.SqlWithParams;

public class GeneratorChain {

	private List<Generator> generatorChain = new ArrayList<>();

	private static GeneratorFactory generatorFactory;

	public GeneratorChain(QuerySqlGenerator sqlGenerator) {
		generatorFactory = new GeneratorFactory(sqlGenerator);
	}

	public void addGenerator(Class<?> generatortype) {
		generatorChain.add((Generator) generatorFactory.getGenerator(generatortype));
	}

	public SqlWithParams startGenerator() {
		for (int i = 0; i < generatorChain.size() - 1; i++) {
			generatorChain.get(i).setNextGenerator(generatorChain.get(i + 1));
		}
		return generatorChain.get(0).generate(new SqlWithParams());
	}
}
