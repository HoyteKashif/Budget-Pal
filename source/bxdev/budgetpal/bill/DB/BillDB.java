package bxdev.budgetpal.bill.DB;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import bxdev.budgetpal.bill.StaticHelpers.ModelLoader;
import bxdev.budgetpal.bill.StaticHelpers.MySqlHelper;
import bxdev.budgetpal.bill.data.model.Bill;

public final class BillDB {

	/**
	 * CREATE TABLE bill_application.bill ( bill_id INT NOT NULL AUTO_INCREMENT
	 * PRIMARY KEY, bill_name VARCHAR(20), day_due INT, amount DECIMAL(10,2),
	 * balance DECIMAL(10,2) );
	 **/
	private static ResultSet oResultSet;

	public enum BillColumnEnum {
		BILL_ID("bill_id"), BILL_NAME("bill_name"), DAY_DUE("day_due"), AMOUNT("amount"), BALANCE("balance");

		private String strName;

		private BillColumnEnum(final String p_strName) {
			strName = p_strName;
		}

		public String getName() {
			return strName;
		}
	}

	/**
	 * Default Constructor
	 */
	public BillDB() {
	}

	public static void insertIntoBillTableDB(final BigDecimal p_lAmountDue, final BigDecimal p_lMinimumPayment,
			final java.sql.Date p_dtDueDate, final java.sql.Date p_dtDatePaid) {
		try (Connection con = MySqlHelper.getConnection()) {

			java.sql.PreparedStatement oStatement = con.prepareStatement(
					"INSERT INTO ledger ( amount_due, minimum_payment, due_date, date_paid)" + " VALUES( ?, ?, ?, ? )");
			oStatement.setBigDecimal(1, p_lAmountDue);
			oStatement.setBigDecimal(2, p_lMinimumPayment);
			oStatement.setDate(3, p_dtDueDate);
			oStatement.setDate(4, p_dtDatePaid);
			oStatement.execute();

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public static ResultSet getAll() {
		try (Connection con = MySqlHelper.getConnection()) {

			return con.createStatement().executeQuery("select * from bill");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<String> getAllBillNames() {
		try (Connection con = MySqlHelper.getConnection()) {
			List<String> lstBillNames = new ArrayList<>();

			ResultSet oResults = con.createStatement().executeQuery("select distinct bill.bill_name from bill");
			while (oResults.next()) {
				lstBillNames.add(oResults.getString(BillColumnEnum.BILL_NAME.getName()));
			}
			return lstBillNames;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	public static List<Bill> getAllBillModels() {
		try (Connection con = MySqlHelper.getConnection()) {
			final List<Bill> lstModels = new ArrayList<>();

			oResultSet = con.createStatement().executeQuery("select * from bill");

			while (oResultSet.next()) {
				lstModels.add(
						ModelLoader.loadBillModel(getBillId(), getBillName(), getAmount(), getDayDue(), getBalance()));
			}
			return lstModels;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	public static Bill getBillModelById(final int p_iBillId) throws SQLException {
		try (Connection con = MySqlHelper.getConnection()) {
			java.sql.PreparedStatement oStatement = con.prepareStatement(
					"SELECT bill.bill_id, bill.bill_name, bill.day_due, bill.amount, bill.balance FROM bill WHERE bill.bill_id = ?");
			oStatement.setInt(1, p_iBillId);
			oResultSet = oStatement.executeQuery();

			while (oResultSet.next()) {
				return ModelLoader.loadBillModel(getBillId(), getBillName(), getAmount(), getDayDue(), getBalance());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (Objects.nonNull(oResultSet)) {
				oResultSet.close();
			}
		}
		return null;
	}

	private static int getBillId() throws SQLException {
		return oResultSet.getInt(BillColumnEnum.BILL_ID.getName());
	}

	private static String getBillName() throws SQLException {
		return oResultSet.getString(BillColumnEnum.BILL_NAME.getName());
	}

	private static int getDayDue() throws SQLException {
		return oResultSet.getInt(BillColumnEnum.DAY_DUE.getName());
	}

	private static BigDecimal getAmount() throws SQLException {
		return oResultSet.getBigDecimal(BillColumnEnum.AMOUNT.getName());
	}

	private static BigDecimal getBalance() throws SQLException {
		return oResultSet.getBigDecimal(BillColumnEnum.BALANCE.getName());
	}
}
