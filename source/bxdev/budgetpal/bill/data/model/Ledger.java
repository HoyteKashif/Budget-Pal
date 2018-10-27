package bxdev.budgetpal.bill.data.model;

import java.math.BigDecimal;
import java.sql.Date;

import org.apache.wicket.util.io.IClusterable;

public final class Ledger implements IClusterable {
	/** SerialUID */
	private static final long serialVersionUID = -1341104582702369393L;

	// Ledger Entry Data
	private Integer id;
	private BigDecimal amountDue;
	private BigDecimal minimumPayment;
	private Date dueDate;
	private Date datePaid;

	// related Bill Data
	private Bill oBill;

	public Ledger() {
	}

	/**
	 * @return the oBill
	 */
	public Bill getBill() {
		return oBill;
	}

	/**
	 * @param p_oBill
	 *            the oBill to set
	 */
	public void setBill(final Bill p_oBill) {
		this.oBill = p_oBill;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param p_id
	 *            the id to set
	 */
	public void setLedgerEntryId(final Integer p_id) {
		this.id = p_id;
	}

	/**
	 * @return the amountDue
	 */
	public BigDecimal getAmountDue() {
		return amountDue;
	}

	/**
	 * @param p_amountDue
	 *            the amountDue to set
	 */
	public void setAmountDue(final BigDecimal p_amountDue) {
		this.amountDue = p_amountDue;
	}

	/**
	 * @return the minimumPayment
	 */
	public BigDecimal getMinimumPayment() {
		return minimumPayment;
	}

	/**
	 * @param p_minimumPayment
	 *            the minimumPayment to set
	 */
	public void setMinimumPayment(final BigDecimal p_minimumPayment) {
		this.minimumPayment = p_minimumPayment;
	}

	/**
	 * @return the dueDate
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * @param p_duedate
	 *            the dueDate to set
	 */
	public void setDueDate(final Date p_dueDate) {
		this.dueDate = p_dueDate;
	}

	/**
	 * @return the datePaid
	 */
	public Date getDatePaid() {
		return datePaid;
	}

	/**
	 * @param p_datePaid
	 *            the datePaid to set
	 */
	public void setDatePaid(final Date p_datePaid) {
		this.datePaid = p_datePaid;
	}
}
