package top.starrysea.sql.handler;

import java.util.List;

public class HandleResult {

	private StringBuilder whereBuffer;
	private List<Object> preParams;

	public HandleResult(StringBuilder whereBuffer, List<Object> preParams) {
		super();
		this.whereBuffer = whereBuffer;
		this.preParams = preParams;
	}

	public StringBuilder getWhereBuffer() {
		return whereBuffer;
	}

	public List<Object> getPreParams() {
		return preParams;
	}

}
