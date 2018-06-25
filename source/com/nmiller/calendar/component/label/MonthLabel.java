package com.nmiller.calendar.component.label;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;

public class MonthLabel extends BaseLabel
{
  /** serialVersionUID */
  private static final long serialVersionUID = 3402326066615460257L;
  
  /** Text style of the date part. default = full */
  private TextStyle m_eStyle = TextStyle.FULL;
  
  public MonthLabel(final String p_strId)
  {
    super(p_strId);
  }

  public MonthLabel(final String p_strId, final IModel<Month> p_oModel)
  {
    super(p_strId, null, p_oModel);
  }
  
  public MonthLabel setStyle(final TextStyle p_eStyle)
  {
    m_eStyle = p_eStyle;
    return this;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public <C> IConverter<C> getConverter(final Class<C> p_cType)
  {
    if(p_cType.isAssignableFrom(Month.class))
    {
      return (IConverter<C>) new IConverter<Month>() 
      {
        /** serialVersionUID */
        private static final long serialVersionUID = -224819175975022166L;

        @Override
        public String convertToString(final Month p_eValue, final Locale p_oLocale)
        {
          return (null == p_eValue) ? null : p_eValue.getDisplayName(m_eStyle, p_oLocale);
        }

        @Override
        public Month convertToObject(final String p_strValue, final Locale p_oLocale) throws ConversionException
        {
          // TODO: implement later...
          return null;
        }
      };
    }
    return super.getConverter(p_cType);
  }
}
