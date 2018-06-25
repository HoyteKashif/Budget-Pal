package com.TechTheEasyWay.bill.StaticHelpers;

import java.math.BigDecimal;
import java.sql.Date;

import com.TechTheEasyWay.bill.data.model.BillModel;
import com.TechTheEasyWay.bill.data.model.LedgerEntryModel;

public final class ModelLoader {
	
	private ModelLoader(){}
	
	public static LedgerEntryModel loadLedgerEntryModel(final int p_iBillId, final BigDecimal p_lAmountDue, final BigDecimal p_lMinimumPayment, final Date p_dtDueDate, final Date p_dtDatePaid, final BillModel p_oBillModel){
		final LedgerEntryModel oEntryModel = new LedgerEntryModel();
		oEntryModel.setAmountDue(p_lAmountDue);
		oEntryModel.setMinimumPayment(p_lMinimumPayment);
		oEntryModel.setDueDate(p_dtDueDate);
		oEntryModel.setDatePaid(p_dtDatePaid);
		oEntryModel.setBillModel(p_oBillModel);
		return oEntryModel;
	}
	
	public static BillModel loadBillModel( final int p_iBillId, final String p_strName, final BigDecimal p_lAmount, final int p_iDayDue, final BigDecimal p_bdBalance)
	{
		final BillModel oBillModel = new BillModel();
		oBillModel.setBillId(p_iBillId);
		oBillModel.setName(p_strName);
		oBillModel.setAmount(p_lAmount);
		oBillModel.setDueDate(p_iDayDue);		
		oBillModel.setBalance(p_bdBalance);
		return oBillModel;
	}
}
