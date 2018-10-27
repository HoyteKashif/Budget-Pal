package bxdev.budgetpal.bill.StaticHelpers;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public final class MySqlHelper {
	private MySqlHelper() {
	}

	public static Connection getConnection() {
		Context ctx = null;
		DataSource ds = null;
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/bill_application");
			return ds.getConnection();
		} catch (final SQLException | NamingException e) {
			e.printStackTrace();
		} finally {
			if (null != ctx) {
				try {
					ctx.close();
				} catch (final NamingException e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}
}
