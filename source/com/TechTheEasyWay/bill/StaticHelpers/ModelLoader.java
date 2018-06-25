package com.TechTheEasyWay.bill.StaticHelpers;

import java.math.BigDecimal;
import java.sql.Date;

import com.TechTheEasyWay.bill.data.model.BillModel;
import com.TechTheEasyWay.bill.data.model.LedgerEntryModel;

public final class ModelLoader {

	private ModelLoader() {
	}

	public static LedgerEntryModel loadLedgerEntryModel(final int p_iBillId, final BigDecimal p_lAmountDue,
			final BigDecimal p_lMinimumPayment, final Date p_dtDueDate, final Date p_dtDatePaid,
			final BillModel p_oBillModel) {
		final LedgerEntryModel oLedgerEntry = new LedgerEntryModel();
		oLedgerEntry.setAmountDue(p_lAmountDue);
		oLedgerEntry.setMinimumPayment(p_lMinimumPayment);
		oLedgerEntry.setDueDate(p_dtDueDate);
		oLedgerEntry.setDatePaid(p_dtDatePaid);
		oLedgerEntry.setBillModel(p_oBillModel);
		return oLedgerEntry;
	}

	public static BillModel loadBillModel(final int p_iBillId, final String p_strName, final BigDecimal p_lAmount,
			final int p_iDayDue, final BigDecimal p_bdBalance) {
		final BillModel oBill = new BillModel();
		oBill.setId(p_iBillId);
		oBill.setName(p_strName);
		oBill.setAmount(p_lAmount);
		oBill.setDueDate(p_iDayDue);
		oBill.setBalance(p_bdBalance);
		return oBill;
	}
}
