package com.TechTheEasyWay.bill.StaticHelpers;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.TechTheEasyWay.bill.data.model.BillModel;
import com.TechTheEasyWay.bill.data.model.LedgerEntryModel;

public class ModelLoader {

	public static LedgerEntryModel loadLedgerModel(final ResultSet p_rsDataDB){
		try {
			return loadLedgerEntryModel( p_rsDataDB.getString(1),p_rsDataDB.getBigDecimal(2),p_rsDataDB.getBigDecimal(3),p_rsDataDB.getDate(4),p_rsDataDB.getDate(5));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static LedgerEntryModel loadLedgerEntryModel(final String p_strName, final BigDecimal p_lAmountDue, final BigDecimal p_lMinimumPayment, final Date p_dtDueDate, final Date p_dtDatePaid){
		LedgerEntryModel oEntryModel = new LedgerEntryModel();
		oEntryModel.setStrBillName(p_strName);
		oEntryModel.setlAmountDue(p_lAmountDue);
		oEntryModel.setlMinimumPayment(p_lMinimumPayment);
		oEntryModel.setDueDate(p_dtDueDate);
		oEntryModel.setDatePaid(p_dtDatePaid);
		return oEntryModel;
	}
	
	public static BillModel loadBillModel(final ResultSet p_rsDataDB){
		try {
			return loadBillModel(p_rsDataDB.getString(2), p_rsDataDB.getBigDecimal(4), p_rsDataDB.getInt(3));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static BillModel loadBillModel(final String p_strName, final BigDecimal p_lAmount, final int p_iDayDue)
	{
		BillModel oBillModel = new BillModel();
		oBillModel.setName(p_strName);
		oBillModel.setAmount(p_lAmount);
		oBillModel.setDueDate(p_iDayDue);		
		return oBillModel;
	}
}
