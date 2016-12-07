package com.TechTheEasyWay.bill.StaticHelpers;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import com.TechTheEasyWay.bill.data.model.BillModel;

public class StaticMonthlyBillsHelper {
	
	/**
	 * Get bill due date in the current month
	 * 
	 * @param p_oBill
	 * @return
	 */
	public static LocalDate getDate(final BillModel p_oBill) {
		return LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), p_oBill.getDueDate());
	}

	/**
	 * Get the next due date to make a payment to a specific bill considering
	 * the current month as the last known month in which a payment has been
	 * made.
	 * 
	 * @param p_oBill
	 * @return
	 */
	public static LocalDate getNextBillDueDate(final BillModel p_oBill) {
		return getNextBillDueDates(p_oBill, BigInteger.ONE.intValue()).get(0);
	}

	/**
	 * Get the next due dates to make a payment to a specific bill considering
	 * the current month as the last known month in which a payment has been
	 * made.
	 * 
	 * @param p_oBill
	 * @param p_iNumberOfDates
	 * @return
	 */
	public static List<LocalDate> getNextBillDueDates(final BillModel p_oBill, final int p_iNumberOfDates) {
		return getNextBillDueDates(p_oBill, p_iNumberOfDates, LocalDate.now().getMonth());
	}

	/**
	 * Get the next due dates to make a payment to a specific bill
	 * 
	 * @param p_oBill
	 *            Monthly Bill
	 * @param p_iNumberOfDates
	 *            number of upcoming due dates to retrieve
	 * @param p_oMonth
	 *            Last month of known payment made
	 * @return <{@code List<LocalDate>} List of upcoming due dates
	 */
	public static List<LocalDate> getNextBillDueDates(final BillModel p_oBill, final int p_iNumberOfDates,
			final Month p_oMonth) {
		List<LocalDate> lstOfDueDates = new ArrayList<>();
		for (int iCounter = 1; iCounter <= p_iNumberOfDates; iCounter++) {
			LocalDate oDate = null;
			if ((oDate = LocalDate.of(LocalDate.now().getYear(), p_oMonth, p_oBill.getDueDate()))
					.isAfter(LocalDate.now())) {
				lstOfDueDates.add(oDate);
			} else {
				lstOfDueDates.add(
						LocalDate.of(LocalDate.now().getYear(), p_oMonth, p_oBill.getDueDate()).plusMonths(iCounter));
			}

		}
		return lstOfDueDates;
	}

	/**
	 * Empty Default Constructor
	 */
	public StaticMonthlyBillsHelper() {
	}
}