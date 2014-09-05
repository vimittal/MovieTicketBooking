package main.java.com.mtb.service;

import main.java.com.mtb.dataaccess.DataAccessInterface;
import main.java.com.mtb.exception.RequestCanNotBeProcessedException;
import main.java.com.mtb.model.BookingRequest;
import main.java.com.mtb.model.BookingResponse;
import main.java.com.mtb.model.Show;
import main.java.com.mtb.model.Ticket;

public class BookingService {

	private DataAccessInterface dataAccessInterface;

	public BookingService(DataAccessInterface dataAccessInterface) {
		this.dataAccessInterface = dataAccessInterface;
	}

	public BookingResponse book(BookingRequest request)
			throws RequestCanNotBeProcessedException {
		dataAccessInterface.addBookingRequest(request);

		Show show = dataAccessInterface.getShow(request.getDate(),
				request.getTime());
		Ticket ticket = show.book(request.getSeats());

		dataAccessInterface.addTicket(ticket);
		return buildResponse(ticket);
	}

	private BookingResponse buildResponse(Ticket ticket) {
		BookingResponse response = new BookingResponse();
		response.setResult("Success");
		response.setTicket(ticket);
		return response;
	}
}
