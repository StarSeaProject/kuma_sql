package top.starrysea.kql.clause;

public class WhereClause {

	private String columnName;
	private WhereType whereType;
	private Object value;

	private WhereClause() {
	}

	public static WhereClause of(String columnName, WhereType whereType, Object value) {
		WhereClause whereClause = new WhereClause();
		whereClause.setColumnName(columnName);
		whereClause.setWhereType(whereType);
		whereClause.setValue(value);
		return whereClause;
	}

	public static WhereClause of(String columnName, String alias, WhereType whereType, Object value) {
		WhereClause whereClause = new WhereClause();
		whereClause.setColumnName(alias + "." + columnName);
		whereClause.setWhereType(whereType);
		whereClause.setValue(value);
		return whereClause;
	}

	public String getColumnName() {
		return columnName;
	}

	private void setColumnName(String colunmName) {
		this.columnName = colunmName;
	}

	public WhereType getWhereType() {
		return whereType;
	}

	private void setWhereType(WhereType whereType) {
		this.whereType = whereType;
	}

	public Object getValue() {
		return value;
	}

	private void setValue(Object value) {
		this.value = value;
	}

}
