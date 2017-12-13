package top.starrysea.kql.facede;

import java.util.List;

import top.starrysea.kql.entity.Entity;

public class ListSqlResult extends SqlResult {

	private List<Entity> result;

	public <T> ListSqlResult(List<Entity> result) {
		super(true);
		this.result = result;
	}

	public List<Entity> getResult() {
		return result;
	}

}
