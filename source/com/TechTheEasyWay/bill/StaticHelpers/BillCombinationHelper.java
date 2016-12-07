package com.TechTheEasyWay.bill.StaticHelpers;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.TechTheEasyWay.bill.data.model.BillModel;

class BillCombinationHelper 
{
	private static List<List<BillModel>> lstAllCombinations = new ArrayList<>();
	 /* arr[]  ---> Input Array
	 data[] ---> Temporary array to store current combination
	 start & end ---> Staring and Ending indexes in arr[]
	 index  ---> Current index in data[]
	 r ---> Size of a combination to be printed */
	static void combinationUtil(List<BillModel> lstBills, List<BillModel> lstTempData, int iStartIndex, int iEndIndex, int iCurrentIndex, int iComboSize)
	{
		if(iCurrentIndex == iComboSize)
		{
			ArrayList<BillModel> lstCombo = new ArrayList<>();
			lstCombo.addAll(lstTempData);
			lstAllCombinations.add(lstCombo);
			return;
		}
		 
		for (int i = iStartIndex; i <= iEndIndex && iEndIndex-i+1 >= iComboSize-iCurrentIndex; i++)
		{
			try{
				lstTempData.set(iCurrentIndex, lstBills.get(i)); 
			}	
			catch(IndexOutOfBoundsException e )
			{
				lstTempData.add(lstBills.get(i));
			}
			combinationUtil(lstBills, lstTempData, i+ 1, iEndIndex, iCurrentIndex+1, iComboSize);
		}
	}
	
	public static List<List<BillModel>> getCombinations()
	{
		// reset holder of all the possible combinations
		lstAllCombinations.clear();
		 
		List<BillModel> lstTempData = new ArrayList<>();
		
		//TODO: this function should take in a list of BillModels and create a combination based on those objects
//		for(int iComboSize = 1; iComboSize <= StaticMonthlyBillsHelper.getBillModelList().size() ; iComboSize++)
//		{
//			 combinationUtil(StaticMonthlyBillsHelper.getBillModelList(), lstTempData, 0, StaticMonthlyBillsHelper.getBillModelList().size() - 1, 0, iComboSize);
//		}
		return lstAllCombinations;
	}
	 
	public static BigDecimal getTotalAmount(final List<BillModel> p_lstBills)
	{
		BigDecimal lSum = BigDecimal.ZERO;
		for(BillModel oBill: p_lstBills)
		{
			lSum = lSum.add(oBill.getAmount());
		}
        return lSum;
	}
	
	/*Driver function to check for above function*/
//	public static void main (String[] args) 
//	{
//		getCombinations();
//		lstAllCombinations.forEach(combination -> {System.out.println(String.format("%s Total Amount $%s",combination, getTotalAmount(combination)));});
//	}
}