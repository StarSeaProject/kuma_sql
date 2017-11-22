package top.starrysea.kql.facede;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import top.starrysea.kql.DeleteSqlGenerator;
import top.starrysea.kql.ISqlGenerator;
import top.starrysea.kql.InsertSqlGenerator;
import top.starrysea.kql.QuerySqlGenerator;
import top.starrysea.kql.SqlWithParams;
import top.starrysea.kql.UpdateSqlGenerator;
import top.starrysea.kql.clause.OrderByType;
import top.starrysea.kql.clause.SelectClause;
import top.starrysea.kql.clause.UpdateSetType;
import top.starrysea.kql.clause.WhereType;
import top.starrysea.kql.entity.Entity;
import top.starrysea.kql.entity.IBuilder;

@Component("kumaSqlDao")
@Scope("prototype")
public class KumaSqlDaoImpl implements KumaSqlDao {

	@Autowired
	private JdbcTemplate template;

	private IBuilder<? extends ISqlGenerator> builder;

	private ThreadLocal<OperationType> operationType = new ThreadLocal<>();

	public KumaSqlDaoImpl() {
		operationType.set(OperationType.SELECT);
		builder = new QuerySqlGenerator.Builder();
	}

	public void changeMode(OperationType operationType) {
		this.operationType.set(operationType);
		switch (operationType) {
		case INSERT:
			builder = new InsertSqlGenerator.Builder();
			break;
		case DELETE:
			builder = new DeleteSqlGenerator.Builder();
			break;
		case SELECT:
			builder = new QuerySqlGenerator.Builder();
			break;
		case UPDATE:
			builder = new UpdateSqlGenerator.Builder();
			break;
		}
	}

	public KumaSqlDao select(SelectClause selectClause) {
		if (operationType.get() != OperationType.SELECT)
			throw new IllegalStateException("当前不是SELECT模式,请调用changeMode进入SELECT模式!");
		top.starrysea.kql.QuerySqlGenerator.Builder queryBuilder = (top.starrysea.kql.QuerySqlGenerator.Builder) builder;
		queryBuilder.select(selectClause);
		return this;
	}

	public KumaSqlDao select(String colunmName) {
		if (operationType.get() != OperationType.SELECT)
			throw new IllegalStateException("当前不是SELECT模式,请调用changeMode进入SELECT模式!");
		top.starrysea.kql.QuerySqlGenerator.Builder queryBuilder = (top.starrysea.kql.QuerySqlGenerator.Builder) builder;
		queryBuilder.select(colunmName);
		return this;
	}

	public KumaSqlDao select(String colunmName, String alias) {
		if (operationType.get() != OperationType.SELECT)
			throw new IllegalStateException("当前不是SELECT模式,请调用changeMode进入SELECT模式!");
		top.starrysea.kql.QuerySqlGenerator.Builder queryBuilder = (top.starrysea.kql.QuerySqlGenerator.Builder) builder;
		queryBuilder.select(colunmName, alias);
		return this;
	}

	public KumaSqlDao from(Class<? extends Entity> table) {
		if (operationType.get() != OperationType.SELECT)
			throw new IllegalStateException("当前不是SELECT模式,请调用changeMode进入SELECT模式!");
		top.starrysea.kql.QuerySqlGenerator.Builder queryBuilder = (top.starrysea.kql.QuerySqlGenerator.Builder) builder;
		queryBuilder.from(table);
		return this;
	}

	public KumaSqlDao from(Class<? extends Entity> table, String alias) {
		if (operationType.get() != OperationType.SELECT)
			throw new IllegalStateException("当前不是SELECT模式,请调用changeMode进入SELECT模式!");
		top.starrysea.kql.QuerySqlGenerator.Builder queryBuilder = (top.starrysea.kql.QuerySqlGenerator.Builder) builder;
		queryBuilder.from(table, alias);
		return this;
	}

	public KumaSqlDao where(String columnName, WhereType whereType, Object value) {
		if (operationType.get() == OperationType.SELECT) {
			top.starrysea.kql.QuerySqlGenerator.Builder queryBuilder = (top.starrysea.kql.QuerySqlGenerator.Builder) builder;
			queryBuilder.where(columnName, whereType, value);
			return this;
		} else if (operationType.get() == OperationType.UPDATE) {
			top.starrysea.kql.UpdateSqlGenerator.Builder updateBuilder = (top.starrysea.kql.UpdateSqlGenerator.Builder) builder;
			updateBuilder.where(columnName, whereType, value);
			return this;
		} else if (operationType.get() == OperationType.DELETE) {
			top.starrysea.kql.DeleteSqlGenerator.Builder deleteBuilder = (top.starrysea.kql.DeleteSqlGenerator.Builder) builder;
			deleteBuilder.where(columnName, whereType, value);
			return this;
		} else
			throw new IllegalStateException("当前不是SELECT模式,请调用changeMode进入SELECT模式!");
	}

