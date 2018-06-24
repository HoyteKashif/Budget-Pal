package com.TechTheEasyWay.page;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

public class WicketApplication extends WebApplication {

	public WicketApplication() {
		super();
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return SignInPage.class;
	}

	@Override
	public void init() {
		super.init();
		mountPage("/SignIn", SignInPage.class);
		mountPage("/CreateLedgerEntry", CreateNewLedgerEntryPage.class);
		mountPage("/ViewLedgerInfo", ViewLedgerInfoPage.class);

		/** New Pages (my own CSS and JavaScript) **/
		mountPage("/LedgerInfo", com.TechTheEasyWay.page.ledger.ViewLedgerInfoPage.class);
		mountPage("/CalendarPage", CalendarPage.class);
	}
}
