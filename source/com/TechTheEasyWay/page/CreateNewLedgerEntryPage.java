package com.TechTheEasyWay.page;

import java.util.Objects;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.IFormValidator;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.TechTheEasyWay.bill.DB.BillDB;
import com.TechTheEasyWay.bill.data.model.LedgerEntryModel;
import com.TechTheEasyWay.page.components.DateTextFieldWithPicker;

public class CreateNewLedgerEntryPage extends BasePage
{
	/**SerialUID*/
	private static final long serialVersionUID = -301904443757118679L;
	public CreateNewLedgerEntryPage()
	{
		final IModel<LedgerEntryModel> oEntryModel = new Model<LedgerEntryModel>();
		oEntryModel.setObject(new LedgerEntryModel());
		
		final Form<LedgerEntryModel> oForm = new Form<LedgerEntryModel>("newEntryForm", CompoundPropertyModel.of(oEntryModel));
		add(oForm);
		
		final DropDownChoice<String> ddcBillSelector = new DropDownChoice<String>("strBillName", BillDB.getAllBillNames());
		oForm.add(ddcBillSelector);
		
		final TextField<String> tfAmountDue = new TextField<String>("lAmountDue");
		oForm.add(tfAmountDue);
		
		final TextField<String> tfMinimumPayment = new TextField<String>("lMinimumPayment");
		oForm.add(tfMinimumPayment);
		
		final DateTextFieldWithPicker dtfDueDate = new DateTextFieldWithPicker("dtDueDate");
		oForm.add(dtfDueDate);
		
		final DateTextFieldWithPicker dtfDatePaidDate = new DateTextFieldWithPicker("dtDatePaid");
		oForm.add(dtfDatePaidDate);
		
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
				String strBillSelector = ddcBillSelector.getRawInput();
				if (Objects.isNull(strBillSelector)){
					form.error("User must select a Bill");
				}
				// checks: 
				//TODO: user must enter a valid numerical value into the Amount Due field
				//TODO: user must enter a valid numerical value into the minimum payment field
				//TODO: user must enter a due date
				//TODO: user does not have to enter a date paid
			}
		});
		
	}
}
