package com.TechTheEasyWay.page.components;

import java.util.Objects;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import com.TechTheEasyWay.bill.DB.LedgerDB;
import com.TechTheEasyWay.bill.data.model.LedgerEntryModel;
import com.TechTheEasyWay.page.ledger.CreateNewLedgerEntryPage;

public class LedgerInfoPanel extends Panel {
	/** SerialUID */
	private static final long serialVersionUID = 5706377213661434524L;

	public LedgerInfoPanel(final String p_strId) {

		super(p_strId);
		add(getListViewOfLedgerEntries());
		add(new Link<Void>("lnkAddEntry") {

			/** SerialUID */
			private static final long serialVersionUID = -8608318705083169618L;

			@Override
			public void onClick() {
				setResponsePage(CreateNewLedgerEntryPage.class);
			}
		});
	}

	private ListView<LedgerEntryModel> getListViewOfLedgerEntries() {
		return new ListView<LedgerEntryModel>("lstLedgerEntries", LedgerDB.getAllLedgerModels()) {
			/** SerialUID */
			private static final long serialVersionUID = -2965543685714247994L;

			public Link<Void> dbRemoveLink(final String id, final ListItem<LedgerEntryModel> item) {
				return new Link<Void>(id) {
					private static final long serialVersionUID = 1L;

					/**
					 * @see org.apache.wicket.markup.html.link.Link#onClick()
					 */
					@Override
					public void onClick() {
						final LedgerEntryModel modelObject = item.getModelObject();

						addStateChange();

						item.modelChanging();

						// Remove item and invalidate listView
						getList().remove(item.getIndex());

						modelChanged();
						removeAll();

						// delete it from the backing database
						LedgerDB.delete(modelObject);
					}
				};
			}

			@Override
			protected void populateItem(ListItem<LedgerEntryModel> p_oItem) {
				final LedgerEntryModel oEntryModel = p_oItem.getModelObject();
				p_oItem.add(new Label("strBillName",
						Objects.nonNull(oEntryModel.getBill()) ? oEntryModel.getBill().getName() : ""));
				p_oItem.add(new Label("strAmountDue",
						"$" + (Objects.nonNull(oEntryModel.getAmountDue()) ? oEntryModel.getAmountDue() : "0")));
				p_oItem.add(new Label("strDueDate", oEntryModel.getDueDate()));
				p_oItem.add(new Label("strDatePaid",
						(Objects.nonNull(oEntryModel.getDatePaid()) ? oEntryModel.getDatePaid() : "Not Paid Yet")));
				p_oItem.add(dbRemoveLink("lnkDelete", p_oItem));
			}
		};
	}
}
