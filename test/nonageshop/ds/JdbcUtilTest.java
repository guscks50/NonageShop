package nonageshop.ds;

import java.sql.Connection;

import javax.swing.JButton;

import org.junit.Test;



import junit.framework.Assert;

public class JdbcUtilTest {

	@Test
	public void testGetConnection() {
	System.out.println("testGetConnection()");
	Connection con = JdbcUtil.getConnection();
	Assert.assertNotNull(con);
	System.out.println("con >"+con);
	}

}
