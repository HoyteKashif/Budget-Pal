package com.TechTheEasyWay.page.components;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.request.Response;
import org.apache.wicket.util.string.Strings;

public class DateTextFieldWithPicker extends DateTextField{
	
	/**SerialUID*/
	private static final long serialVersionUID = -875758008040298047L;

	public DateTextFieldWithPicker(final String p_strId)
	{
		super(p_strId);
		add(getPicker());
	}
	
	public DatePicker getPicker()
	{
		return new DatePicker()
		{
			/**SerialUID */
			private static final long serialVersionUID = 8963430493187131376L;

			@Override
			public CharSequence getIconUrl()
			{
				return "";
			}
			
			@Override
			public boolean hideOnSelect()
			{
				return true;
			}

			@Override
			public boolean showOnFieldClick()
			{
				return true;
			}
			
			@Override
			public boolean autoHide()
			{
				return true;
			}
			
			@Override
			public void afterRender(final Component component)
			{
//				super.afterRender(component);

				// Append the span and img icon right after the rendering of the
				// component. Not as pretty as working with a panel etc, but works
				// for behaviors and is more efficient
				Response response = component.getResponse();
//				response.write("\n<span class=\"fa fa-calendar-o form-control-feedback left\">&nbsp;<span style=\"");
				response.write("\n<span class=\"fa fa-calendar-o form-control-feedback left\">&nbsp;</span>\n<span class=\"yui-skin-sam \">&nbsp;<span style=\"");
				if (renderOnLoad())
				{
					response.write("display:block;");
				}
				else
				{
					response.write("display:none;");
					response.write("position:absolute;");
				}

				response.write("z-index: 99999;\" id=\"");
				response.write(getEscapedComponentMarkupId());
				response.write("Dp\"></span><img style=\"");
				response.write(getIconStyle());
				response.write("\" id=\"");
				response.write(getIconId());
//				response.write("\" src=\"");
//				CharSequence iconUrl = getIconUrl();
//				response.write(Strings.escapeMarkup(iconUrl != null ? iconUrl.toString() : ""));
//				response.write("\" alt=\"");
//				CharSequence alt = getIconAltText();
//				response.write(Strings.escapeMarkup((alt != null) ? alt.toString() : ""));
//				response.write("\" title=\"");
//				CharSequence title = getIconTitle();
//				response.write(Strings.escapeMarkup((title != null) ? title.toString() : ""));
				response.write("\"/>");

				if (renderOnLoad())
				{
					response.write("<br style=\"clear:left;\"/>");
				}
				response.write("</span>");
			}
		};
	}

}
