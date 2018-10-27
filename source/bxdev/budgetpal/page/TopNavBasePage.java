package bxdev.budgetpal.page;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

import bxdev.budgetpal.page.ledger.ViewLedgerInfoPage;

public class TopNavBasePage extends WebPage {

	/** Serial UID */
	private static final long serialVersionUID = -7305512311036748076L;

	public static enum MenuItemEnum {
		VIEW_LEDGER_INFO, CALENDAR;
	}

	public TopNavBasePage(final MenuItemEnum p_eMenuItem) {
		final Link<Void> lnkLedger = new Link<Void>("lnkLedger") {

			/** Serial UID */
			private static final long serialVersionUID = -5561276931527814384L;

			@Override
			public void onClick() {

				System.out
						.println(
								"Context Path: "
										+ Files.exists(
												new File(WicketApplication.get().getServletContext().getContextPath()
														+ "/resources/css/main.css").toPath(),
												LinkOption.NOFOLLOW_LINKS));
				System.out.println(
						"Servlet Context Name: " + WicketApplication.get().getServletContext().getServletContextName());

				setResponsePage(ViewLedgerInfoPage.class);
			}
		};
		lnkLedger.setOutputMarkupId(true);

		final Link<Void> lnkCalendar = new Link<Void>("lnkCalendar") {

			/** Serial UID */
			private static final long serialVersionUID = 3086892745017285227L;

			@Override
			public void onClick() {
				setResponsePage(CalendarPage.class);
			}
		};
		lnkCalendar.setOutputMarkupId(true);

		add(lnkLedger, lnkCalendar);

		if (MenuItemEnum.CALENDAR.equals(p_eMenuItem)) {
			addJsModifier(lnkCalendar);
		}
		if (MenuItemEnum.VIEW_LEDGER_INFO.equals(p_eMenuItem)) {
			addJsModifier(lnkLedger);
		}
	}

	public void addJsModifier(final Link<Void> p_navLink) {
		p_navLink.add(AttributeModifier.append("class", "active"));
	}
}
