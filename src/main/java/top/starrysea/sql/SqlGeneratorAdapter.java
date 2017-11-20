package top.starrysea.sql;

import java.util.List;

import top.starrysea.entity.Entity;
import top.starrysea.sql.clause.InsertClause;
import top.starrysea.sql.clause.JoinClause;
import top.starrysea.sql.clause.LimitClause;
import top.starrysea.sql.clause.OrderClause;
import top.starrysea.sql.clause.SelectClause;
import top.starrysea.sql.clause.TableClause;
import top.starrysea.sql.clause.UpdateSetClause;
import top.starrysea.sql.clause.WhereClause;

public class SqlGeneratorAdapter {

	private ISqlGenerator sqlGenerator;

	public SqlGeneratorAdapter(ISqlGenerator sqlGenerator) {
		this.sqlGenerator = sqlGenerator;
	}

	public List<SelectClause> getSelectClauses() {
		if (!(sqlGenerator instanceof QuerySqlGenerator))
			throw new UnsupportedOperationException(
					"getSelectClauses仅支持QuerySqlGenerator类,目前类是" + sqlGenerator.getClass().getName());
		QuerySqlGenerator temp = (QuerySqlGenerator) sqlGenerator;
		return temp.getSelectClauses();
	}

	public List<TableClause> getTableClauses() {
		if (!(sqlGenerator instanceof QuerySqlGenerator))
			throw new UnsupportedOperationException(
					"getTableClauses仅支持QuerySqlGenerator类,目前类是" + sqlGenerator.getClass().getName());
		QuerySqlGenerator temp = (QuerySqlGenerator) sqlGenerator;
		return temp.getTableClauses();
	}

	public List<WhereClause> getWhereClauses() {
		if (!(sqlGenerator instanceof QuerySqlGenerator || sqlGenerator instanceof UpdateSqlGenerator || sqlGenerator instanceof DeleteSqlGenerator))
			throw new UnsupportedOperationException("getWhereClauses仅支持QuerySqlGenerator类、UpdateSqlGenerator类和DeleteSqlGenerator类,目前类是"
					+ sqlGenerator.getClass().getName());
		Class<?> clazz = sqlGenerator.getClass();
		if (clazz == QuerySqlGenerator.class) {
			QuerySqlGenerator temp = (QuerySqlGenerator) sqlGenerator;
			return temp.getWhereClauses();
		} else if (clazz == UpdateSqlGenerator.class) {
			UpdateSqlGenerator temp = (UpdateSqlGenerator) sqlGenerator;
			return temp.getWhereClauses();
		} else if (clazz == DeleteSqlGenerator.class) {
			DeleteSqlGenerator temp = (DeleteSqlGenerator) sqlGenerator;
			return temp.getWhereClauses();
		} else
			return null;
	}

	public List<OrderClause> getOrderByClauses() {
		if (!(sqlGenerator instanceof QuerySqlGenerator))
			throw new UnsupportedOperationException(
					"getOrderByClauses仅支持QuerySqlGenerator类,目前类是" + sqlGenerator.getClass().getName());
		QuerySqlGenerator temp = (QuerySqlGenerator) sqlGenerator;
		return temp.getOrderByClauses();
	}

	public LimitClause getLimitClause() {
		if (!(sqlGenerator instanceof QuerySqlGenerator))
			throw new UnsupportedOperationException(
					"getLimitClause仅支持QuerySqlGenerator类,目前类是" + sqlGenerator.getClass().getName());
		QuerySqlGenerator temp = (QuerySqlGenerator) sqlGenerator;
		return temp.getLimitClause();
	}
	
	public List<JoinClause> getJoinClauses() {
		if (!(sqlGenerator instanceof QuerySqlGenerator))
			throw new UnsupportedOperationException(
					"getJoinClauses仅支持QuerySqlGenerator类,目前类是" + sqlGenerator.getClass().getName());
		QuerySqlGenerator temp = (QuerySqlGenerator) sqlGenerator;
		return temp.getJoinClauses();
	}
	
	public String getTableAlias(Class<? extends Entity> table) {
		if (!(sqlGenerator instanceof QuerySqlGenerator))
			throw new UnsupportedOperationException(
					"getTableAlias仅支持QuerySqlGenerator类,目前类是" + sqlGenerator.getClass().getName());
		QuerySqlGenerator temp = (QuerySqlGenerator) sqlGenerator;
		return temp.getTableAlias(table);
	}

	public List<InsertClause> getInsertClauses() {
		if (!(sqlGenerator instanceof InsertSqlGenerator))
			throw new UnsupportedOperationException(
					"getInsertClauses仅支持InsertSqlGenerator类,目前类是" + sqlGenerator.getClass().getName());
		InsertSqlGenerator temp = (InsertSqlGenerator) sqlGenerator;
		return temp.getInsertClauses();
	}

	public Class<? extends Entity> getTable() {
		if (sqlGenerator instanceof QuerySqlGenerator)
			throw new UnsupportedOperationException("getTable不支持QuerySqlGenerator类");
		Class<?> clazz = sqlGenerator.getClass();
		if (clazz == InsertSqlGenerator.class) {
			InsertSqlGenerator temp = (InsertSqlGenerator) sqlGenerator;
			return temp.getTable();
		} else if (clazz == UpdateSqlGenerator.class) {
			UpdateSqlGenerator temp = (UpdateSqlGenerator) sqlGenerator;
			return temp.getTable();
		} else if (clazz == DeleteSqlGenerator.class) {
			DeleteSqlGenerator temp = (DeleteSqlGenerator) sqlGenerator;
			return temp.getTable();
		} else
			return null;
	}

	public List<UpdateSetClause> getUpdateSetClauses() {
		if (!(sqlGenerator instanceof UpdateSqlGenerator))
			throw new UnsupportedOperationException(
					"getTable仅支持UpdateSqlGenerator类目前类是" + sqlGenerator.getClass().getName());
		UpdateSqlGenerator temp = (UpdateSqlGenerator) sqlGenerator;
		return temp.getUpdateSetClauses();
	}
}
