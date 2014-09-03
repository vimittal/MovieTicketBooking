package com.mtb.main.manager;

import com.mtb.main.exception.RequestCanNotBeProcessedException;
import com.mtb.main.model.BookingRequest;
import com.mtb.main.model.BookingResponse;
import com.mtb.main.model.Ticket;
import com.mtb.main.service.BookingService;

public class BookingManager {

	private BookingService bookingService;

	public BookingManager(BookingService bookingService) {
		this.bookingService = bookingService;
	}

	public BookingResponse bookTicket(BookingRequest bookingRequest)
			throws RequestCanNotBeProcessedException {
		BookingResponse response = new BookingResponse();
		Ticket ticket = bookingService.book(bookingRequest);
		response.setResult("Success");
		response.setTicket(ticket);
		return response;
	}
}
