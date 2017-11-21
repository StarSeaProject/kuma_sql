package top.starrysea.facede;

import org.springframework.jdbc.core.RowMapper;

import top.starrysea.entity.Entity;
import top.starrysea.sql.clause.OrderByType;
import top.starrysea.sql.clause.WhereType;

public interface KumaSqlDao {

	void changeMode(OperationType operationType);

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

	KumaSqlDao insert(String columnName, Object value);

	KumaSqlDao table(Class<? extends Entity> table);

	KumaSqlDao update(String columnName, Object value);

	<T> SqlResult end(RowMapper<? extends Entity> rowMapper);

	<T> SqlResult end();
}