	public KumaSqlDao where(String columnName, String alias, WhereType whereType, Object value) {
		if (operationType.get() == OperationType.SELECT) {
			top.starrysea.kql.QuerySqlGenerator.Builder queryBuilder = (top.starrysea.kql.QuerySqlGenerator.Builder) builder;
			queryBuilder.where(columnName, alias, whereType, value);
			return this;
		} else
			throw new IllegalStateException("当前不是SELECT模式,请调用changeMode进入SELECT模式!");
	}

	@Override
	public KumaSqlDao orderBy(String columnName) {
		if (operationType.get() != OperationType.SELECT)
			throw new IllegalStateException("当前不是SELECT模式,请调用changeMode进入SELECT模式!");
		top.starrysea.kql.QuerySqlGenerator.Builder queryBuilder = (top.starrysea.kql.QuerySqlGenerator.Builder) builder;
		queryBuilder.orderBy(columnName);
		return this;
	}

	@Override
	public KumaSqlDao orderBy(String columnName, OrderByType orderByType) {
		if (operationType.get() != OperationType.SELECT)
			throw new IllegalStateException("当前不是SELECT模式,请调用changeMode进入SELECT模式!");
		top.starrysea.kql.QuerySqlGenerator.Builder queryBuilder = (top.starrysea.kql.QuerySqlGenerator.Builder) builder;
		queryBuilder.orderBy(columnName, orderByType);
		return this;
	}

	@Override
	public KumaSqlDao orderBy(String columnName, String alias) {
		if (operationType.get() != OperationType.SELECT)
			throw new IllegalStateException("当前不是SELECT模式,请调用changeMode进入SELECT模式!");
		top.starrysea.kql.QuerySqlGenerator.Builder queryBuilder = (top.starrysea.kql.QuerySqlGenerator.Builder) builder;
		queryBuilder.orderBy(columnName, alias);
		return this;
	}

	@Override
	public KumaSqlDao orderBy(String columnName, String alias, OrderByType orderByType) {
		if (operationType.get() != OperationType.SELECT)
			throw new IllegalStateException("当前不是SELECT模式,请调用changeMode进入SELECT模式!");
		top.starrysea.kql.QuerySqlGenerator.Builder queryBuilder = (top.starrysea.kql.QuerySqlGenerator.Builder) builder;
		queryBuilder.orderBy(columnName, alias, orderByType);
		return this;
	}

	@Override
	public KumaSqlDao limit(int start) {
		if (operationType.get() != OperationType.SELECT)
			throw new IllegalStateException("当前不是SELECT模式,请调用changeMode进入SELECT模式!");
		top.starrysea.kql.QuerySqlGenerator.Builder queryBuilder = (top.starrysea.kql.QuerySqlGenerator.Builder) builder;
		queryBuilder.limit(start);
		return this;
	}

	@Override
	public KumaSqlDao limit(int start, int limit) {
		if (operationType.get() != OperationType.SELECT)
			throw new IllegalStateException("当前不是SELECT模式,请调用changeMode进入SELECT模式!");
		top.starrysea.kql.QuerySqlGenerator.Builder queryBuilder = (top.starrysea.kql.QuerySqlGenerator.Builder) builder;
		queryBuilder.limit(start, limit);
		return this;
	}

	@Override
	public KumaSqlDao innerjoin(Class<? extends Entity> target, String alias, String targetColumn,
			Class<? extends Entity> source, String sourceColumn) {
		if (operationType.get() != OperationType.SELECT)
			throw new IllegalStateException("当前不是SELECT模式,请调用changeMode进入SELECT模式!");
		top.starrysea.kql.QuerySqlGenerator.Builder queryBuilder = (top.starrysea.kql.QuerySqlGenerator.Builder) builder;
		queryBuilder.innerjoin(target, alias, targetColumn, source, sourceColumn);
		return this;
	}

	@Override
	public KumaSqlDao leftjoin(Class<? extends Entity> target, String alias, String targetColumn,
			Class<? extends Entity> source, String sourceColumn) {
		if (operationType.get() != OperationType.SELECT)
			throw new IllegalStateException("当前不是SELECT模式,请调用changeMode进入SELECT模式!");
		top.starrysea.kql.QuerySqlGenerator.Builder queryBuilder = (top.starrysea.kql.QuerySqlGenerator.Builder) builder;
		queryBuilder.leftjoin(target, alias, targetColumn, source, sourceColumn);
		return this;
	}

	@Override
	public KumaSqlDao rightjoin(Class<? extends Entity> target, String alias, String targetColumn,
			Class<? extends Entity> source, String sourceColumn) {
		if (operationType.get() != OperationType.SELECT)
			throw new IllegalStateException("当前不是SELECT模式,请调用changeMode进入SELECT模式!");
		top.starrysea.kql.QuerySqlGenerator.Builder queryBuilder = (top.starrysea.kql.QuerySqlGenerator.Builder) builder;
		queryBuilder.rightjoin(target, alias, targetColumn, source, sourceColumn);
		return this;
	}

	@Override
	public KumaSqlDao fulljoin(Class<? extends Entity> target, String alias, String targetColumn,
			Class<? extends Entity> source, String sourceColumn) {
		if (operationType.get() != OperationType.SELECT)
			throw new IllegalStateException("当前不是SELECT模式,请调用changeMode进入SELECT模式!");
		top.starrysea.kql.QuerySqlGenerator.Builder queryBuilder = (top.starrysea.kql.QuerySqlGenerator.Builder) builder;
		queryBuilder.fulljoin(target, alias, targetColumn, source, sourceColumn);
		return this;
	}
	
