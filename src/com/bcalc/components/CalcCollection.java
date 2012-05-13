package com.bcalc.components;

import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;

public class CalcCollection extends ArrayList<Calc> implements Serializable {
	/**
	 * Serial Version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	private String title;
	private Date date;
	private long id;
	
	public CalcCollection(long id, String title, Date date) {
		this.id = id;
		this.title = title;
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
