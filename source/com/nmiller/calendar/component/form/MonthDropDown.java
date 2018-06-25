package com.nmiller.calendar.component.form;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class MonthDropDown extends DropDownChoice<Month> {
	/** serialVersionUID */
	private static final long serialVersionUID = -6648116854820290614L;

	public MonthDropDown(final String p_strId) {
		this(p_strId, TextStyle.FULL);
	}

	public MonthDropDown(final String p_strId, final TextStyle p_eStyle) {
		super(p_strId);
		setChoices(Model.ofList(Arrays.asList(Month.values())));
		setChoiceRenderer(new IChoiceRenderer<Month>() {
			/** serialVersionUID */
			private static final long serialVersionUID = 2538076383889348266L;

			@Override
			public Object getDisplayValue(final Month p_oMonth) {
				return (null != p_oMonth) ? p_oMonth.getDisplayName(p_eStyle, getRequest().getLocale()) : null;
			}

			@Override
			public String getIdValue(final Month p_oMonth, final int p_iIndex) {
				return String.valueOf(p_iIndex);
			}

			@Override
			public Month getObject(String id, IModel<? extends List<? extends Month>> choices) {
				// TODO Auto-generated method stub
				return null;
			}

		});
	}
}
