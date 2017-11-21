package top.starrysea;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import top.starrysea.KumaSqlApplication;
import top.starrysea.facede.KumaSqlDao;
import top.starrysea.facede.ListSqlResult;
import top.starrysea.facede.OperationType;
import top.starrysea.facede.SqlResult;
import top.starrysea.sql.clause.WhereType;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KumaSqlApplication.class)
public class KumaSqlApplicationTests {

	@Autowired
	private KumaSqlDao kumaSqlDao;

	@Test
	public void selectTest() {
		Work work = new Work.Builder().workName("a").build();
		ListSqlResult result = (ListSqlResult) kumaSqlDao.select("work_id").select("work_name").from(Work.class)
				.where("work_name", WhereType.FUZZY, work.getWorkName()).orderBy("work_uploadtime").limit(0, 10)
				.end((rs, row) -> new Work.Builder().workId(rs.getInt("work_id")).workName("work_name").build());
		System.out.println(result.getResult());
	}
	
	@Test
	public void insertTest() {
		kumaSqlDao.changeMode(OperationType.INSERT);
		Work work = new Work.Builder().workName("a").workUploadTime("2017-08-09").workPdfpath("asdasjdaslkd/asdasd").workStock(100).build();
		SqlResult result = kumaSqlDao.insert("work_name", work.getWorkName()).insert("work_uploadtime", work.getWorkUploadTime()).insert("work_pdfpath", work.getWorkPdfpath()).insert("work_stock", work.getWorkStock()).table(Work.class).end(null);
		System.out.println(result.isSuccessed());
	}
	
	@Test
	public void updateTest() {
		kumaSqlDao.changeMode(OperationType.UPDATE);
		Work work = new Work.Builder().workId(1).workStock(1).build();
		SqlResult result = kumaSqlDao.update("work_stock", work.getWorkStock()).where("work_id", WhereType.EQUALS, work.getWorkId()).table(Work.class).end();
		System.out.println(result.isSuccessed());
	}

	@Test
	public void deleteTest() {
		kumaSqlDao.changeMode(OperationType.DELETE);
		Work work = new Work.Builder().workId(7).build();
		SqlResult result = kumaSqlDao.table(Work.class).where("work_id", WhereType.EQUALS, work.getWorkId()).end();
		System.out.println(result.isSuccessed());
	}
}
