//package bxdev.budgetpal.bill.StaticHelpers;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//import bxdev.budgetpal.bill.data.model.Bill;
//
//class BillCombinationHelper {
//	private static List<List<Bill>> lstAllCombinations = new ArrayList<>();
//
//	/*
//	 * arr[] ---> Input Array data[] ---> Temporary array to store current
//	 * combination start & end ---> Staring and Ending indexes in arr[] index
//	 * ---> Current index in data[] r ---> Size of a combination to be printed
//	 */
//	static void combinationUtil(List<Bill> lstBills, List<Bill> lstTempData, int iStartIndex, int iEndIndex,
//			int iCurrentIndex, int iComboSize) {
//		if (iCurrentIndex == iComboSize) {
//			ArrayList<Bill> lstCombo = new ArrayList<>();
//			lstCombo.addAll(lstTempData);
//			lstAllCombinations.add(lstCombo);
//			return;
//		}
//
//		for (int i = iStartIndex; i <= iEndIndex && iEndIndex - i + 1 >= iComboSize - iCurrentIndex; i++) {
//			try {
//				lstTempData.set(iCurrentIndex, lstBills.get(i));
//			} catch (IndexOutOfBoundsException e) {
//				lstTempData.add(lstBills.get(i));
//			}
//			combinationUtil(lstBills, lstTempData, i + 1, iEndIndex, iCurrentIndex + 1, iComboSize);
//		}
//	}
//
//	public static List<List<Bill>> getCombinations() {
//		// reset holder of all the possible combinations
//		lstAllCombinations.clear();
//
//		List<Bill> lstTempData = new ArrayList<>();
//
//		// TODO: this function should take in a list of BillModels and create a
//		// combination based on those objects
//		// for(int iComboSize = 1; iComboSize <=
//		// StaticMonthlyBillsHelper.getBillModelList().size() ; iComboSize++)
//		// {
//		// combinationUtil(StaticMonthlyBillsHelper.getBillModelList(),
//		// lstTempData, 0, StaticMonthlyBillsHelper.getBillModelList().size() -
//		// 1, 0, iComboSize);
//		// }
//		return lstAllCombinations;
//	}
//
//	public static BigDecimal getTotalAmount(final List<Bill> bills) {
//		BigDecimal sum = BigDecimal.ZERO;
//		for (Bill oBill : bills) {
//			sum = sum.add(oBill.getAmount());
//		}
//		return sum;
//	}
//}