package main.java.com.mtb.dataaccess;

import java.util.ArrayList;

import main.java.com.mtb.model.BookingRequest;
import main.java.com.mtb.model.Show;
import main.java.com.mtb.model.Ticket;

public class DataAccessInterface {
	private ArrayList<BookingRequest> bookingRequests = new ArrayList<BookingRequest>();
	private ArrayList<Show> shows = new ArrayList<Show>();
	private ArrayList<Ticket> tickets = new ArrayList<Ticket>();

	public ArrayList<BookingRequest> getBookingRequests() {
		return bookingRequests;
	}

	public ArrayList<Show> getShows() {
		return shows;
	}

	public void addShow(Show show) {
		shows.add(show);
	}

	public void addBookingRequest(BookingRequest bookingRequest) {
		bookingRequests.add(bookingRequest);
	}

	public void addTicket(Ticket ticket) {
		tickets.add(ticket);
	}

}
