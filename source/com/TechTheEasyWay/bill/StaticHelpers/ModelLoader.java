package com.TechTheEasyWay.bill.StaticHelpers;

import com.TechTheEasyWay.bill.data.model.BillModel;
import com.TechTheEasyWay.bill.data.model.LedgerEntryModel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModelLoader {

	public static void loadLedgerModel(final ResultSet p_rsDataDB){
		try {
			LedgerEntryModel oEntryModel = new LedgerEntryModel();
			oEntryModel.setStrBillName(p_rsDataDB.getString(1));
			oEntryModel.setlAmountDue(p_rsDataDB.getBigDecimal(2));
			oEntryModel.setlMinimumPayment(p_rsDataDB.getBigDecimal(3));
			oEntryModel.setDtDuedate(p_rsDataDB.getDate(4));
			oEntryModel.setDtDatePaid(p_rsDataDB.getDate(5));
			return;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadBillModel(final ResultSet p_rsDataDB){
		try {
			BillModel oBillModel = new BillModel();
			oBillModel.setName(p_rsDataDB.getString(2));
			oBillModel.setAmount(p_rsDataDB.getBigDecimal(4));
			oBillModel.setDueDate(p_rsDataDB.getInt(3));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
