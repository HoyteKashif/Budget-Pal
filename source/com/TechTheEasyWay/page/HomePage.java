package com.TechTheEasyWay.page;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.TechTheEasyWay.java_main.BillDatePicker;

public class HomePage extends BasePage{
	/**Serial UID*/
	private static final long serialVersionUID = -7265330126613608960L;

	public HomePage()
	{
//		add( new LedgerInfoPanel("pnlLedgerInfo"));
//		for(BillModel oBillModel : StaticMonthlyBillsHelper.getBillModelList())
//		{
//			System.out.println(oBillModel.getName() + " " + oBillModel.getAmount() + " " + oBillModel.getDueDate());
//		}
		
		BillDatePicker.insertIntoBillTableDB( new BigDecimal("500"), new BigDecimal("500"), java.sql.Date.valueOf(LocalDate.now()), java.sql.Date.valueOf(LocalDate.now()));
		System.out.println("inserted");
	}
}
