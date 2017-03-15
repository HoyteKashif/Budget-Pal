package com.TechTheEasyWay.page;

import com.TechTheEasyWay.page.components.LedgerInfoPanel;

public class ViewLedgerInfoPage extends BasePage{
 
	/**SerialUID*/
	private static final long serialVersionUID = -5341638726365308993L;

	public ViewLedgerInfoPage()
	{
		super();
		add( new LedgerInfoPanel("pnlLedgerInfo"));
	}
}
