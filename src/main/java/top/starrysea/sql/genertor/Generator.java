package top.starrysea.sql.genertor;

import top.starrysea.sql.SqlWithParams;
import top.starrysea.sql.ISqlGenerator;
import top.starrysea.sql.SqlGeneratorAdapter;

public abstract class Generator {

	public abstract SqlWithParams generate(SqlWithParams sqlWithParams);

	protected SqlGeneratorAdapter sqlGenerator;

	protected Generator nextGenerator = null;

	protected Generator(ISqlGenerator sqlGenerator) {
		this.sqlGenerator = new SqlGeneratorAdapter(sqlGenerator);
	}

	public Generator getNextGenerator() {
		return nextGenerator;
	}

	public Generator setNextGenerator(Generator nextGenerator) {
		this.nextGenerator = nextGenerator;
		return this;
	}

}
