package com.TechTheEasyWay.bill.DB;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.TechTheEasyWay.bill.StaticHelpers.StaticDBHelper.BillApplicationDB;
import com.TechTheEasyWay.bill.data.model.LedgerEntryModel;

/**
 * @author WizardOfOz
 *
 *	CREATE TABLE ledger
 *	(
 *		ledger_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 *		bill_id INT,
 * 		amount_due DECIMAL (10,2),
 *		minimum_payment DECIMAL (10,2),
 *		due_date DATE,
 *		date_paid DATE
 *	);
 */
public class LedgerDB {
	
	private static ResultSet oResultSet;
	
	public LedgerDB(){
		
	}

	public static void insert(final int p_iBillId, final BigDecimal p_lAmountDue, final BigDecimal p_lMinimumPayment, final java.sql.Date p_dtDueDate, final java.sql.Date p_dtDatePaid){

		try(Connection oConnection = BillApplicationDB.getConnection()){
			
			java.sql.PreparedStatement oStatement= oConnection.prepareStatement("INSERT INTO ledger ( amount_due, minimum_payment, due_date, date_paid)"
					+ " VALUES( ?, ?, ?, ? )");
			oStatement.setInt(1, p_iBillId);
			oStatement.setBigDecimal(1, p_lAmountDue);
			oStatement.setBigDecimal(2, p_lMinimumPayment);
			oStatement.setDate(3, p_dtDueDate);
			oStatement.setDate(4, p_dtDatePaid);
			oStatement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<LedgerEntryModel> getAllLedgerModels()
	{
		try( Connection oConnection = BillApplicationDB.getConnection()) {
			
			final Statement stmt = oConnection.createStatement();
			final String strQuery = "select bill.bill_name, ledger.amount_due, ledger.minimum_payment, ledger.due_date, ledger.date_paid from ledger "
					+ "join bill on bill.bill_id = ledger.bill_id";
			
			oResultSet = stmt.executeQuery(strQuery);
			
			final List<LedgerEntryModel> lstLedgerEntries = new ArrayList<>();
			
			while(oResultSet.next())
			{
				LedgerEntryModel oEntryModel = new LedgerEntryModel();
				oEntryModel.setStrBillName(getBillName());
				oEntryModel.setlAmountDue(oResultSet.getBigDecimal(2));
				oEntryModel.setlMinimumPayment(oResultSet.getBigDecimal(3));
				oEntryModel.setDtDuedate(oResultSet.getDate(4));
				oEntryModel.setDtDatePaid(oResultSet.getDate(5));
				lstLedgerEntries.add(oEntryModel);
				oResultSet.close();
			}
			return lstLedgerEntries;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static String getBillName()
	{
		try {
			return oResultSet.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
