package com.TechTheEasyWay.bill.StaticHelpers;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.TechTheEasyWay.bill.data.model.BillModel;

public class StaticPaycheckHelper
{
	private final static LocalDate oInitialPaycheckReceived = LocalDate.of(2016, 6, 1);
	private final static int TWO_WEEKS = 14;
	
	/**
	 * Get the Date of the first PayDay.
	 * @return
	 */
	public static LocalDate getInitialPaycheckDate()
	{
		return oInitialPaycheckReceived;
	}
	
	/**
	 * Test whether the bill falls on or before a specific paycheck in a Month.
	 * @param p_oBill
	 * @param p_oPayDay
	 * @return
	 */
	public static boolean doesPaycheckComeBeforBill(final BillModel p_oBill, final LocalDate p_oPayDay)
	{
		return doesPaycheckComeBeforeBill(StaticMonthlyBillsHelper.getNextBillDueDate(p_oBill), p_oPayDay);
	}
	
	/**
	 * Does the PayDay come after or on the date of the next time this Bill is due.
	 * @param p_oDueDate
	 * @param p_oPayDay
	 * @return
	 */
	public static boolean doesPaycheckComeBeforeBill(final LocalDate p_oDueDate, final LocalDate p_oPayDay)
	{
		return p_oDueDate.isBefore(p_oPayDay) || p_oDueDate.isEqual(p_oPayDay);
	}
	
	
	public static List<BillModel> getPaycheckListOfBills(final LocalDate p_oPaycheckDate,
			final HashMap<Month, HashMap<LocalDate, List<BillModel>>> hshMapBillMonth) {
		return hshMapBillMonth.get(p_oPaycheckDate.getMonth()).get(p_oPaycheckDate);
	}
	
	/**
	 * Get the paychecks that can be used for the given {@code MonthlyBill} in
	 * the given {@code Month}.
	 * 
	 * @param p_oBill
	 *            Montly Bill
	 * @param p_oMonth
	 *            Month of the Due Date used to Query
	 * @return {@code List<LocalDate>} List of Paydays that can be used to pay
	 *         the given bill
	 */
	public static List<LocalDate> getPaychecksForBillMonth(final BillModel p_oBill, final Month p_oMonth) {
		return getPaychecksForBillMonth(p_oBill, p_oMonth, Year.now());
	}
	
	/**
	 * 
	 * @param p_oBill
	 * @return
	 */
	public static HashMap<Month, List<LocalDate>> getMapOfMonthToPaychecks(final BillModel p_oBill) {
		LocalDate oDate = LocalDate.now();
		HashMap<Month, List<LocalDate>> hshmapMonthToPaychecks = new HashMap<>();
		for (int iCounter = 1; iCounter < 13; iCounter++) {
			LocalDate ldTempDate = oDate.plusMonths(iCounter);
			hshmapMonthToPaychecks.put(ldTempDate.getMonth(),
					getPaychecksForBillMonth(p_oBill, ldTempDate.getMonth(), Year.from(ldTempDate)));
		}
		return hshmapMonthToPaychecks;
	}



	/**
	 * Get the paychecks that can be used for the given {@code MonthlyBill} in
	 * the given {@code Month}.
	 * 
	 * @param p_oBill
	 *            Montly Bill
	 * @param p_oMonth
	 *            Month of the Due Date used to Query
	 * @param oYear
	 *            Year of the Due Date used to Query
	 * @return {@code List<LocalDate>} List of Paydays that can be used to pay
	 *         the given bill
	 */
	public static List<LocalDate> getPaychecksForBillMonth(final BillModel p_oBill, final Month p_oMonth,
			final Year oYear) {
		LocalDate oUpcomingDueDate = LocalDate.of(oYear.getValue(), p_oMonth.getValue(), p_oBill.getDueDate());
		// get paychecks that fall in-between the Date above and the DueDate
		// prior to this one
		LocalDate oPreviousDueDate = oUpcomingDueDate.minusMonths(BigInteger.ONE.intValue());

		// list paychecks dates after previous due date but either before or on
		// the date of the Upcoming Due Date
		LocalDate oCheckDate = null;
		List<LocalDate> oPaycheckDates = new ArrayList<>();
		while ((oCheckDate = getDateOfNextPaycheck(oPreviousDueDate)).isBefore(oUpcomingDueDate)) {
			oPaycheckDates.add(oCheckDate);
			// advance the Previous Check to the next found paycheck
			oPreviousDueDate = oCheckDate;
		}
		return oPaycheckDates;
	}

