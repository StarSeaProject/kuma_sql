package top.starrysea;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import top.starrysea.KumaSqlApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=KumaSqlApplication.class)
public class KumaSqlApplicationTests {

	@Autowired
	private JdbcTemplate template;
	
	@Test
	public void test() {
		System.out.println(template);
	}

}
