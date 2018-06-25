package com.nmiller.calendar.data.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class CalendarModel implements Serializable
{
  /** serialVersionUID */
  private static final long serialVersionUID = 8378018576617124316L;
  
  private LocalDate dDate;
  
  private int iYear;
  private Month eMonth;
  private int iDay;
  
  private List<WeekModel> lstWeek = new ArrayList<>(5);
  
  public CalendarModel()
  {
    
  }
  
  public final LocalDate getDate()
  {
    return dDate;
  }
  
  public final void setDate(final LocalDate p_dDate)
  {
    dDate = p_dDate;
  }

  public final int getYear()
  {
    return iYear;
  }

  public final void setYear(final int p_iYear)
  {
    this.iYear = p_iYear;
  }

  public final Month getMonth()
  {
    return eMonth;
  }

  public final void setMonth(final Month p_eMonth)
  {
    this.eMonth = p_eMonth;
  }

  public final int getDay()
  {
    return iDay;
  }

  public final void setDay(final int p_iDay)
  {
    this.iDay = p_iDay;
  }

  public List<WeekModel> getWeekList()
  {
    return lstWeek;
  }

  public void setWeekList(final List<WeekModel> p_lstWeek)
  {
    this.lstWeek = p_lstWeek;
  }
}
