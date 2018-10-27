package bxdev.budgetpal.page;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

import bxdev.budgetpal.page.ledger.ViewLedgerInfoPage;

public class WicketApplication extends WebApplication {

	public WicketApplication() {
		super();
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return ViewLedgerInfoPage.class;
	}

	@Override
	public void init() {
		super.init();

		/** New Pages (my own CSS and JavaScript) **/
		mountPage("/LedgerInfo", ViewLedgerInfoPage.class);
		mountPage("/CalendarPage", CalendarPage.class);
	}
}
