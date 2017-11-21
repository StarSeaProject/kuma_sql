package top.starrysea.sql.clause;

public enum OrderByType {

	ASC("ASC"), DESC("DESC");

	private String orderByType;

	private OrderByType(String orderByType) {
		this.orderByType = orderByType;
	}

	public String getType() {
		return this.orderByType;
	}
}
