package com.TechTheEasyWay.bill.StaticHelpers;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.TechTheEasyWay.bill.DB.BillDB;
import com.TechTheEasyWay.bill.data.model.BillModel;

public class StaticBillDateHelper {
	
	/**
	 * Get a map of Bills to paychecks for the next 12 Months. The HashMap links Bills 
	 * to Paychecks within a Month showing what Paychecks can be used to pay a particular Bill.
	 */
	public static HashMap<BillModel, HashMap<Month, List<LocalDate>>> getMapOfBillsToMonth() 
	{
		HashMap<BillModel, HashMap<Month, List<LocalDate>>> hshmapBillToPaycheckByMonth = new HashMap<>();
		// Iterate through the list of Monthly Bills
		for (BillModel oBill : BillDB.getAllBillModels()) {
			// Iterate through a list of the next 12 Months
			for (Month oMonth : Month.values()) {
				List<LocalDate> lstPayDates = new ArrayList<LocalDate>();
				// if the month is before the current month look at the Month in the
				// upcoming new year
				if (oMonth.compareTo(LocalDate.now().getMonth()) < 0
						|| (oMonth.compareTo(LocalDate.now().getMonth()) == 0
								&& oBill.getDueDate() < LocalDate.now().getDayOfMonth())) {
					lstPayDates = StaticPaycheckHelper.getPaychecksForBillMonth(oBill, oMonth,
							Year.of(LocalDate.now().getYear()).plusYears(BigInteger.ONE.intValue()));
				} else {
					lstPayDates = StaticPaycheckHelper.getPaychecksForBillMonth(oBill, oMonth);
				}
				hshmapBillToPaycheckByMonth = addToHashMap(hshmapBillToPaycheckByMonth, oBill, oMonth, lstPayDates);
			}
		}
		return hshmapBillToPaycheckByMonth;
	}
	
	/**
	 * Used to add another entry to the {@code HashMap<MonthlyBill, HashMap<Month, List<LocalDate>>>}.
	 * @param p_hshmapBillToPaycheckByMonth
	 * @param p_oBill #MonthlyBill
	 * @param p_oMonth
	 * @param p_lstDates
	 * @return {@code HashMap<MonthlyBill, HashMap<Month, List<LocalDate>>>}
	 */
	private static HashMap<BillModel, HashMap<Month, List<LocalDate>>> addToHashMap(
			final HashMap<BillModel, HashMap<Month, List<LocalDate>>> p_hshmapBillToPaycheckByMonth,
			final BillModel p_oBill, final Month p_oMonth, final List<LocalDate> p_lstDates) 
	{
		if (p_hshmapBillToPaycheckByMonth.containsKey(p_oBill)) {
			p_hshmapBillToPaycheckByMonth.get(p_oBill).put(p_oMonth, p_lstDates);
		} else {
			HashMap<Month, List<LocalDate>> hshmapMonthToPaychecks = new HashMap<>();
			hshmapMonthToPaychecks.put(p_oMonth, p_lstDates);
			p_hshmapBillToPaycheckByMonth.put(p_oBill, hshmapMonthToPaychecks);
		}
		return p_hshmapBillToPaycheckByMonth;
	}
	
	/**
	 * Print out the Bills with Paycheck dates per month Format: <br/>
	 * ********** CHASE_CREDIT_CARD <br/>
	 * JANUARY :: [2016-12-14, 2016-12-28]<br/>
	 * FEBRUARY :: [2017-01-11, 2017-01-25]<br/>
	 * MARCH :: [2017-02-08, 2017-02-22]<br/>
	 * APRIL :: [2017-03-08, 2017-03-22]<br/>
	 * MAY :: [2017-04-05, 2017-04-19]<br/>
	 * JUNE :: [2017-05-03, 2017-05-17, 2017-05-31]<br/>
	 * JULY :: [2016-06-15, 2016-06-29]<br/>
	 * AUGUST :: [2016-07-13, 2016-07-27]<br/>
	 * SEPTEMBER :: [2016-08-10, 2016-08-24]<br/>
	 * OCTOBER :: [2016-09-07, 2016-09-21]<br/>
	 * NOVEMBER :: [2016-10-05, 2016-10-19]<br/>
	 * DECEMBER :: [2016-11-02, 2016-11-16, 2016-11-30]
	 * 
	 * @param p_oHash
	 */
	public static void printBillDateHashMap(final HashMap<BillModel, HashMap<Month, List<LocalDate>>> p_oHash) 
	{
		for (Month oMonth : Month.values()) {
			System.out.println("\n**********  " + oMonth);
			for (BillModel oBill : p_oHash.keySet()) {

				System.out.println(oBill + " " + p_oHash.get(oBill).get(oMonth));
			}
		}
	}
	
}
