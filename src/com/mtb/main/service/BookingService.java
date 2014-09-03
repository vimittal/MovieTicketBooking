package com.mtb.main.service;

import java.util.List;

import com.mtb.main.dataaccess.DataAccessInterface;
import com.mtb.main.exception.RequestCanNotBeProcessedException;
import com.mtb.main.model.BookingRequest;
import com.mtb.main.model.Show;
import com.mtb.main.model.Ticket;

public class BookingService {

	private DataAccessInterface dataAccessInterface;

	public BookingService(DataAccessInterface dataAccessInterface) {
		this.dataAccessInterface = dataAccessInterface;
	}

	private void validateRequest(BookingRequest bookingRequest)
			throws RequestCanNotBeProcessedException {
		if (isDuplicateRequest(bookingRequest))
			throw new RequestCanNotBeProcessedException("Duplicate request");
		if (!isShowAvailable(bookingRequest))
			throw new RequestCanNotBeProcessedException("Show not available");
	}

	public boolean isShowAvailable(BookingRequest bookingRequest) {
		Show show = findShow(bookingRequest);
		return show != null & show.areSeatsAvailable(bookingRequest.getSeats());
	}

	private Show findShow(BookingRequest bookingRequest) {
		List<Show> shows = dataAccessInterface.getShows();
		for (Show show : shows) {
			if (show.equals(bookingRequest.getDate(), bookingRequest.getTime()))
				return show;
		}
		return null;
	}

	public boolean isDuplicateRequest(BookingRequest request) {
		List<BookingRequest> requests = dataAccessInterface
				.getBookingRequests();
		return requests.contains(request);
	}

	public Ticket book(BookingRequest request) throws RequestCanNotBeProcessedException {
		validateRequest(request);
		Show show = findShow(request);
		String[] seats = show.book(request.getSeats());
		dataAccessInterface.addBookingRequest(request);
		Ticket ticket = new Ticket(show, seats);
		dataAccessInterface.addTicket(ticket);
		return ticket;
	}
}
