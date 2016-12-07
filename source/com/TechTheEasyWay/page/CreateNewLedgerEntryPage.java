package com.TechTheEasyWay.page;

import org.apache.wicket.extensions.markup.html.form.DateTextField;

public class CreateNewLedgerEntryPage extends BasePage
{
	/**SerialUID*/
	private static final long serialVersionUID = -301904443757118679L;
	public CreateNewLedgerEntryPage()
	{
		final DateTextField oDateTextField = new DateTextField("dtDateField");// = new DatePicker("datepicker", Model.of(new Date()));
		add(oDateTextField);
	}
}
