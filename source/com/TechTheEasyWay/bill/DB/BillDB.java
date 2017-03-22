package com.TechTheEasyWay.bill.DB;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.TechTheEasyWay.bill.StaticHelpers.ModelLoader;
import com.TechTheEasyWay.bill.StaticHelpers.StaticDBHelper.BillApplicationDB;
import com.TechTheEasyWay.bill.data.model.BillModel;

public class BillDB {

	private static ResultSet oResultSet;
	
	public enum BillColumnEnum{
		BILL_NAME("bill_name"),
		DAY_DUE("day_due"),
		AMOUNT("amount"),
		BALANCE("balance");
		
		private String strName;
		private BillColumnEnum( final String p_strName){
			strName = p_strName;
		}
		public String getName(){
			return strName;
		}
	}
	
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
	
	public static List<String> getAllBillNames()
	{
		try(Connection con = BillApplicationDB.getConnection()) {
			List<String> lstBillNames = new ArrayList<>();
			
			ResultSet oResults = con.createStatement().executeQuery("select distinct bill.bill_name from bill");
			while(oResults.next()){
				lstBillNames.add(oResults.getString(BillColumnEnum.BILL_NAME.getName()));
			}
			return lstBillNames;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	
	public static List<BillModel> getAllBillModels()
	{
		try{
			List<BillModel> lstModels = new ArrayList<>();
			for(ResultSet rs = getAll(); rs.next(); )
			{
				lstModels.add(ModelLoader.loadBillModel(rs));
			}
			return lstModels;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	
	public static String getBillNameById( final int p_iBillId)
	{
		try (Connection con = BillApplicationDB.getConnection()){
			java.sql.PreparedStatement oStatement = con.prepareStatement("SELECT bill_name FROM bill WHERE bill.bill_id = ?");
			oStatement.setInt(1, p_iBillId);
			oResultSet = oStatement.executeQuery();
			return oResultSet.getString(BillColumnEnum.BILL_NAME.getName());
		} catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
}
