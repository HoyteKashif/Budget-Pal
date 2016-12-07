package com.TechTheEasyWay.bill.data.model;

import java.math.BigDecimal;

import org.apache.wicket.util.io.IClusterable;

public class BillModel implements IClusterable
{	
	/**SerialUID*/
	private static final long serialVersionUID = 8619877080056399980L;
	
	private String strName;
	private BigDecimal lAmount;
	private int iDueDate;
	
	public BillModel()
	{
		
	}
	
	public BillModel(final String p_strName, final BigDecimal p_lAmount, final int p_iDueDate)
	{
		this.strName = p_strName;
		this.lAmount = p_lAmount;
		this.iDueDate = p_iDueDate;
	}
	
	public String getName() {
		return strName;
	}
	public void setName(final String p_strName) {
		this.strName = p_strName;
	}
	public BigDecimal getAmount() {
		return lAmount;
	}
	public void setAmount(final BigDecimal p_lAmount) {
		this.lAmount = p_lAmount;
	}
	public int getDueDate() {
		return iDueDate;
	}
	public void setDueDate(final int p_iDueDate) {
		this.iDueDate = p_iDueDate;
	}
}
