package yi_java3st_2team.ds;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

public class LocalDataSourceTest {

	@Test
	public void testGetConnection() {
		try {
			Connection con = LocalDataSource.getConnection();
			Assert.assertNotNull(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
