package top.starrysea.facede;

import java.util.List;

import top.starrysea.entity.Entity;

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
