package top.starrysea.kql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import top.starrysea.kql.clause.WhereClause;
import top.starrysea.kql.clause.WhereType;
import top.starrysea.kql.entity.Entity;
import top.starrysea.kql.entity.IBuilder;
import top.starrysea.kql.handler.WhereHandlers;
import top.starrysea.kql.handler.IWhereHandler;

public class DeleteSqlGenerator extends NonQuerySqlGenerator {

	private Class<? extends Entity> table;
	private List<WhereClause> whereClauses;
	private static Map<WhereType, IWhereHandler> handlerMap = new HashMap<>();

	static {
		handlerMap.put(WhereType.EQUALS, WhereHandlers.equalsHandler);
		handlerMap.put(WhereType.FRONT_FUZZY, WhereHandlers.frontFuzzyHandler);
		handlerMap.put(WhereType.BACK_FUZZY, WhereHandlers.backFuzzyHandler);
		handlerMap.put(WhereType.FUZZY, WhereHandlers.fuzzyHandler);
		handlerMap.put(WhereType.GREATER, WhereHandlers.greaterHandler);
		handlerMap.put(WhereType.GREATER_EQUAL, WhereHandlers.greaterEqualHandler);
		handlerMap.put(WhereType.LESS, WhereHandlers.lessHandler);
		handlerMap.put(WhereType.LESS_EQUAL, WhereHandlers.lessEqualHandler);
	}

	private DeleteSqlGenerator(Builder builder) {
		this.table = builder.table;
		this.whereClauses = builder.whereClauses;
	}

	public static class Builder implements IBuilder<DeleteSqlGenerator> {

		private Class<? extends Entity> table;
		private List<WhereClause> whereClauses;

		public Builder() {
			whereClauses = new ArrayList<>();
		}

		public Builder table(Class<? extends Entity> table) {
			this.table = table;
			return this;
		}

		public Builder where(String columnName, WhereType whereType, Object value) {
			WhereClause updateWhereClause = WhereClause.of(columnName, whereType, value);
			whereClauses.add(updateWhereClause);
			return this;
		}

		@Override
		public DeleteSqlGenerator build() {
			if (table == null)
				throw new IllegalArgumentException("当前没有设置要操作哪张表!");
			if (whereClauses.size() == 0) {
				System.err.println("生成的delete语句没有where条件,将会删掉整张表");
			}
			return new DeleteSqlGenerator(this);
		}
	}

	public Class<? extends Entity> getTable() {
		return table;
	}

	public List<WhereClause> getWhereClauses() {
		return whereClauses;
	}

	@Override
	protected ISqlGenerator getType() {
		return this;
	}

}
