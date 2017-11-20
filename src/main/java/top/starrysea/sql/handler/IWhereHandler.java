package top.starrysea.sql.handler;

import java.util.List;

import top.starrysea.sql.clause.WhereClause;

@FunctionalInterface
public interface IWhereHandler {

	HandleResult handleWhereBuffer(WhereClause where, StringBuilder whereBuffer, List<Object> preParams);
}
