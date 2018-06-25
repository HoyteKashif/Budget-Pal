package com.nmiller.calendar.data.model;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class DayModel implements Serializable {
	/** serialVersionUID */
	private static final long serialVersionUID = 1584505946279600422L;

	private LocalDate dDate;

	private Integer iDayOfMonth;
	private DayOfWeek eDayOfWeek;

	private List<EventModel> lstEvent;

	public DayModel() {
	}

	public final LocalDate getDate() {
		return dDate;
	}

	public final void setDate(final LocalDate p_dDate) {
		dDate = p_dDate;
	}

	public final Integer getDayOfMonth() {
		return iDayOfMonth;
	}

	public final void setDayOfMonth(final int p_iDayOfMonth) {
		iDayOfMonth = p_iDayOfMonth;
	}

	public final DayOfWeek getDayOfWeek() {
		return eDayOfWeek;
	}

	public final void setDayOfWeek(final DayOfWeek p_eDayOfWeek) {
		eDayOfWeek = p_eDayOfWeek;
	}

	public final List<EventModel> getEventList() {
		return lstEvent;
	}

	public final void setEventList(final List<EventModel> p_lstEvent) {
		lstEvent = p_lstEvent;
	}
}
