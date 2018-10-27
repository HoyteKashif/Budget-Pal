package com.nmiller.calendar.component.panel;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;

import com.nmiller.calendar.component.label.MonthLabel;
import com.nmiller.calendar.data.model.CalendarCriteria;
import com.nmiller.calendar.data.model.CalendarModel;
import com.nmiller.calendar.data.model.DayModel;
import com.nmiller.calendar.data.model.WeekModel;
import com.nmiller.calendar.util.helper.DataHelper;

import bxdev.budgetpal.calendar.panel.DayPanel;


public abstract class CalendarPanel extends Panel
{
  /** serialVersionUID */
  private static final long serialVersionUID = 5443626943373011477L;
  
  /**
   * Default constructor for today's date
   * 
   * @param p_strId
   */
  public CalendarPanel(final String p_strId)
  {
    this(p_strId, null);
  }

  /**
   * Main calendar panel constructor.
   * 
   * @param p_strId
   * @param p_oCalendarCriteria
   */
  public CalendarPanel(final String p_strId, final IModel<CalendarCriteria> p_oCalendarCriteria)
  {
    super(p_strId);
    
    // use the criteria to create a CalendarModel and use it for the calendar form
    addForm(getCalendarFormModel(p_oCalendarCriteria));
  }
  
  /**
   * Abstract method so the parent container can do things when the panel's form submits
   * 
   * @param p_oTarget
   * @param p_mCalendar
   */
  protected abstract void onSubmit(final AjaxRequestTarget p_oTarget, final CalendarModel p_mCalendar);
  
  /**
   * Add the calendar form
   * 
   * @param p_oCalendarModel
   */
  private void addForm(final IModel<CalendarModel> p_oCalendarModel)
  {
    final Form<CalendarModel> oForm = new Form<>("frmCalendar", new CompoundPropertyModel<>(p_oCalendarModel));
    add(oForm);
    
    // add previous and next buttons
    oForm.add(getPreviousButton(oForm));
    oForm.add(getNextButton(oForm));
    
    // month and year label for top of the calendar
    oForm.add(new MonthLabel("eMonth"));
    oForm.add(new MonthLabel("iYear"));
    
    // start building the calendar. weeks -> days -> events
    oForm.add(new PropertyListView<WeekModel>("lstWeek")
    {
      /** serialVersionUID */
      private static final long serialVersionUID = 4443250230494810917L;

      @Override
      protected void populateItem(final ListItem<WeekModel> p_oItem)
      {
        p_oItem.add(getDayList(p_oItem.getModel(), PropertyModel.of(oForm.getModel(), "dDate")));
      }
    });
  }
  
  /**
   * Get the previous button to move back a month.
   * 
   * @param p_oForm
   * @param p_oCalendarCriteria
   * @return
   */
  private Button getPreviousButton(final Form<CalendarModel> p_oCalendarForm)
  {
    return new AjaxFallbackButton("btnPrevious", p_oCalendarForm)
    {
      /** serialVersionUID */
      private static final long serialVersionUID = 6747349412464718345L;

      protected void onSubmit(final AjaxRequestTarget p_oTarget, final Form<?> p_oForm)
      {
        final CalendarModel mCalendar = getNewCalendar(p_oCalendarForm.getModelObject().getDate().minusMonths(1));
        p_oCalendarForm.setModelObject(mCalendar);
        
        // call abstract onSubmit so the parent can do stuff
        CalendarPanel.this.onSubmit(p_oTarget, mCalendar);
      }
    };
  }
  
