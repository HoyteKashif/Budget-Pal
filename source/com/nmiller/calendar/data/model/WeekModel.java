package com.nmiller.calendar.data.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WeekModel implements Serializable
{
  /** serialVersionUID */
  private static final long serialVersionUID = -2735082655449686486L;
  
  private List<DayModel> lstDay = new ArrayList<>(7);
  
  public WeekModel()
  {
    
  }

  public final List<DayModel> getDayList()
  {
    return lstDay;
  }

  public final void setDayList(final List<DayModel> p_lstDay)
  {
    lstDay = p_lstDay;
  }
}
