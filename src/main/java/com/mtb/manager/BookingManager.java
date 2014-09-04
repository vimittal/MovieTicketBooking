package main.java.com.mtb.manager;

import main.java.com.mtb.exception.RequestCanNotBeProcessedException;
import main.java.com.mtb.model.BookingRequest;
import main.java.com.mtb.model.BookingResponse;
import main.java.com.mtb.model.Ticket;
import main.java.com.mtb.service.BookingService;

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
