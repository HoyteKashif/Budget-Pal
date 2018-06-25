package com.ksolutions.calendar.panel;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.nmiller.calendar.component.label.AutoHideLabel;
import com.nmiller.calendar.data.model.DayModel;
import com.nmiller.calendar.data.model.EventModel;
import com.nmiller.calendar.util.helper.ValidationHelper;

public class DayPanel  extends Panel{
	/** Serial UID */
	private static final long serialVersionUID = 8978893478322194037L;
	
	public DayPanel(final String p_strId, final DayModel p_DayModel)
	{
		super(p_strId);
		
		if (p_DayModel == null || p_DayModel.getDayOfMonth() == null)
		{
			throw new NullPointerException("Null DayModel");
		}
		
		System.out.println(p_DayModel.getDate());
		
		addDayOfMonthLabel();
		
		add(getEventList(new Model<DayModel>(p_DayModel)));
	    
		final LocalDate activeDate = LocalDate.of(Year.now().getValue(), Month.JULY.getValue(), 22);
		
		if(null != p_DayModel.getDate())
	        {
	          if(ValidationHelper.isEqual(activeDate, p_DayModel.getDate()))
	          {
	            add(AttributeModifier.append("class", "active"));
	          }
	          else if(ValidationHelper.isEqual(LocalDate.now(), p_DayModel.getDate()))
	          {
	            add(AttributeModifier.append("class", "today"));
	          }
	          else if(activeDate.getMonth() != p_DayModel.getDate().getMonth())
	          {
	            add(AttributeModifier.append("class", "inactive"));
	          }
	        }
	}
	
	public void addDayOfMonthLabel()
	{
		final Label dayLabel = new Label("iDayOfMonth");
		
		add(dayLabel);
	}

	/**
	   * Get the list of events for the given day.
	   * 
	   * @param p_oDayModel               the day
	   * @return ListView<EventModel>
	   */
	  private ListView<EventModel> getEventList(final IModel<DayModel> p_oDayModel)
	  {
	    return new PropertyListView<EventModel>("lstEvent")
	    {
	      /** serialVersionUID */
	      private static final long serialVersionUID = 7988167210413995594L;

	      @Override
	      protected void populateItem(final ListItem<EventModel> p_oItem)
	      {
	        p_oItem.add(new AutoHideLabel("strName"));
	      }
	      
	      @Override
	      public void onConfigure()
	      {
	        setVisible(null != getModelObject() && !getModelObject().isEmpty());
	      }
	    };
	  }
}
