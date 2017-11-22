package top.starrysea.kql.handler;

import java.util.List;

public class HandleResult {

	private StringBuilder buffer;
	private List<Object> preParams;

	public HandleResult(StringBuilder whereBuffer, List<Object> preParams) {
		super();
		this.buffer = whereBuffer;
		this.preParams = preParams;
	}

	public StringBuilder getBuffer() {
		return buffer;
	}

	public List<Object> getPreParams() {
		return preParams;
	}

}