	@Override
	public KumaSqlDao crossjoin(Class<? extends Entity> target, String alias, String targetColumn,
			Class<? extends Entity> source, String sourceColumn) {
		if (operationType.get() != OperationType.SELECT)
			throw new IllegalStateException("当前不是SELECT模式,请调用changeMode进入SELECT模式!");
		top.starrysea.kql.QuerySqlGenerator.Builder queryBuilder = (top.starrysea.kql.QuerySqlGenerator.Builder) builder;
		queryBuilder.crossjoin(target, alias, targetColumn, source, sourceColumn);
		return this;
	}

	@Override
	public KumaSqlDao insert(String columnName, Object value) {
		if (operationType.get() != OperationType.INSERT)
			throw new IllegalStateException("当前不是INSERT模式,请调用changeMode进入INSERT模式!");
		top.starrysea.kql.InsertSqlGenerator.Builder insertBuilder = (top.starrysea.kql.InsertSqlGenerator.Builder) builder;
		insertBuilder.insert(columnName, value);
		return this;
	}

	@Override
	public KumaSqlDao table(Class<? extends Entity> table) {
		if (operationType.get() == OperationType.INSERT) {
			top.starrysea.kql.InsertSqlGenerator.Builder insertBuilder = (top.starrysea.kql.InsertSqlGenerator.Builder) builder;
			insertBuilder.into(table);
			return this;
		} else if (operationType.get() == OperationType.DELETE) {
			top.starrysea.kql.DeleteSqlGenerator.Builder deleteBuilder = (top.starrysea.kql.DeleteSqlGenerator.Builder) builder;
			deleteBuilder.table(table);
			return this;
		} else if (operationType.get() == OperationType.UPDATE) {
			top.starrysea.kql.UpdateSqlGenerator.Builder updateBuilder = (top.starrysea.kql.UpdateSqlGenerator.Builder) builder;
			updateBuilder.table(table);
			return this;
		}
		throw new IllegalStateException("SELECT模式不支持该方法!");
	}

	@Override
	public KumaSqlDao update(String columnName, UpdateSetType updateSetType, Object value) {
		if (operationType.get() != OperationType.UPDATE)
			throw new IllegalStateException("当前不是UPDATE模式,请调用changeMode进入INSERT模式!");
		top.starrysea.kql.UpdateSqlGenerator.Builder updateBuilder = (top.starrysea.kql.UpdateSqlGenerator.Builder) builder;
		updateBuilder.update(columnName, updateSetType, value);
		return this;
	}

	@Override
	public <T> ListSqlResult endForList(RowMapper<? extends Entity> rowMapper) {
		if (operationType.get() != OperationType.SELECT)
			throw new UnsupportedOperationException("endForList方法仅支持SELECT模式,增删改请使用无参数版本的end方法");
		QuerySqlGenerator querySqlGenerator = (QuerySqlGenerator) builder.build();
		SqlWithParams sqlWithParams = querySqlGenerator.generate();
		List<? extends Entity> result = template.query(sqlWithParams.getSql(), sqlWithParams.getParams(), rowMapper);
		return new ListSqlResult(result);
	}

	@Override
	public <T> IntegerSqlResult endForNumber() {
		if (operationType.get() != OperationType.SELECT)
			throw new UnsupportedOperationException("endForNumber方法仅支持SELECT模式,增删改请使用无参数版本的end方法");
		QuerySqlGenerator querySqlGenerator = (QuerySqlGenerator) builder.build();
		if (querySqlGenerator.getSelectClauses().size() != 1)
			throw new IllegalArgumentException("只能SELECT一列的数字!如SELECT COUNT(*) FROM...");
		SqlWithParams sqlWithParams = querySqlGenerator.generate();
		Integer result = template.queryForObject(sqlWithParams.getSql(), sqlWithParams.getParams(), Integer.class);
		return new IntegerSqlResult(result);
	}

	@Override
	public <T> EntitySqlResult endForObject(RowMapper<? extends Entity> rowMapper) {
		if (operationType.get() != OperationType.SELECT)
			throw new UnsupportedOperationException("endForObject方法仅支持SELECT模式,增删改请使用无参数版本的end方法");
		QuerySqlGenerator querySqlGenerator = (QuerySqlGenerator) builder.build();
		SqlWithParams sqlWithParams = querySqlGenerator.generate();
		Entity result = template.queryForObject(sqlWithParams.getSql(), sqlWithParams.getParams(), rowMapper);
		return new EntitySqlResult(result);
	}

	@Override
	public <T> SqlResult end() {
		if (operationType.get() == OperationType.SELECT)
			throw new UnsupportedOperationException("该end方法不支持SELECT模式");
		SqlWithParams sqlWithParams = builder.build().generate();
		template.update(sqlWithParams.getSql(), sqlWithParams.getParams());
		return new SqlResult(true);
	}

}
