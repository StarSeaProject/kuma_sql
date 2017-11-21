package top.starrysea.kql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import top.starrysea.kql.clause.UpdateSetClause;
import top.starrysea.kql.clause.WhereClause;
import top.starrysea.kql.clause.WhereType;
import top.starrysea.kql.entity.Entity;
import top.starrysea.kql.entity.IBuilder;
import top.starrysea.kql.handler.Handlers;
import top.starrysea.kql.handler.IWhereHandler;

public class UpdateSqlGenerator extends NonQuerySqlGenerator {

	private Class<? extends Entity> table;
	private List<UpdateSetClause> updateSetClauses;
	private List<WhereClause> whereClauses;
	private static Map<WhereType, IWhereHandler> handlerMap = new HashMap<>();

	static {
		handlerMap.put(WhereType.EQUALS, Handlers.equalsHandler);
		handlerMap.put(WhereType.FRONT_FUZZY, Handlers.frontFuzzyHandler);
		handlerMap.put(WhereType.BACK_FUZZY, Handlers.backFuzzyHandler);
		handlerMap.put(WhereType.FUZZY, Handlers.fuzzyHandler);
		handlerMap.put(WhereType.GREATER, Handlers.greaterHandler);
		handlerMap.put(WhereType.GREATER_EQUAL, Handlers.greaterEqualHandler);
		handlerMap.put(WhereType.LESS, Handlers.lessHandler);
		handlerMap.put(WhereType.LESS_EQUAL, Handlers.lessEqualHandler);
	}

	private UpdateSqlGenerator(Builder builder) {
		this.table = builder.table;
		this.updateSetClauses = builder.updateSetClauses;
		this.whereClauses = builder.whereClauses;
	}

	public static class Builder implements IBuilder<UpdateSqlGenerator> {

		private Class<? extends Entity> table;
		private List<UpdateSetClause> updateSetClauses;
		private List<WhereClause> whereClauses;

		public Builder() {
			updateSetClauses = new ArrayList<>();
			whereClauses = new ArrayList<>();
		}

		public Builder table(Class<? extends Entity> table) {
			this.table = table;
			return this;
		}

		public Builder update(String columnName, Object value) {
			UpdateSetClause updateSetClause = UpdateSetClause.of(columnName, value);
			updateSetClauses.add(updateSetClause);
			return this;
		}

		public Builder where(String columnName, WhereType whereType, Object value) {
			WhereClause updateWhereClause = WhereClause.of(columnName, whereType, value);
			whereClauses.add(updateWhereClause);
			return this;
		}

		@Override
		public UpdateSqlGenerator build() {
			if (table == null)
				throw new IllegalArgumentException("操作的表不能为空!");
			return new UpdateSqlGenerator(this);
		}

	}

	public Class<? extends Entity> getTable() {
		return table;
	}

	public List<UpdateSetClause> getUpdateSetClauses() {
		return updateSetClauses;
	}

	public List<WhereClause> getWhereClauses() {
		return whereClauses;
	}

	public static Map<WhereType, IWhereHandler> getHandlerMap() {
		return handlerMap;
	}

	@Override
	protected ISqlGenerator getType() {
		return this;
	}

}
