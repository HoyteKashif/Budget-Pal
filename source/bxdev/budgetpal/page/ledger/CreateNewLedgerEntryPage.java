package bxdev.budgetpal.page.ledger;

import java.math.BigDecimal;
import java.util.Date;

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

import bxdev.budgetpal.bill.DB.BillDB;
import bxdev.budgetpal.bill.DB.LedgerDB;
import bxdev.budgetpal.bill.data.model.Bill;
import bxdev.budgetpal.bill.data.model.Ledger;
import bxdev.budgetpal.page.TopNavBasePage;
import bxdev.budgetpal.page.components.NotificationPanel;

public class CreateNewLedgerEntryPage extends TopNavBasePage {
	/** SerialUID */
	private static final long serialVersionUID = -301904443757118679L;

	public CreateNewLedgerEntryPage() {
		super(MenuItemEnum.VIEW_LEDGER_INFO);
	}

	@Override
	public void onInitialize() {
		super.onInitialize();

		add(new NotificationPanel());

		add(new EntryForm());
	}

	private final class EntryForm extends Form<Ledger> {
		/** SerialVersionUID **/
		private static final long serialVersionUID = 2035333485851088653L;

		private FormComponent<Bill> billSelector;

		private FormComponent<BigDecimal> amountDue;

		private FormComponent<BigDecimal> minimumPayment;

		private FormComponent<Date> dueDate;

		public EntryForm() {
			super("newEntryForm", new CompoundPropertyModel<>(new Ledger()));
		}

		@Override
		public void onInitialize() {
			super.onInitialize();

			add(billSelector = new DropDownChoice<>("oBill", BillDB.getAllBillModels(),
					new ChoiceRenderer<Bill>("name")));

			add(amountDue = new TextField<>("amountDue"));

			add(minimumPayment = new TextField<>("minimumPayment"));

			dueDate = new DateTextField("dueDate");
			dueDate.add(new DatePicker());
			add(dueDate);

			add(new DateTextField("datePaid"));

			add(new Button("submit") {
				/** SerialVersionUID **/
				private static final long serialVersionUID = -3274962272258130399L;

				@Override
				public void onConfigure() {
					super.onConfigure();
					setDefaultFormProcessing(true);
				}

				@Override
				public void onSubmit() {
					LedgerDB.insert(EntryForm.this.getModelObject());
					setResponsePage(ViewLedgerInfoPage.class);
				}
			});

			add(new Button("cancel") {
				/** SerialVersionUID **/
				private static final long serialVersionUID = -8878848650659051072L;

				@Override
				public void onConfigure() {
					super.onConfigure();
					setDefaultFormProcessing(false);
				}

				@Override
				public void onSubmit() {
					setResponsePage(ViewLedgerInfoPage.class);
				}
			});

			addValidator();
		}

		private void addValidator() {
			add(new IFormValidator() {

				/** SerialUID */
				private static final long serialVersionUID = -8061796052622009273L;

				@Override
				public FormComponent<?>[] getDependentFormComponents() {
					return null;
				}

				@Override
				public void validate(Form<?> form) {

					/** Check bill **/
					if (billSelector.getConvertedInput() == null) {
						form.error("Select a Bill.");
					}

					/** Check amount **/
					final BigDecimal bdAmountDue = amountDue.getConvertedInput();
					if (bdAmountDue == null) {
						form.error("Enter a valid Payment amount.");
					}

					/** Check minimum payment (not required) **/
					final BigDecimal bdMinPayment = minimumPayment.getConvertedInput();
					if (bdMinPayment == null) {
						form.error("Enter a valid minimum Payment amount.");
					}

					/** Check the due date **/
					if (dueDate.getConvertedInput() == null) {
						form.error("Enter a valid Due Date.");
					}
				}
			});
		}
	}
}
