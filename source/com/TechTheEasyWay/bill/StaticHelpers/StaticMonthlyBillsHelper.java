package com.TechTheEasyWay.bill.StaticHelpers;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.TechTheEasyWay.bill.data.model.BillModel;

public class StaticMonthlyBillsHelper {
	
	/**
	 * Get bill due date in the current month
	 * 
	 * @param p_oBill
	 * @return
	 */
	public static LocalDate getCurrentDueDate(final BillModel p_oBill) {
		Objects.requireNonNull(p_oBill);
		
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
			LocalDate oDate = getDueDateByMonth(p_oMonth, p_oBill);
			if (isPassedCurrentDate(oDate)) {
				lstOfDueDates.add(oDate);
			} else {
				lstOfDueDates.add(getDateInFuture(p_oMonth, p_oBill.getDueDate(), iCounter));
			}
		}
		
		return lstOfDueDates;
	}
	
	public static Year getCurrentYear()
	{
		return Year.of(LocalDate.now().getYear());
	}
	
	public static Year getYear(final int p_iYearAdjustment)
	{
		return getCurrentYear().plusYears(p_iYearAdjustment);
	}
	
	private static LocalDate getDueDateByMonth(final Month p_oMonth, final BillModel p_oBill)
	{
		return LocalDate.of(LocalDate.now().getYear(), p_oMonth, p_oBill.getDueDate());
	}
	
	public static boolean isNotPassedCurrentDate(final BillModel p_oBill, final Month p_oMonth)
	{
		return !isPassedCurrentDate(p_oBill, p_oMonth);
	}
	
	public static boolean isPassedCurrentDate(final BillModel p_oBill, final Month p_oMonth)
	{
		return isPassedCurrentDate(getDueDateByMonth(p_oMonth, p_oBill));
	}
	
	private static boolean isPassedCurrentDate(final LocalDate p_Date)
	{
		return Objects.requireNonNull(p_Date).isAfter(LocalDate.now()); 
	}
	
	
	private static LocalDate getDateInFuture(final Month p_oMonth, final int p_iDayOfMonth, final int p_iMonthsInFuture)
	{
		return getDateInCurrentYear(p_oMonth, p_iDayOfMonth).plusMonths(p_iMonthsInFuture);
	}
	
	private static LocalDate getDateInCurrentYear(final Month p_oMonth, final int p_iDayOfMonth)
	{
		return LocalDate.of(LocalDate.now().getYear(), p_oMonth, p_iDayOfMonth);
	}

	/**
	 * Empty Default Constructor
	 */
	public StaticMonthlyBillsHelper() {
	}
}