  /**
   * Get the next button to move forward a month.
   * 
   * @param p_oForm
   * @param p_oCalendarCriteria
   * @return
   */
  private Button getNextButton(final Form<CalendarModel> p_oCalendarForm)
  {
    return new AjaxFallbackButton("btnNext", p_oCalendarForm)
    {
      /** serialVersionUID */
      private static final long serialVersionUID = -8958588097442415332L;

      protected void onSubmit(final AjaxRequestTarget p_oTarget, final Form<?> p_oForm)
      {
        final CalendarModel mCalendar = getNewCalendar(p_oCalendarForm.getModelObject().getDate().plusMonths(1));
        p_oCalendarForm.setModelObject(mCalendar);
        
        // call abstract onSubmit so the parent can do stuff
        CalendarPanel.this.onSubmit(p_oTarget, mCalendar);
      }
    };
  }
  
  /**
   * Get the list of days for the given week.
   * 
   * @param p_oWeekModel          the week
   * @param p_oActiveDateModel    the active date
   * @return ListView<DayModel>
   */
  private ListView<DayModel> getDayList(final IModel<WeekModel> p_oWeekModel, final IModel<LocalDate> p_oActiveDateModel)
  {
    return new PropertyListView<DayModel>("lstDay")
    {
      /** serialVersionUID */
      private static final long serialVersionUID = -6941116559014525848L;
  
      @Override
      protected void populateItem(final ListItem<DayModel> p_oItem)
      {
        final DayModel mDay = p_oItem.getModelObject();
        
        p_oItem.add(new DayPanel("DayPanel", mDay));
      }
    };
  }
  
  
  /**
   * Get the calendar form model.
   * 
   * @param p_oCalendarCriteria         CalendarCriteria
   * @return IModel<CalendarModel>
   */
  private IModel<CalendarModel> getCalendarFormModel(final IModel<CalendarCriteria> p_oCalendarCriteria)
  {
    return new LoadableDetachableModel<CalendarModel>() 
    {
      /** serialVersionUID */
      private static final long serialVersionUID = -8859314495262028527L;

      @Override
      public CalendarModel load()
      {
        if(null != p_oCalendarCriteria)
        {
          return getNewCalendar(p_oCalendarCriteria.getObject().getDate());
        }
        return getNewCalendar(LocalDate.now());
      }
    };
  }
  
  /**
   * Get a calendar model for the given date.
   * 
   * @param p_dDate
   * @return CalendarModel
   */
  private static CalendarModel getNewCalendar(final LocalDate p_dDate)
  {
    try
    {
      // set current date fields
      final CalendarModel mCalendar = new CalendarModel();
      mCalendar.setDate(p_dDate);
      mCalendar.setYear(p_dDate.getYear());
      mCalendar.setMonth(p_dDate.getMonth());
      mCalendar.setDay(p_dDate.getDayOfMonth());
      
      // start building the calendar
      final List<WeekModel> lstWeek = new ArrayList<>();
      
      // adjust date to first sunday at or before the first day of the given month
      final LocalDate dStartDate = p_dDate.withDayOfMonth(1).with(TemporalAdjusters.previous(DayOfWeek.SUNDAY));
      final LocalDate dEndDate = p_dDate.withDayOfMonth(p_dDate.lengthOfMonth()).with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
      
      // build the calendar
      LocalDate dTempDate = dStartDate;
      while(!dTempDate.isEqual(dEndDate.plusDays(1)))
      {
        if(DayOfWeek.SUNDAY.equals(dTempDate.getDayOfWeek()))
        {
          lstWeek.add(new WeekModel());
        }
        
        final DayModel mDay = new DayModel();
        mDay.setDate(dTempDate);
        mDay.setDayOfMonth(dTempDate.getDayOfMonth());
        mDay.setDayOfWeek(dTempDate.getDayOfWeek());
        mDay.setEventList(DataHelper.getEvents(dTempDate));
        
        lstWeek.get(lstWeek.size() - 1).getDayList().add(mDay);
        dTempDate = dTempDate.plusDays(1);
      }
      mCalendar.setWeekList(lstWeek);
      
      return mCalendar;
    }
    catch (final IOException e)
    {
      throw new WicketRuntimeException(e);
    }
  }
}
