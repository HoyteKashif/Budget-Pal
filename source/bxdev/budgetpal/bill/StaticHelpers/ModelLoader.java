package bxdev.budgetpal.bill.StaticHelpers;

import java.math.BigDecimal;
import java.sql.Date;

import bxdev.budgetpal.bill.data.model.Bill;
import bxdev.budgetpal.bill.data.model.Ledger;

public final class ModelLoader {

	private ModelLoader() {
	}

	public static Ledger loadLedgerEntryModel(final int p_iBillId, final BigDecimal p_lAmountDue,
			final BigDecimal p_lMinimumPayment, final Date p_dtDueDate, final Date p_dtDatePaid,
			final Bill p_oBillModel) {
		final Ledger oLedgerEntry = new Ledger();
		oLedgerEntry.setAmountDue(p_lAmountDue);
		oLedgerEntry.setMinimumPayment(p_lMinimumPayment);
		oLedgerEntry.setDueDate(p_dtDueDate);
		oLedgerEntry.setDatePaid(p_dtDatePaid);
		oLedgerEntry.setBill(p_oBillModel);
		return oLedgerEntry;
	}

	public static Bill loadBillModel(final int p_iBillId, final String p_strName, final BigDecimal p_lAmount,
			final int p_iDayDue, final BigDecimal p_bdBalance) {
		final Bill oBill = new Bill();
		oBill.setId(p_iBillId);
		oBill.setName(p_strName);
		oBill.setAmount(p_lAmount);
		oBill.setDueDate(p_iDayDue);
		oBill.setBalance(p_bdBalance);
		return oBill;
	}
}
