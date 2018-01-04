package top.starrysea.kql.facede;

import java.util.List;

public class ListSqlResult extends SqlResult {

	private List<?> result;

	public <T> ListSqlResult(List<?> result) {
		super(true);
		this.result = result;
	}

	public List<?> getResult() {
		return result;
	}

}
