package com.mtb.main.dataaccess;

import java.util.ArrayList;

import com.mtb.main.model.BookingRequest;
import com.mtb.main.model.Show;
import com.mtb.main.model.Ticket;

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
