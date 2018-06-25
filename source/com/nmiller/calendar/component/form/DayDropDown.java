package com.nmiller.calendar.component.form;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.util.ListModel;

public class DayDropDown extends DropDownChoice<Integer>
{
  /** serialVersionUID */
  private static final long serialVersionUID = 4775228608036138224L;

  public DayDropDown(final String p_strId, final IModel<LocalDate> p_oActiveDateModel)
  {
    super(p_strId);
    setChoices(new ListModel<Integer>()
    {
      /** serialVersionUID */
      private static final long serialVersionUID = -6817467869649372571L;

      @Override
      public List<Integer> getObject()
      {
        final List<Integer> lstDays = new ArrayList<>();
        if(null != p_oActiveDateModel && null != p_oActiveDateModel.getObject())
        {
          for(int i = 1; i <= p_oActiveDateModel.getObject().lengthOfMonth(); i++)
          {
            lstDays.add(i);
          }
        }
        return lstDays;
      }
    });
  }
}
