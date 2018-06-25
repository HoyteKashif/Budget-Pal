package com.TechTheEasyWay.bill.DB;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.TechTheEasyWay.bill.StaticHelpers.StaticDBHelper.BillApplicationDB;
import com.TechTheEasyWay.bill.data.model.LedgerEntryModel;

/**
 * @author WizardOfOz
 *
 *         CREATE TABLE ledger ( ledger_id INT NOT NULL AUTO_INCREMENT PRIMARY
 *         KEY, bill_id INT, amount_due DECIMAL (10,2), minimum_payment DECIMAL
 *         (10,2), due_date DATE, date_paid DATE );
 */
public final class LedgerDB {

	public enum LedgerColumnEnum {
		LEDGER_ENTRY_ID("ledger_id"), BILL_ID("bill_id"), AMOUNT_DUE("amount_due"), MINIMUM_PAYMENT(
				"minimum_payment"), DUE_DATE("due_date"), DATE_PAID("date_paid");

		private final String strColumnName;

		private LedgerColumnEnum(final String p_strColumnName) {
			this.strColumnName = p_strColumnName;
		}

		public String getColumnName() {
			return strColumnName;
		}
	}

	private static ResultSet oResultSet;
	private static PreparedStatement oPreparedStatement;
	private static Statement oStatement;

	private LedgerDB() {
	}

	public static void delete(final LedgerEntryModel p_oLedgerEntryModel) {
		Objects.requireNonNull(p_oLedgerEntryModel);

		try (Connection oConnection = BillApplicationDB.getConnection()) {
			final PreparedStatement statement = oConnection.prepareStatement("delete from ledger where ledger_id = ?");
			statement.setInt(1, p_oLedgerEntryModel.getLedgerEntryId());
			statement.execute();
		} catch (final SQLException e) {
			e.printStackTrace();
		} finally {
			databaseFinally();
		}
	}

	public static void insert(final LedgerEntryModel p_oLedgerEntryModel) {
		Objects.requireNonNull(p_oLedgerEntryModel);
		Objects.requireNonNull(p_oLedgerEntryModel.getBillModel());

		insert(p_oLedgerEntryModel.getBillModel().getBillId(), p_oLedgerEntryModel.getAmountDue(),
				p_oLedgerEntryModel.getMinimumPayment(), p_oLedgerEntryModel.getDueDate(),
				p_oLedgerEntryModel.getDatePaid());
	}

	public static void insert(final int p_iBillId, final BigDecimal p_lAmountDue, final BigDecimal p_lMinimumPayment,
			final Date p_dtDueDate, final Date p_dtDatePaid) {

		try (Connection oConnection = BillApplicationDB.getConnection()) {

			PreparedStatement oStatement = oConnection
					.prepareStatement("INSERT INTO ledger (bill_id, amount_due, minimum_payment, due_date, date_paid)"
							+ " VALUES( ?, ?, ?, ?, ? )");
			oStatement.setInt(1, p_iBillId);
			oStatement.setBigDecimal(2, p_lAmountDue);
			oStatement.setBigDecimal(3, p_lMinimumPayment);
			oStatement.setDate(4, p_dtDueDate);
			oStatement.setDate(5, p_dtDatePaid);
			oStatement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			databaseFinally();
		}
	}

	public static List<LedgerEntryModel> getAllLedgerModels() {
		try (Connection oConnection = BillApplicationDB.getConnection()) {

			final Statement stmt = oConnection.createStatement();
			final String strQuery = "select ledger.ledger_id, ledger.bill_id, ledger.amount_due, ledger.minimum_payment, ledger.due_date, ledger.date_paid from ledger";

			oResultSet = stmt.executeQuery(strQuery);

			final List<LedgerEntryModel> lstLedgerEntries = new ArrayList<>();

			while (oResultSet.next()) {
				final LedgerEntryModel oEntryModel = new LedgerEntryModel();
				oEntryModel.setLedgerEntryId(getLedgerEntryId());
				oEntryModel.setAmountDue(getAmountDue());
				oEntryModel.setMinimumPayment(getMinimumPayment());
				oEntryModel.setDueDate(getDueDate());
				oEntryModel.setDatePaid(getDatePaid());
				oEntryModel.setBillModel(BillDB.getBillModelById(getBillId()));

				lstLedgerEntries.add(oEntryModel);
			}
			oResultSet.close();
			stmt.close();

			return lstLedgerEntries;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			databaseFinally();
		}

		return Collections.emptyList();
	}

	private static int getLedgerEntryId() throws SQLException {
		return oResultSet.getInt(LedgerColumnEnum.LEDGER_ENTRY_ID.getColumnName());
	}

	private static int getBillId() throws SQLException {
		return oResultSet.getInt(LedgerColumnEnum.BILL_ID.getColumnName());
	}

	private static BigDecimal getAmountDue() throws SQLException {
		return oResultSet.getBigDecimal(LedgerColumnEnum.AMOUNT_DUE.getColumnName());
	}

	private static BigDecimal getMinimumPayment() throws SQLException {
		return oResultSet.getBigDecimal(LedgerColumnEnum.MINIMUM_PAYMENT.getColumnName());
	}

	private static Date getDueDate() throws SQLException {
		return oResultSet.getDate(LedgerColumnEnum.DUE_DATE.getColumnName());
	}

	private static Date getDatePaid() throws SQLException {
		return oResultSet.getDate(LedgerColumnEnum.DATE_PAID.getColumnName());
	}

	private static void databaseFinally() {
		try {
			if (null != oResultSet) {
				oResultSet.close();
			}

			if (null != oStatement) {
				oStatement.close();
			}

			if (null != oPreparedStatement) {
				oPreparedStatement.close();
			}
		} catch (final SQLException e) {
			e.printStackTrace();
		}
	}
}
