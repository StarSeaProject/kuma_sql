package top.starrysea.kql.facede;

import top.starrysea.kql.entity.Entity;

public class EntitySqlResult extends SqlResult {

	private Entity result;

	public EntitySqlResult(Entity result) {
		super(true);
		this.result = result;
	}

	public Entity getResult() {
		return result;
	}

	public void setResult(Entity result) {
		this.result = result;
	}

}
