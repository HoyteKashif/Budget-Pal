package com.TechTheEasyWay.page.ledger;

import java.math.BigDecimal;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.IFormValidator;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.TechTheEasyWay.bill.DB.BillDB;
import com.TechTheEasyWay.bill.DB.LedgerDB;
import com.TechTheEasyWay.bill.StaticHelpers.ValidationHelper;
import com.TechTheEasyWay.bill.data.model.BillModel;
import com.TechTheEasyWay.bill.data.model.LedgerEntryModel;
import com.TechTheEasyWay.page.TopNavBasePage;
import com.TechTheEasyWay.page.components.NotificationPanel;

public class CreateNewLedgerEntryPage extends TopNavBasePage {
	/** SerialUID */
	private static final long serialVersionUID = -301904443757118679L;

	public CreateNewLedgerEntryPage() {
		super(MenuItemEnum.VIEW_LEDGER_INFO);

		add(new NotificationPanel("pnlFeedback"));

		final IModel<LedgerEntryModel> oEntryModel = new Model<LedgerEntryModel>();
		oEntryModel.setObject(new LedgerEntryModel());

		final Form<LedgerEntryModel> oForm = new Form<LedgerEntryModel>("newEntryForm",
				CompoundPropertyModel.of(oEntryModel));
		add(oForm);

		final DropDownChoice<BillModel> ddcBillSelector = new DropDownChoice<BillModel>("m_oBillModel",
				BillDB.getAllBillModels(), new ChoiceRenderer<BillModel>("m_strName"));
		oForm.add(ddcBillSelector);

		final TextField<BigDecimal> tfAmountDue = new TextField<BigDecimal>("m_lAmountDue");
		oForm.add(tfAmountDue);

		final TextField<BigDecimal> tfMinimumPayment = new TextField<BigDecimal>("m_lMinimumPayment");
		oForm.add(tfMinimumPayment);

		final DateTextField dtDueDate = new DateTextField("m_dtDueDate");
		dtDueDate.add(new DatePicker());
		oForm.add(dtDueDate);

		final DateTextField dtDatePaid = new DateTextField("m_dtDatePaid");
		dtDatePaid.add(new DatePicker());
		oForm.add(dtDatePaid);

		oForm.add(new Button("btnCancel") {
			/** SerialUID */
			private static final long serialVersionUID = -5091921791313048531L;

			@Override
			public void onSubmit() {
				setResponsePage(ViewLedgerInfoPage.class);
			}
		}.setDefaultFormProcessing(false));

		oForm.add(new Button("btnSubmit") {

			/** SerialUID */
			private static final long serialVersionUID = 2203649808872981820L;

			@Override
			public void onSubmit() {
				LedgerDB.insert(oForm.getModelObject());
				setResponsePage(ViewLedgerInfoPage.class);
			}
		}.setDefaultFormProcessing(true));

		oForm.add(new IFormValidator() {

			/** SerialUID */
			private static final long serialVersionUID = -8061796052622009273L;

			@Override
			public FormComponent<?>[] getDependentFormComponents() {
				return null;
			}

			@Override
			public void validate(Form<?> form) {

				/** Check bill **/
				if (null == ddcBillSelector.getConvertedInput()) {
					form.error("Select a Bill.");
				}

				/** Check amount **/
				final BigDecimal bdAmountDue = tfAmountDue.getConvertedInput();
				if (null == bdAmountDue || !ValidationHelper.isNumeric(bdAmountDue.toString(), false)) {
					form.error("Enter a valid Payment amount.");
				}

				/** Check minimum payment (not required) **/
				final BigDecimal bdMinPayment = tfMinimumPayment.getConvertedInput();
				if (null != bdMinPayment && !ValidationHelper.isNumeric(bdMinPayment.toString(), false)) {
					form.error("Enter a valid minimum Payment amount.");
				}

				/** Check the due date **/
				if (null == dtDueDate.getConvertedInput()) {
					form.error("Enter a valid Due Date.");
				}
			}
		});

	}
}