	/**
	 * Used to find the next Paychecks to be received over a set number of
	 * months from the current Date.
	 * 
	 * @param p_iNumberOfMonths
	 * @return {@code List<LocalDate>} List of Dates to receive a paycheck
	 */
	public static List<LocalDate> getNextPayCheckDates(final int p_iNumberOfMonths) {
		ArrayList<LocalDate> lstOfPayCheckDates = new ArrayList<>();
		// first entry is the date of the next paycheck after
		lstOfPayCheckDates.add(getDateOfNextPaycheck());

		for (int iCounter = 0; iCounter < p_iNumberOfMonths;) {
			// get date of next paycheck and add it to the list
			lstOfPayCheckDates.add(getDateOfNextPaycheck(lstOfPayCheckDates.get(lstOfPayCheckDates.size() - 1)));
			// compare the last two dates in the list to see if the month is
			// different
			if (!lstOfPayCheckDates.get(lstOfPayCheckDates.size() - 1).getMonth()
					.equals(lstOfPayCheckDates.get(lstOfPayCheckDates.size() - 2).getMonth())) {
				iCounter += 1;
			}
		}

		// remove the last entry in the list because it will be the first
		// paycheck for the last
		if (!lstOfPayCheckDates.isEmpty()) {
			lstOfPayCheckDates.remove(lstOfPayCheckDates.size() - 1);
		}
		return lstOfPayCheckDates;
	}

	/**
	 * Get the date of the next paycheck that comes after the current Date.
	 * 
	 * @return LocalDate Date Of next o
	 */
	public static LocalDate getDateOfNextPaycheck() {
		return getDateOfNextPaycheck(LocalDate.now());
	}

	/**
	 * Get the date of the next paycheck that comes after the Date given.
	 * 
	 * @param oDateOfLastPayCheck
	 * @return
	 */
	public static LocalDate getDateOfNextPaycheck(final LocalDate p_oDate) {
		LocalDate oDateIterator = oInitialPaycheckReceived;
		while (!oDateIterator.isAfter(p_oDate)) {
			oDateIterator = oDateIterator.plusDays(TWO_WEEKS);
		}
		return oDateIterator;
	}
	
	/**
	 * Get the next set of paychecks from the given start date
	 * 
	 * @param p_oStartDate
	 * @return {@code List<LocalDate>}
	 */
	public static List<LocalDate> getSixMonthsOfPayDates(final LocalDate p_oStartDate) {
		int iNumberOfMonths = 1;
		ArrayList<LocalDate> lstOfPayDates = new ArrayList<>();
		lstOfPayDates.add(p_oStartDate);
		while (iNumberOfMonths <= 6) {
			lstOfPayDates.add(LocalDate.from(lstOfPayDates.get(lstOfPayDates.size() - 1).plusDays(14L)));
			// compare the last two dates to see if the month has changed
			if (!lstOfPayDates.get(lstOfPayDates.size() - 1).getMonth()
					.equals(lstOfPayDates.get(lstOfPayDates.size() - 2).getMonth())) {
				iNumberOfMonths += 1;
			}
		}
		return lstOfPayDates;
	}
	
	/**
	 * Empty Default Constructor
	 */
	public StaticPaycheckHelper()
	{
	}
}