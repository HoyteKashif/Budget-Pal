package bxdev.budgetpal.page;

import java.time.LocalDate;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import com.nmiller.calendar.component.form.DayDropDown;
import com.nmiller.calendar.component.form.MonthDropDown;
import com.nmiller.calendar.component.form.YearDropDown;
import com.nmiller.calendar.component.panel.CalendarPanel;
import com.nmiller.calendar.data.model.CalendarCriteria;
import com.nmiller.calendar.data.model.CalendarModel;
import com.nmiller.calendar.util.helper.CalendarHelper;

public class CalendarPage extends TopNavBasePage {
	/** serialVersionUID */
	private static final long serialVersionUID = -5451604108547652321L;

	public CalendarPage() {
		this(getDefaultCriteria());
	}

	public CalendarPage(final CalendarCriteria p_calCriteria) {
		super(MenuItemEnum.CALENDAR);

		// create initial criteria
		final Model<CalendarCriteria> criteriaModel = Model.of(p_calCriteria);

		// create the criteria form
		final Form<CalendarCriteria> form = new Form<>("frmCalendar", new CompoundPropertyModel<>(p_calCriteria));
		form.setOutputMarkupId(true);
		add(form);

		// add drop downs
		form.add(new MonthDropDown("eMonth").add(getOnChangeBehavior(form)));
		form.add(new DayDropDown("iDay", PropertyModel.of(form.getModel(), "date")).add(getOnChangeBehavior(form)));
		form.add(new YearDropDown("iYear").add(getOnChangeBehavior(form)));

		// add the calendar panel using the criteria from the form
		final CalendarPanel pnlCalendar = new CalendarPanel("pnlCalendar", criteriaModel) {
			/** serialVersionUID */
			private static final long serialVersionUID = 4085667317454911316L;

			@Override
			protected void onSubmit(final AjaxRequestTarget p_target, final CalendarModel p_calendarModel) {
				final CalendarCriteria criteria = form.getModelObject();
				criteria.setDay(p_calendarModel.getDay());
				criteria.setMonth(p_calendarModel.getMonth());
				criteria.setYear(p_calendarModel.getYear());

				criteriaModel.setObject(criteria);
				if (p_target != null) {
					p_target.add(form);
					p_target.add(this);
				}
			}
		};
		pnlCalendar.setOutputMarkupId(true);
		add(pnlCalendar);

		// filter button to change the calendar
		form.add(new AjaxFallbackButton("btnFilter", form) {
			/** serialVersionUID */
			private static final long serialVersionUID = 1944465866025891442L;

			protected void onSubmit(final AjaxRequestTarget p_target, final Form<?> p_form) {
				criteriaModel.setObject((CalendarCriteria) p_form.getModelObject());
				if (null != p_target) {
					p_target.add(pnlCalendar);
				}
			}
		});
	}

	/**
	 * Get "onchange" behavior for the drop downs
	 * 
	 * @param p_form
	 * @return
	 */
	private AjaxFormSubmitBehavior getOnChangeBehavior(final Form<CalendarCriteria> p_form) {
		return new AjaxFormSubmitBehavior(p_form, "change") {
			/** serialVersionUID */
			private static final long serialVersionUID = -6295369544168094069L;

			@Override
			protected void onSubmit(final AjaxRequestTarget p_target) {
				final CalendarCriteria criteria = p_form.getModelObject();
				criteria.setDay(CalendarHelper.getValidDayOfMonth(criteria.getDay(), criteria.getDate()));

				if (p_target != null) {
					p_target.add(p_form);
				}
			}
		};
	}

	/**
	 * Get the default criteria for the form.
	 * 
	 * @return CalendarCriteria
	 */
	private static CalendarCriteria getDefaultCriteria() {
		final LocalDate ldNow = LocalDate.now();

		final CalendarCriteria criteria = new CalendarCriteria();
		criteria.setDay(ldNow.getDayOfMonth());
		criteria.setYear(ldNow.getYear());
		criteria.setMonth(ldNow.getMonth());
		return criteria;
	}
}
