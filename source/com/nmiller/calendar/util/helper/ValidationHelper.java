package com.nmiller.calendar.util.helper;

import java.time.LocalDate;

public final class ValidationHelper
{
  public static boolean hasText(final String p_str)
  {
    return null != p_str && p_str.trim().length() > 0;
  }
  
  public static boolean isEqual(final LocalDate p_d1, final LocalDate p_d2)
  {
    return (p_d1 == null && p_d2 == null) || (null != p_d1 && null != p_d2 && p_d1.isEqual(p_d2));
  }

  public static String getTrimmedText(final String p_str)
  {
    if (null == p_str)
    {
      return "";
    }
    return p_str.trim();
  }
}
