package top.starrysea.kql.handler;

import java.util.List;

import top.starrysea.kql.clause.UpdateSetClause;

public interface IUpdateSetHandler {

	HandleResult handleUpdateBuffer(UpdateSetClause updateSetClause, StringBuilder updateBuilder, List<Object> params);
}
