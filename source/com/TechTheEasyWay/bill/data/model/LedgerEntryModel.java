package com.TechTheEasyWay.bill.data.model;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.wicket.util.io.IClusterable;


public class LedgerEntryModel implements IClusterable
{
	/**SerialUID*/
	private static final long serialVersionUID = -1341104582702369393L;
	
	private Integer iLedgerEntryId;
	private String strBillName;
	private BigDecimal lAmountDue;
	private BigDecimal lMinimumPayment;
	private Date dtDuedate;
	private Date dtDatePaid;
	
	/**
	 * @return the iLedgerEntryId
	 */
	public Integer getiLedgerEntryId() {
		return iLedgerEntryId;
	}

	/**
	 * @param iLedgerEntryId the iLedgerEntryId to set
	 */
	public void setiLedgerEntryId(Integer iLedgerEntryId) {
		this.iLedgerEntryId = iLedgerEntryId;
	}

	/**
	 * @return the strBillName
	 */
	public String getStrBillName() {
		return strBillName;
	}

	/**
	 * @param strBillName the strBillName to set
	 */
	public void setStrBillName(String strBillName) {
		this.strBillName = strBillName;
	}

	/**
	 * @return the lAmountDue
	 */
	public BigDecimal getlAmountDue() {
		return lAmountDue;
	}

	/**
	 * @param lAmountDue the lAmountDue to set
	 */
	public void setlAmountDue(BigDecimal lAmountDue) {
		this.lAmountDue = lAmountDue;
	}

	/**
	 * @return the lMinimumPayment
	 */
	public BigDecimal getlMinimumPayment() {
		return lMinimumPayment;
	}

	/**
	 * @param lMinimumPayment the lMinimumPayment to set
	 */
	public void setlMinimumPayment(BigDecimal lMinimumPayment) {
		this.lMinimumPayment = lMinimumPayment;
	}

	/**
	 * @return the dtDuedate
	 */
	public Date getDtDuedate() {
		return dtDuedate;
	}

	/**
	 * @param dtDuedate the dtDuedate to set
	 */
	public void setDtDuedate(Date dtDuedate) {
		this.dtDuedate = dtDuedate;
	}

	/**
	 * @return the dtDatePaid
	 */
	public Date getDtDatePaid() {
		return dtDatePaid;
	}

	/**
	 * @param dtDatePaid the dtDatePaid to set
	 */
	public void setDtDatePaid(Date dtDatePaid) {
		this.dtDatePaid = dtDatePaid;
	}

	public LedgerEntryModel()
	{
	}
}
