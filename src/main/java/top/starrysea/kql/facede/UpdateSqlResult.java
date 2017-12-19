package top.starrysea.kql.facede;

public class UpdateSqlResult extends SqlResult {

	private Object pk;

	public UpdateSqlResult(Object pk) {
		super(true);
		this.pk = pk;
	}

	public Object getPk() {
		return pk;
	}

	public void setPk(Object pk) {
		this.pk = pk;
	}
}
