package com.mtb.main.model;

public class Show {

	private Theatre theatre;
	private String date;
	private String time;

	public Show(Theatre theatre) {
		this.theatre = theatre;
	}

	public boolean areSeatsAvailable(int seats) {
		return theatre.areSeatsAvailable(seats);
	}

	public String[] book(int seats) {
		return theatre.book(seats);
	}

	public String getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}

	public boolean equals(String date, String time) {
		return date.equals(date) && time.equals(time);
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
