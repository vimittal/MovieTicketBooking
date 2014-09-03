package com.mtb.main.model;

public class Ticket {

	private String[] seats;
	private Show show;

	public Ticket(Show show, String[] seats) {
		this.seats = seats;
		this.show = show;
	}

	public String[] getSeats() {
		return seats;
	}

	public Show getShow() {
		return show;
	}

}
