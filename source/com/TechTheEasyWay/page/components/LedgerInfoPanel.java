package com.TechTheEasyWay.page.components;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import com.TechTheEasyWay.bill.DB.LedgerDB;
import com.TechTheEasyWay.bill.data.model.LedgerEntryModel;
import com.TechTheEasyWay.page.CreateNewLedgerEntryPage;

public class LedgerInfoPanel extends Panel 
{
	/**SerialUID*/
	private static final long serialVersionUID = 5706377213661434524L;

	public LedgerInfoPanel( final String p_strId) {
		
		super(p_strId);
		final Form<Void> oFormLedgerTable  = new Form<Void>("formLedgerTable");
		add( oFormLedgerTable);
		
		oFormLedgerTable.add (new Button("btnAddEntry")
		{
			/**SerialUID*/
			private static final long serialVersionUID = -8608318705083169618L;

			@Override
			public void onSubmit() {
				setResponsePage(CreateNewLedgerEntryPage.class);
			}	
		});
		
		oFormLedgerTable.add (new ListView<LedgerEntryModel>("lstLedgerEntries", LedgerDB.getAllLedgerModels())
		{
			/**SerialUID */
			private static final long serialVersionUID = -2965543685714247994L;

			@Override
			protected void populateItem(ListItem<LedgerEntryModel> p_oItem) 
			{	
				LedgerEntryModel oEntryModel = p_oItem.getModelObject();
				p_oItem.add(new Label("strBillName", oEntryModel.getStrBillName()));
				p_oItem.add(new Label("strAmountDue", "$" + ((oEntryModel.getlAmountDue() != null) ? oEntryModel.getlAmountDue() : "0")));
				p_oItem.add(new Label("strDueDate", oEntryModel.getDueDate()));
				p_oItem.add(new Label("strDatePaid", (oEntryModel.getDatePaid() != null) ? oEntryModel.getDatePaid() : "Not Paid Yet"));
				p_oItem.add(removeLink("lnkDelete", p_oItem));
			}
		});
	}
}
