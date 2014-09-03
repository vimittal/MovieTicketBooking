package com.mtb.main.model;

public class BookingResponse {

	private String result;
	private Ticket ticket;
	private String message;

	public String getResult() {
		return result;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
