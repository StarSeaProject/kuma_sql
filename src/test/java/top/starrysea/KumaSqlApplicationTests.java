package top.starrysea;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.test.context.junit4.SpringRunner;

import top.starrysea.KumaSqlApplication;
import top.starrysea.kql.clause.UpdateSetType;
import top.starrysea.kql.clause.WhereType;
import top.starrysea.kql.facede.KumaSqlDao;
import top.starrysea.kql.facede.ListSqlResult;
import top.starrysea.kql.facede.OperationType;
import top.starrysea.kql.facede.SqlResult;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KumaSqlApplication.class)
public class KumaSqlApplicationTests {

	@Autowired
	private KumaSqlDao kumaSqlDao;

	@Test
	public void selectTest() {
		Work work = new Work.Builder().workName("a").build();
		ListSqlResult result = (ListSqlResult) kumaSqlDao.select("work_id").select("work_name").from(Work.class)
				.where("work_name", WhereType.FUZZY, work.getWorkName()).where("work_id", WhereType.EQUALS, 1)
				.orderBy("work_uploadtime").limit(0, 10)
				.endForList((rs, row) -> new Work.Builder().workId(rs.getInt("work_id")).workName("work_name").build());
		System.out.println(result.getResult());
	}

	@Test
	public void insertTest() {
		kumaSqlDao.insertMode();
		Work work = new Work.Builder().workName("a").workUploadTime("2017-08-09").workPdfpath("asdasjdaslkd/asdasd")
				.workStock(100).workCover("asdasd").workSummary("aaaaaaaaaaaaaa").build();
		SqlResult result = kumaSqlDao.insert("work_name", work.getWorkName())
				.insert("work_uploadtime", work.getWorkUploadTime()).insert("work_pdfpath", work.getWorkPdfpath())
				.insert("work_stock", work.getWorkStock()).insert("work_cover", work.getWorkCover())
				.insert("work_summary", work.getWorkSummary()).table(Work.class).end();
		System.out.println(result.isSuccessed());
	}

	@Test
	public void batchInsertTest() {
		kumaSqlDao.insertMode();
		List<Work> works = new ArrayList<>();
		Work work1 = new Work.Builder().workName("a").workUploadTime("2017-08-09").workPdfpath("asdasjdaslkd/asdasd")
				.workStock(100).workCover("asdasd").workSummary("aaaaaaaaaaaaaa").build();
		Work work2 = new Work.Builder().workName("a").workUploadTime("2017-08-09").workPdfpath("asdasjdaslkd/asdasd")
				.workStock(100).workCover("asdasd").workSummary("aaaaaaaaaaaaaa").build();
		Work work3 = new Work.Builder().workName("a").workUploadTime("2017-08-09").workPdfpath("asdasjdaslkd/asdasd")
				.workStock(100).workCover("asdasd").workSummary("aaaaaaaaaaaaaa").build();
		works.add(work1);
		works.add(work2);
		works.add(work3);
		SqlResult result = kumaSqlDao.insert("work_name").insert("work_uploadtime").insert("work_pdfpath")
				.insert("work_stock").insert("work_cover").insert("work_summary").table(Work.class)
				.batchEnd(new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setString(1, works.get(i).getWorkName());
						ps.setString(2, works.get(i).getWorkUploadTime());
						ps.setString(3, works.get(i).getWorkPdfpath());
						ps.setInt(4, works.get(i).getWorkStock());
						ps.setString(5, works.get(i).getWorkCover());
						ps.setString(6, works.get(i).getWorkSummary());
					}

					@Override
					public int getBatchSize() {
						return works.size();
					}
				});
		System.out.println(result.isSuccessed());
	}

	@Test
	public void updateTest() {
		kumaSqlDao.updateMode();
		Work work = new Work.Builder().workId(6).workStock(1).build();
		SqlResult result = kumaSqlDao.update("work_stock", UpdateSetType.REDUCE, work.getWorkStock())
				.update("work_pdfpath", UpdateSetType.ASSIGN, "/qwe")
				.where("work_id", WhereType.EQUALS, work.getWorkId()).where("work_name", WhereType.FUZZY, "aa")
				.table(Work.class).end();
		System.out.println(result.isSuccessed());
	}

	@Test
	public void deleteTest() {
		kumaSqlDao.deleteMode();
		Work work = new Work.Builder().workId(7).build();
		SqlResult result = kumaSqlDao.table(Work.class).where("work_name", WhereType.EQUALS, "aa")
				.where("work_id", WhereType.EQUALS, work.getWorkId()).end();
		System.out.println(result.isSuccessed());
	}
}
