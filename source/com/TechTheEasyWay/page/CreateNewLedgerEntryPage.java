package com.TechTheEasyWay.page;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.IFormValidator;

import com.TechTheEasyWay.bill.DB.BillDB;
import com.TechTheEasyWay.page.components.DateTextFieldWithPicker;

public class CreateNewLedgerEntryPage extends BasePage
{
	/**SerialUID*/
	private static final long serialVersionUID = -301904443757118679L;
	public CreateNewLedgerEntryPage()
	{
		Form<Void> oForm = new Form<Void>("newEntryForm");
		add(oForm);
		oForm.add(new DropDownChoice<String>("lstBillOptions", BillDB.getAllBillNames()));
		oForm.add(new TextField<String>("txtAmountDue"));
		oForm.add(new DateTextFieldWithPicker("dtDueDateField"));
		oForm.add(new DateTextFieldWithPicker("dtDatePaidField"));
		oForm.add(new Button("btnCancel"){
			/**SerialUID*/
			private static final long serialVersionUID = -5091921791313048531L;

			@Override
			public void onSubmit(){
				setResponsePage(ViewLedgerInfoPage.class);
			}
		}.setDefaultFormProcessing(false));
		oForm.add(new Button("btnSubmit"){

			/**SerialUID*/
			private static final long serialVersionUID = 2203649808872981820L;
			
			@Override
			public void onSubmit(){
				setResponsePage(CreateNewLedgerEntryPage.class);
			}		
		}.setDefaultFormProcessing(true));
		
		oForm.add(new IFormValidator(){

			/**SerialUID*/
			private static final long serialVersionUID = -8061796052622009273L;

			@Override
			public FormComponent<?>[] getDependentFormComponents() {
				return null;
			}

			@Override
			public void validate(Form<?> form) {
				// checks: 
				// one of each is entered or selected
			}
		});
		
	}
}
