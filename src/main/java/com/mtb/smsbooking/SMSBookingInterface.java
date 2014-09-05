package main.java.com.mtb.smsbooking;

import main.java.com.mtb.exception.RequestCanNotBeProcessedException;
import main.java.com.mtb.model.BookingRequest;
import main.java.com.mtb.model.BookingResponse;
import main.java.com.mtb.service.BookingService;

public class SMSBookingInterface {

	private MessageParser messageParser;
	private ResponseMessageGenerator responseMessageGenerator;
	private BookingService service;

	public SMSBookingInterface(MessageParser messageParser,
			ResponseMessageGenerator responseMessageGenerator,
			BookingService service) {
		this.messageParser = messageParser;
		this.responseMessageGenerator = responseMessageGenerator;
		this.service = service;
	}

	public String bookTicket(String request) {
		try {
			BookingRequest bookingRequest = messageParser.getRequest(request);
			BookingResponse bookingResponse = service.book(bookingRequest);
			return responseMessageGenerator.getMessage(bookingResponse);
		} catch (RequestCanNotBeProcessedException e) {
			return String.format("Failed! %s", e.getMessage());
		}
	}

}
