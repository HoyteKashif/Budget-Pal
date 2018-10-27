package bxdev.budgetpal.bill.DB;

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

import bxdev.budgetpal.bill.StaticHelpers.MySqlHelper;
import bxdev.budgetpal.bill.data.model.Ledger;

/**
 * @author WizardOfOz
 *
 *         CREATE TABLE ledger ( ledger_id INT NOT NULL AUTO_INCREMENT PRIMARY
 *         KEY, bill_id INT, amount_due DECIMAL (10,2), minimum_payment DECIMAL
 *         (10,2), due_date DATE, date_paid DATE );
 */
public final class LedgerDB {

	private static ResultSet oResultSet;
	private static PreparedStatement oPreparedStatement;
	private static Statement oStatement;

	public enum LedgerColumn {
		LEDGER_ENTRY_ID("ledger_id"), BILL_ID("bill_id"), AMOUNT_DUE("amount_due"), MINIMUM_PAYMENT(
				"minimum_payment"), DUE_DATE("due_date"), DATE_PAID("date_paid");

		private final String name;

		private LedgerColumn(final String p_strName) {
			this.name = p_strName;
		}

		public String getName() {
			return name;
		}
	}

	private LedgerDB() {
	}

	public static void delete(final Ledger p_oLedgerEntry) {
		Objects.requireNonNull(p_oLedgerEntry);

		try (Connection oConnection = MySqlHelper.getConnection()) {
			final PreparedStatement statement = oConnection.prepareStatement("delete from ledger where ledger_id = ?");
			statement.setInt(1, p_oLedgerEntry.getId());
			statement.execute();
		} catch (final SQLException e) {
			e.printStackTrace();
		} finally {
			databaseFinally();
		}
	}

	public static void insert(final Ledger p_oLedgerEntry) {
		Objects.requireNonNull(p_oLedgerEntry);
		Objects.requireNonNull(p_oLedgerEntry.getBill());

		insert(p_oLedgerEntry.getBill().getId(), p_oLedgerEntry.getAmountDue(), p_oLedgerEntry.getMinimumPayment(),
				p_oLedgerEntry.getDueDate(), p_oLedgerEntry.getDatePaid());
	}

	public static void insert(final int p_iBillId, final BigDecimal p_lAmountDue, final BigDecimal p_lMinimumPayment,
			final Date p_dtDueDate, final Date p_dtDatePaid) {

		try (Connection oConnection = MySqlHelper.getConnection()) {

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

	public static List<Ledger> getAllLedgerModels() {
		try (Connection oConnection = MySqlHelper.getConnection()) {

			final Statement stmt = oConnection.createStatement();
			final String strQuery = "select ledger.ledger_id, ledger.bill_id, ledger.amount_due, ledger.minimum_payment, ledger.due_date, ledger.date_paid from ledger";

			oResultSet = stmt.executeQuery(strQuery);

			final List<Ledger> ledgerEntries = new ArrayList<>();

			while (oResultSet.next()) {
				final Ledger oLedgerEntry = new Ledger();
				oLedgerEntry.setLedgerEntryId(getLedgerEntryId());
				oLedgerEntry.setAmountDue(getAmountDue());
				oLedgerEntry.setMinimumPayment(getMinimumPayment());
				oLedgerEntry.setDueDate(getDueDate());
				oLedgerEntry.setDatePaid(getDatePaid());
				oLedgerEntry.setBill(BillDB.getBillModelById(getBillId()));

				ledgerEntries.add(oLedgerEntry);
			}

			return ledgerEntries;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			databaseFinally();
		}

		return Collections.emptyList();
	}

	private static int getLedgerEntryId() throws SQLException {
		return oResultSet.getInt(LedgerColumn.LEDGER_ENTRY_ID.getName());
	}

	private static int getBillId() throws SQLException {
		return oResultSet.getInt(LedgerColumn.BILL_ID.getName());
	}

	private static BigDecimal getAmountDue() throws SQLException {
		return oResultSet.getBigDecimal(LedgerColumn.AMOUNT_DUE.getName());
	}

	private static BigDecimal getMinimumPayment() throws SQLException {
		return oResultSet.getBigDecimal(LedgerColumn.MINIMUM_PAYMENT.getName());
	}

	private static Date getDueDate() throws SQLException {
		return oResultSet.getDate(LedgerColumn.DUE_DATE.getName());
	}

	private static Date getDatePaid() throws SQLException {
		return oResultSet.getDate(LedgerColumn.DATE_PAID.getName());
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
