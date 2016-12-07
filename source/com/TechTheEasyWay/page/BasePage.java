package com.TechTheEasyWay.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

public class BasePage extends WebPage {

	/**Serial UID*/
	private static final long serialVersionUID = -609739701940956955L;
	public BasePage()
	{
		add(new Link<Void>("lnkHomePage")
		{
			/**SerialUID**/
			private static final long serialVersionUID = -2577253652043946570L;

			@Override
			public void onClick() {
				setResponsePage(HomePage.class);
			}
		});
		
		add(new Link<Void>("lnkViewLedgerPage")
		{
			/**SerialUID */
			private static final long serialVersionUID = -2577253652043946570L;

			@Override
			public void onClick() {
				setResponsePage(ViewLedgerInfoPage.class);
			}
		});
	}
}
