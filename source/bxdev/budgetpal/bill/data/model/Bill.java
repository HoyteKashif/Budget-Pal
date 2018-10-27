package bxdev.budgetpal.bill.data.model;

import java.math.BigDecimal;

import org.apache.wicket.util.io.IClusterable;

public final class Bill implements IClusterable {
	/** SerialUID */
	private static final long serialVersionUID = 8619877080056399980L;

	private int id;
	private String name;
	private BigDecimal amount;
	private int dueDate;
	private BigDecimal balance;

	public Bill() {
	}

	public int getId() {
		return id;
	}

	public void setId(final int p_id) {
		this.id = p_id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String p_name) {
		this.name = p_name;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(final BigDecimal p_amount) {
		this.amount = p_amount;
	}

	public int getDueDate() {
		return dueDate;
	}

	public void setDueDate(final int p_dueDate) {
		this.dueDate = p_dueDate;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(final BigDecimal p_balance) {
		this.balance = p_balance;
	}
}
