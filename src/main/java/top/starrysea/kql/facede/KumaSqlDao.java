package top.starrysea.kql.facede;

import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import top.starrysea.kql.clause.OrderByType;
import top.starrysea.kql.clause.SelectClause;
import top.starrysea.kql.clause.UpdateSetType;
import top.starrysea.kql.clause.WhereType;
import top.starrysea.kql.entity.Entity;

public interface KumaSqlDao {

	void changeMode(OperationType operationType);

	KumaSqlDao select(SelectClause selectClause);

	KumaSqlDao select(String colunmName);

	KumaSqlDao select(String colunmName, String alias);

	KumaSqlDao from(Class<? extends Entity> table);

	KumaSqlDao from(Class<? extends Entity> table, String alias);

	KumaSqlDao where(String columnName, WhereType whereType, Object value);

	KumaSqlDao where(String columnName, String alias, WhereType whereType, Object value);

	KumaSqlDao orderBy(String columnName);

	KumaSqlDao orderBy(String columnName, OrderByType orderByType);

	KumaSqlDao orderBy(String columnName, String alias);

	KumaSqlDao orderBy(String columnName, String alias, OrderByType orderByType);

	KumaSqlDao limit(int start);

	KumaSqlDao limit(int start, int limit);

	KumaSqlDao innerjoin(Class<? extends Entity> target, String alias, String targetColumn,
			Class<? extends Entity> source, String sourceColumn);

	KumaSqlDao leftjoin(Class<? extends Entity> target, String alias, String targetColumn,
			Class<? extends Entity> source, String sourceColumn);

	KumaSqlDao rightjoin(Class<? extends Entity> target, String alias, String targetColumn,
			Class<? extends Entity> source, String sourceColumn);

	KumaSqlDao fulljoin(Class<? extends Entity> target, String alias, String targetColumn,
			Class<? extends Entity> source, String sourceColumn);
	
	KumaSqlDao crossjoin(Class<? extends Entity> target, String alias, String targetColumn,
			Class<? extends Entity> source, String sourceColumn);

	KumaSqlDao insert(String columnName);
	
	KumaSqlDao insert(String columnName, Object value);

	KumaSqlDao table(Class<? extends Entity> table);

	KumaSqlDao update(String columnName, UpdateSetType updateSetType, Object value);

	ListSqlResult endForList(RowMapper<Entity> rowMapper);

	IntegerSqlResult endForNumber();

	EntitySqlResult endForObject(RowMapper<Entity> rowMapper);

	SqlResult end();
	
	SqlResult batchEnd(BatchPreparedStatementSetter bpss);
}
