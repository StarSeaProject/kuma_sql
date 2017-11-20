package top.starrysea.sql.clause;

public class UpdateSetClause {

	private String columnName;
	private Object value;
	
	public static UpdateSetClause of(String columnName,Object value) {
		UpdateSetClause updateSetClause=new UpdateSetClause();
		updateSetClause.setColumnName(columnName);
		updateSetClause.setValue(value);
		return updateSetClause;
	}

	public String getColumnName() {
		return columnName;
	}

	private void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Object getValue() {
		return value;
	}

	private void setValue(Object value) {
		this.value = value;
	}
	
}
