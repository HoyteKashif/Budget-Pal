package com.nmiller.calendar.util.helper;

import java.time.LocalDate;

public final class CalendarHelper
{
  /**
   * Get the valid day of month for the given date.
   * 
   * @param p_iActiveDay     currently active day
   * @param p_dNewDate       new date to find a day for
   * @return int             the valid day of month
   */
  public static int getValidDayOfMonth(final Integer p_iActiveDay, final LocalDate p_dNewDate)
  {
    if(null == p_iActiveDay)
    {
      return 1;
    }
    else if(p_iActiveDay > p_dNewDate.lengthOfMonth())
    {
      return p_dNewDate.lengthOfMonth();
    }
    return p_iActiveDay;
  }
}
