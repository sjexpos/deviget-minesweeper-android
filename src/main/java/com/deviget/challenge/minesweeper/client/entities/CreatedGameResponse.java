package com.deviget.challenge.minesweeper.client.entities;

import java.util.Date;

public class CreatedGameResponse {
	private String id;
	private Date start;
	private String state;

	public CreatedGameResponse() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
