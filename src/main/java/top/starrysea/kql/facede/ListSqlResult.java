package top.starrysea.kql.facede;

import java.util.List;

import top.starrysea.kql.entity.Entity;

public class ListSqlResult extends SqlResult {

	private List<? extends Entity> result;

	public <T> ListSqlResult(List<? extends Entity> result) {
		super(true);
		this.result = result;
	}

	public List<? extends Entity> getResult() {
		return result;
	}

}
