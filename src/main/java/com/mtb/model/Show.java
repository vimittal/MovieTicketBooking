package main.java.com.mtb.model;

import main.java.com.mtb.exception.RequestCanNotBeProcessedException;

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

	public Ticket book(int seats) throws RequestCanNotBeProcessedException {
		if (!areSeatsAvailable(seats))
			throw new RequestCanNotBeProcessedException("Seats not available");
		return new Ticket(this, theatre.book(seats));
	}

	public String getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}

	public boolean equals(String date, String time) {
		return this.date.equals(date) && this.time.equals(time);
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
