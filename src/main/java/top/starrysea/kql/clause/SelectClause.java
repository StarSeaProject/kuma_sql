package top.starrysea.kql.clause;

public class SelectClause {

	private String colunmName;
	public final static SelectClause COUNT = SelectClause.of("COUNT(*)");
	public final static SelectClause MAX = SelectClause.of("MAX(*)");
	public final static SelectClause MIN = SelectClause.of("MIN(*)");

	public static SelectClause of(String colunmName) {
		SelectClause selectClause = new SelectClause();
		selectClause.setColunmName(colunmName);
		return selectClause;
	}

	public static SelectClause of(String colunmName, String alias) {
		SelectClause selectClause = new SelectClause();
		selectClause.setColunmName(alias + "." + colunmName);
		return selectClause;
	}

	public String getColunmName() {
		return colunmName;
	}

	private void setColunmName(String colunmName) {
		this.colunmName = colunmName;
	}
}
