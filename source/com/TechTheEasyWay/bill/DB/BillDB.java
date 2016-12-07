package com.TechTheEasyWay.bill.DB;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.TechTheEasyWay.bill.StaticHelpers.StaticDBHelper.BillApplicationDB;
import com.TechTheEasyWay.bill.data.model.BillModel;

public class BillDB {

	/**
	 * Default Constructor
	 */
	public BillDB(){
		
	}
	
	public static void insertIntoBillTableDB(final BigDecimal p_lAmountDue, final BigDecimal p_lMinimumPayment, final java.sql.Date p_dtDueDate, final java.sql.Date p_dtDatePaid) 
	{
		try(Connection con = BillApplicationDB.getConnection()) {
			
			java.sql.PreparedStatement oStatement= con.prepareStatement("INSERT INTO ledger ( amount_due, minimum_payment, due_date, date_paid)"
					+ " VALUES( ?, ?, ?, ? )");
			
			oStatement.setBigDecimal(1, p_lAmountDue);
			oStatement.setBigDecimal(2, p_lMinimumPayment);
			oStatement.setDate(3, p_dtDueDate);
			oStatement.setDate(4, p_dtDatePaid);
			oStatement.execute();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public static ResultSet getAll(){
		try(Connection con = BillApplicationDB.getConnection()) {
			
			return con.createStatement().executeQuery("select * from bill");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<BillModel> getAllBillModels()
	{
		try{
			for(ResultSet rs = getAll(); rs.next(); )
			{
				//TODO: load results into bill models
			}
			
			//TODO: return list of loaded models
			return Collections.EMPTY_LIST;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.EMPTY_LIST;
	}
	
}
