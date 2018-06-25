package com.nmiller.calendar.data.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;

public class CalendarCriteria implements Serializable
{
  /** serialVersionUID */
  private static final long serialVersionUID = 8550298903874005463L;

  private Integer iYear;
  private Month eMonth;
  private Integer iDay;
  
  public CalendarCriteria()
  {
    
  }

  public final Month getMonth()
  {
    return eMonth;
  }

  public final void setMonth(final Month p_eMonth)
  {
    eMonth = p_eMonth;
  }

  public final int getYear()
  {
    return iYear;
  }

  public final void setYear(final int p_iYear)
  {
    iYear = p_iYear;
  }

  public final Integer getDay()
  {
    return iDay;
  }

  public final void setDay(final Integer p_iDay)
  {
    iDay = p_iDay;
  }
  
  public final LocalDate getDate()
  {
    final LocalDate dActiveDate = LocalDate.of(getYear(), getMonth(), 1);
    if(getDay() > dActiveDate.lengthOfMonth())
    {
      return dActiveDate.withDayOfMonth(dActiveDate.lengthOfMonth());
    }
    return dActiveDate.withDayOfMonth(getDay());
  }
}
