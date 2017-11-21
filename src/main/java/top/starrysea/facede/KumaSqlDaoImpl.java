package top.starrysea.facede;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import top.starrysea.entity.Entity;
import top.starrysea.entity.IBuilder;
import top.starrysea.sql.DeleteSqlGenerator;
import top.starrysea.sql.ISqlGenerator;
import top.starrysea.sql.InsertSqlGenerator;
import top.starrysea.sql.QuerySqlGenerator;
import top.starrysea.sql.SqlWithParams;
import top.starrysea.sql.UpdateSqlGenerator;
import top.starrysea.sql.clause.OrderByType;
import top.starrysea.sql.clause.SelectClause;
import top.starrysea.sql.clause.WhereType;

@Component("kumaSqlDao")
@Scope("prototype")
public class KumaSqlDaoImpl implements KumaSqlDao {

	@Autowired
	private JdbcTemplate template;

	private IBuilder<? extends ISqlGenerator> builder;

	private OperationType operationType;

	public KumaSqlDaoImpl() {
		operationType = OperationType.SELECT;
		builder = new QuerySqlGenerator.Builder();
	}

	public void changeMode(OperationType operationType) {
		this.operationType = operationType;
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

	public KumaSqlDao select(String colunmName) {
		if (operationType != OperationType.SELECT)
			throw new IllegalStateException("当前不是SELECT模式,请调用changeMode进入SELECT模式!");
		top.starrysea.sql.QuerySqlGenerator.Builder queryBuilder = (top.starrysea.sql.QuerySqlGenerator.Builder) builder;
		queryBuilder.select(colunmName);
		return this;
	}

	public KumaSqlDao select(String colunmName, String alias) {
		if (operationType != OperationType.SELECT)
			throw new IllegalStateException("当前不是SELECT模式,请调用changeMode进入SELECT模式!");
		top.starrysea.sql.QuerySqlGenerator.Builder queryBuilder = (top.starrysea.sql.QuerySqlGenerator.Builder) builder;
		queryBuilder.select(colunmName, alias);
		return this;
	}

	public KumaSqlDao from(Class<? extends Entity> table) {
		if (operationType != OperationType.SELECT)
			throw new IllegalStateException("当前不是SELECT模式,请调用changeMode进入SELECT模式!");
		top.starrysea.sql.QuerySqlGenerator.Builder queryBuilder = (top.starrysea.sql.QuerySqlGenerator.Builder) builder;
		queryBuilder.from(table);
		return this;
	}

	public KumaSqlDao from(Class<? extends Entity> table, String alias) {
		if (operationType != OperationType.SELECT)
			throw new IllegalStateException("当前不是SELECT模式,请调用changeMode进入SELECT模式!");
		top.starrysea.sql.QuerySqlGenerator.Builder queryBuilder = (top.starrysea.sql.QuerySqlGenerator.Builder) builder;
		queryBuilder.from(table, alias);
		return this;
	}

	public KumaSqlDao where(String columnName, WhereType whereType, Object value) {
		if (operationType == OperationType.SELECT) {
			top.starrysea.sql.QuerySqlGenerator.Builder queryBuilder = (top.starrysea.sql.QuerySqlGenerator.Builder) builder;
			queryBuilder.where(columnName, whereType, value);
			return this;
		} else if (operationType == OperationType.UPDATE) {
			top.starrysea.sql.UpdateSqlGenerator.Builder updateBuilder = (top.starrysea.sql.UpdateSqlGenerator.Builder) builder;
			updateBuilder.where(columnName, whereType, value);
			return this;
		} else if (operationType == OperationType.DELETE) {
			top.starrysea.sql.DeleteSqlGenerator.Builder deleteBuilder = (top.starrysea.sql.DeleteSqlGenerator.Builder) builder;
			deleteBuilder.where(columnName, whereType, value);
			return this;
		} else
			throw new IllegalStateException("当前不是SELECT模式,请调用changeMode进入SELECT模式!");
	}

	public KumaSqlDao where(String columnName, String alias, WhereType whereType, Object value) {
		if (operationType == OperationType.SELECT) {
			top.starrysea.sql.QuerySqlGenerator.Builder queryBuilder = (top.starrysea.sql.QuerySqlGenerator.Builder) builder;
			queryBuilder.where(columnName, alias, whereType, value);
			return this;
		} else
			throw new IllegalStateException("当前不是SELECT模式,请调用changeMode进入SELECT模式!");
	}

	@Override
	public KumaSqlDao orderBy(String columnName) {
		if (operationType != OperationType.SELECT)
			throw new IllegalStateException("当前不是SELECT模式,请调用changeMode进入SELECT模式!");
		top.starrysea.sql.QuerySqlGenerator.Builder queryBuilder = (top.starrysea.sql.QuerySqlGenerator.Builder) builder;
		queryBuilder.orderBy(columnName);
		return this;
	}

	@Override
	public KumaSqlDao orderBy(String columnName, OrderByType orderByType) {
		if (operationType != OperationType.SELECT)
			throw new IllegalStateException("当前不是SELECT模式,请调用changeMode进入SELECT模式!");
		top.starrysea.sql.QuerySqlGenerator.Builder queryBuilder = (top.starrysea.sql.QuerySqlGenerator.Builder) builder;
		queryBuilder.orderBy(columnName, orderByType);
		return this;
	}

	@Override
	public KumaSqlDao orderBy(String columnName, String alias) {
		if (operationType != OperationType.SELECT)
			throw new IllegalStateException("当前不是SELECT模式,请调用changeMode进入SELECT模式!");
		top.starrysea.sql.QuerySqlGenerator.Builder queryBuilder = (top.starrysea.sql.QuerySqlGenerator.Builder) builder;
		queryBuilder.orderBy(columnName, alias);
		return this;
	}

	@Override
	public KumaSqlDao orderBy(String columnName, String alias, OrderByType orderByType) {
		if (operationType != OperationType.SELECT)
			throw new IllegalStateException("当前不是SELECT模式,请调用changeMode进入SELECT模式!");
		top.starrysea.sql.QuerySqlGenerator.Builder queryBuilder = (top.starrysea.sql.QuerySqlGenerator.Builder) builder;
		queryBuilder.orderBy(columnName, alias, orderByType);
		return this;
	}

	@Override
	public KumaSqlDao limit(int start) {
		if (operationType != OperationType.SELECT)
			throw new IllegalStateException("当前不是SELECT模式,请调用changeMode进入SELECT模式!");
		top.starrysea.sql.QuerySqlGenerator.Builder queryBuilder = (top.starrysea.sql.QuerySqlGenerator.Builder) builder;
		queryBuilder.limit(start);
		return this;
	}

	@Override
	public KumaSqlDao limit(int start, int limit) {
		if (operationType != OperationType.SELECT)
			throw new IllegalStateException("当前不是SELECT模式,请调用changeMode进入SELECT模式!");
		top.starrysea.sql.QuerySqlGenerator.Builder queryBuilder = (top.starrysea.sql.QuerySqlGenerator.Builder) builder;
		queryBuilder.limit(start, limit);
		return this;
	}

	@Override
	public KumaSqlDao innerjoin(Class<? extends Entity> target, String alias, String targetColumn,
			Class<? extends Entity> source, String sourceColumn) {
		if (operationType != OperationType.SELECT)
			throw new IllegalStateException("当前不是SELECT模式,请调用changeMode进入SELECT模式!");
		top.starrysea.sql.QuerySqlGenerator.Builder queryBuilder = (top.starrysea.sql.QuerySqlGenerator.Builder) builder;
		queryBuilder.innerjoin(target, alias, targetColumn, source, sourceColumn);
		return this;
	}

	@Override
	public KumaSqlDao leftjoin(Class<? extends Entity> target, String alias, String targetColumn,
			Class<? extends Entity> source, String sourceColumn) {
		if (operationType != OperationType.SELECT)
			throw new IllegalStateException("当前不是SELECT模式,请调用changeMode进入SELECT模式!");
		top.starrysea.sql.QuerySqlGenerator.Builder queryBuilder = (top.starrysea.sql.QuerySqlGenerator.Builder) builder;
		queryBuilder.leftjoin(target, alias, targetColumn, source, sourceColumn);
		return this;
	}

	@Override
	public KumaSqlDao rightjoin(Class<? extends Entity> target, String alias, String targetColumn,
			Class<? extends Entity> source, String sourceColumn) {
		if (operationType != OperationType.SELECT)
			throw new IllegalStateException("当前不是SELECT模式,请调用changeMode进入SELECT模式!");
		top.starrysea.sql.QuerySqlGenerator.Builder queryBuilder = (top.starrysea.sql.QuerySqlGenerator.Builder) builder;
		queryBuilder.rightjoin(target, alias, targetColumn, source, sourceColumn);
		return this;
	}

	@Override
	public KumaSqlDao fulljoin(Class<? extends Entity> target, String alias, String targetColumn,
			Class<? extends Entity> source, String sourceColumn) {
		if (operationType != OperationType.SELECT)
			throw new IllegalStateException("当前不是SELECT模式,请调用changeMode进入SELECT模式!");
		top.starrysea.sql.QuerySqlGenerator.Builder queryBuilder = (top.starrysea.sql.QuerySqlGenerator.Builder) builder;
		queryBuilder.fulljoin(target, alias, targetColumn, source, sourceColumn);
		return this;
	}

	@Override
	public KumaSqlDao insert(String columnName, Object value) {
		if (operationType != OperationType.INSERT)
			throw new IllegalStateException("当前不是INSERT模式,请调用changeMode进入INSERT模式!");
		top.starrysea.sql.InsertSqlGenerator.Builder insertBuilder = (top.starrysea.sql.InsertSqlGenerator.Builder) builder;
		insertBuilder.insert(columnName, value);
		return this;
	}

	@Override
	public KumaSqlDao table(Class<? extends Entity> table) {
		if (operationType == OperationType.INSERT) {
			top.starrysea.sql.InsertSqlGenerator.Builder insertBuilder = (top.starrysea.sql.InsertSqlGenerator.Builder) builder;
			insertBuilder.into(table);
			return this;
		} else if (operationType == OperationType.DELETE) {
			top.starrysea.sql.DeleteSqlGenerator.Builder deleteBuilder = (top.starrysea.sql.DeleteSqlGenerator.Builder) builder;
			deleteBuilder.table(table);
			return this;
		} else if (operationType == OperationType.UPDATE) {
			top.starrysea.sql.UpdateSqlGenerator.Builder updateBuilder = (top.starrysea.sql.UpdateSqlGenerator.Builder) builder;
			updateBuilder.table(table);
			return this;
		}
		throw new IllegalStateException("SELECT模式不支持该方法!");
	}

	@Override
	public KumaSqlDao update(String columnName, Object value) {
		if (operationType != OperationType.UPDATE)
			throw new IllegalStateException("当前不是UPDATE模式,请调用changeMode进入INSERT模式!");
		top.starrysea.sql.UpdateSqlGenerator.Builder updateBuilder = (top.starrysea.sql.UpdateSqlGenerator.Builder) builder;
		updateBuilder.update(columnName, value);
		return this;
	}

	@Override
	public <T> SqlResult end(RowMapper<? extends Entity> rowMapper) {
		if (operationType != OperationType.SELECT)
			throw new UnsupportedOperationException("该end方法仅支持SELECT模式,增删改请使用无参数版本的end方法");
		QuerySqlGenerator querySqlGenerator = (QuerySqlGenerator) builder.build();
		SqlWithParams sqlWithParams = querySqlGenerator.generate();
		if (querySqlGenerator.getSelectClauses().size() > 1) {
			List<? extends Entity> result = template.query(sqlWithParams.getSql(), sqlWithParams.getParams(),
					rowMapper);
			return new ListSqlResult(result);
		} else if (querySqlGenerator.getSelectClauses().size() == 1) {
			SelectClause firstSelectClause = querySqlGenerator.getSelectClauses().get(0);
			if (firstSelectClause == SelectClause.COUNT || firstSelectClause == SelectClause.MAX
					|| firstSelectClause == SelectClause.MIN) {
				int result = template.queryForObject(sqlWithParams.getSql(), sqlWithParams.getParams(), Integer.class);
				return new IntegerSqlResult(result);
			}
			Entity result = template.queryForObject(sqlWithParams.getSql(), sqlWithParams.getParams(), rowMapper);
			return new EntitySqlResult(result);
		}
		throw new UnsupportedOperationException("当前的数据类型无法支持!");
	}

	@Override
	public <T> SqlResult end() {
		if (operationType == OperationType.SELECT)
			throw new UnsupportedOperationException("该end方法不支持SELECT模式");
		SqlWithParams sqlWithParams = builder.build().generate();
		template.update(sqlWithParams.getSql(), sqlWithParams.getParams());
		return new SqlResult(true);
	}
}
