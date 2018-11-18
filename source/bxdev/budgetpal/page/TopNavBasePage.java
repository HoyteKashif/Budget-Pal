package bxdev.budgetpal.page;

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

	private final MenuItemEnum eSelected;
	private Link<Void> calendar;
	private Link<Void> ledger;

	public TopNavBasePage(final MenuItemEnum p_eSelected) {
		this.eSelected = p_eSelected;
	}

	@Override
	public void onInitialize() {
		super.onInitialize();

		add(calendar = new Link<Void>("lnkCalendar") {
			/** Serial UID */
			private static final long serialVersionUID = 3086892745017285227L;

			@Override
			public void onConfigure() {
				super.onConfigure();
				setOutputMarkupId(true);
			}

			@Override
			public void onClick() {
				setResponsePage(CalendarPage.class);
			}
		});

		add(ledger = new Link<Void>("lnkLedger") {

			/** Serial UID */
			private static final long serialVersionUID = -5561276931527814384L;

			@Override
			public void onConfigure() {
				super.onConfigure();
				setOutputMarkupId(true);
			}

			@Override
			public void onClick() {
				setResponsePage(ViewLedgerInfoPage.class);
			}
		});

		setActiveItem();
	}

	private void setActiveItem() {
		switch (eSelected) {
		case CALENDAR:
			addJsModifier(calendar);
			break;
		case VIEW_LEDGER_INFO:
			addJsModifier(ledger);
			break;
		default:
			throw new IllegalArgumentException("Unknown Menu Selection.");
		}
	}

	public void addJsModifier(final Link<Void> p_navLink) {
		p_navLink.add(AttributeModifier.append("class", "active"));
	}
}
