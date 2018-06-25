package com.TechTheEasyWay.page;

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

	public CalendarPage(final CalendarCriteria p_oCriteria) {
		super(MenuItemEnum.CALENDAR);

		// create initial criteria
		final Model<CalendarCriteria> oCriteriaModel = Model.of(p_oCriteria);

		// create the criteria form
		final Form<CalendarCriteria> oForm = new Form<>("frmCalendar", new CompoundPropertyModel<>(p_oCriteria));
		oForm.setOutputMarkupId(true);
		add(oForm);

		// add drop downs
		oForm.add(new MonthDropDown("eMonth").add(getOnChangeBehavior(oForm)));
		oForm.add(new DayDropDown("iDay", PropertyModel.of(oForm.getModel(), "date")).add(getOnChangeBehavior(oForm)));
		oForm.add(new YearDropDown("iYear").add(getOnChangeBehavior(oForm)));

		// add the calendar panel using the criteria from the form
		final CalendarPanel pnlCalendar = new CalendarPanel("pnlCalendar", oCriteriaModel) {
			/** serialVersionUID */
			private static final long serialVersionUID = 4085667317454911316L;

			@Override
			protected void onSubmit(final AjaxRequestTarget p_oTarget, final CalendarModel p_mCalendar) {
				final CalendarCriteria oCriteria = oForm.getModelObject();
				oCriteria.setDay(p_mCalendar.getDay());
				oCriteria.setMonth(p_mCalendar.getMonth());
				oCriteria.setYear(p_mCalendar.getYear());

				oCriteriaModel.setObject(oCriteria);
				if (null != p_oTarget) {
					p_oTarget.add(oForm);
					p_oTarget.add(this);
				}
			}
		};
		pnlCalendar.setOutputMarkupId(true);
		add(pnlCalendar);

		// filter button to change the calendar
		oForm.add(new AjaxFallbackButton("btnFilter", oForm) {
			/** serialVersionUID */
			private static final long serialVersionUID = 1944465866025891442L;

			protected void onSubmit(final AjaxRequestTarget p_oTarget, final Form<?> p_oForm) {
				oCriteriaModel.setObject((CalendarCriteria) p_oForm.getModelObject());
				if (null != p_oTarget) {
					p_oTarget.add(pnlCalendar);
				}
			}
		});
	}

	/**
	 * Get "onchange" behavior for the drop downs
	 * 
	 * @param p_oForm
	 * @return
	 */
	private AjaxFormSubmitBehavior getOnChangeBehavior(final Form<CalendarCriteria> p_oForm) {
		return new AjaxFormSubmitBehavior(p_oForm, "change") {
			/** serialVersionUID */
			private static final long serialVersionUID = -6295369544168094069L;

			@Override
			protected void onSubmit(final AjaxRequestTarget p_oTarget) {
				final CalendarCriteria oCriteria = p_oForm.getModelObject();
				oCriteria.setDay(CalendarHelper.getValidDayOfMonth(oCriteria.getDay(), oCriteria.getDate()));

				if (null != p_oTarget) {
					p_oTarget.add(p_oForm);
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
		final LocalDate dToday = LocalDate.now();

		final CalendarCriteria oCriteria = new CalendarCriteria();
		oCriteria.setDay(dToday.getDayOfMonth());
		oCriteria.setYear(dToday.getYear());
		oCriteria.setMonth(dToday.getMonth());
		return oCriteria;
	}
}
