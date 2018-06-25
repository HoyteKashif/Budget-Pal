package com.nmiller.calendar.data.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class EventModel implements Serializable
{
  /** serialVersionUID */
  private static final long serialVersionUID = 2926869909991313057L;
  
  private String strName;
  private String strDescription;
  private LocalDateTime dtStartDate;
  private LocalDateTime dtEndDate;
  
  public EventModel()
  {
    
  }

  public final String getName()
  {
    return strName;
  }

  public final void setName(final String p_strName)
  {
    strName = p_strName;
  }

  public final String getDescription()
  {
    return strDescription;
  }

  public final void setDescription(final String p_strDescription)
  {
    strDescription = p_strDescription;
  }

  public final LocalDateTime getStartDate()
  {
    return dtStartDate;
  }

  public final void setStartDate(final LocalDateTime p_dStartDate)
  {
    dtStartDate = p_dStartDate;
  }

  public final LocalDateTime getEndDate()
  {
    return dtEndDate;
  }

  public final void setEndDate(final LocalDateTime p_dEndDate)
  {
    dtEndDate = p_dEndDate;
  }
}