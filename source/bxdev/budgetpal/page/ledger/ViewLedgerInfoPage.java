package bxdev.budgetpal.page.ledger;

import bxdev.budgetpal.page.TopNavBasePage;
import bxdev.budgetpal.page.components.LedgerInfoPanel;

public class ViewLedgerInfoPage extends TopNavBasePage{
 
	/**SerialUID*/
	private static final long serialVersionUID = -5341638726365308993L;

	public ViewLedgerInfoPage()
	{
		super(MenuItemEnum.VIEW_LEDGER_INFO);
		
		add( new LedgerInfoPanel("pnlLedgerInfo"));
	}
}
