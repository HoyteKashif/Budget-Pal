package com.nmiller.calendar.component.form;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;

public class YearDropDown extends DropDownChoice<Integer>
{
  /** serialVersionUID */
  private static final long serialVersionUID = -3309045007612534591L;

  public YearDropDown(final String p_strId)
  {
    super(p_strId);
    
    final List<Integer> lstChoices = new ArrayList<>();
    for(int i = 2017; i <= LocalDate.now().getYear() + 2; i++)
    {
      lstChoices.add(i);
    }
    setChoices(lstChoices);
  }
}
