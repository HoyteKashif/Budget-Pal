package com.nmiller.calendar.util.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.WicketRuntimeException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import com.nmiller.calendar.data.model.EventModel;

public final class DataHelper
{
	//FIXME: Bad way of keeping the path to the data
  private static final String DATA_PATH = "C://projects//Toolbox//data";
  
  public static List<EventModel> getEvents(final LocalDate p_dDate) throws IOException
  {
    final List<EventModel> lstEvents = new ArrayList<>();
    if(null != p_dDate)
    {
      final String strYear = String.valueOf(p_dDate.getYear());
      final String strMonth = String.valueOf(p_dDate.getMonthValue());
      final String strDay = String.valueOf(p_dDate.getDayOfMonth());
      
      final File oDataFile = Paths.get(DATA_PATH, strYear, strMonth + StaticValuesHelper.XML_EXT).toFile();
      if(oDataFile.exists())
      {
        final Element oDayData = getDayDataElement(oDataFile, strDay);
        oDayData.select("event").forEach(oEvent -> {
          try
          {
            final EventModel mEvent = new EventModel();
            
            mEvent.setName(getEventDataElement(oEvent, "name").text());
            mEvent.setDescription(getEventDataElement(oEvent, "description").text());
            mEvent.setStartDate(LocalDateTime.parse(strYear + strMonth + strDay + getEventDataElement(oEvent, "start").text(),
                DateTimeFormatter.ofPattern("yyyyMdHHmm")));
            mEvent.setEndDate(LocalDateTime.parse(strYear + strMonth + strDay + getEventDataElement(oEvent, "end").text(),
                DateTimeFormatter.ofPattern("yyyyMdHHmm")));
            
            lstEvents.add(mEvent);
          } 
          catch (final IOException e)
          {
            throw new WicketRuntimeException(e);
          }
        });
      }
    }
    return lstEvents;
  }
  
  public static Element getEventDataElement(final Element p_oEvent, final String p_strProperty) throws IOException
  {
    return p_oEvent.getElementsByTag(p_strProperty).first();
  }
  
  public static Element getDayDataElement(final File p_oFile, final String strDay) throws IOException
  {
    return Jsoup.parse(p_oFile, "UTF-8").getElementById(strDay);
  }
}
