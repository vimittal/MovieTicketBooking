package main.java.com.mtb.smsbooking;

import main.java.com.mtb.exception.RequestCanNotBeProcessedException;
import main.java.com.mtb.manager.BookingManager;
import main.java.com.mtb.model.BookingRequest;
import main.java.com.mtb.model.BookingResponse;

public class SMSBookingInterface {

	private MessageParser messageParser;
	private ResponseMessageGenerator responseMessageGenerator;
	private BookingManager manager;

	public SMSBookingInterface(MessageParser messageParser,
			ResponseMessageGenerator responseMessageGenerator,
			BookingManager manager) {
		this.messageParser = messageParser;
		this.responseMessageGenerator = responseMessageGenerator;
		this.manager = manager;
	}

	public String bookTicket(String request) {

		try {
			BookingRequest bookingRequest = messageParser.getRequest(request);
			BookingResponse bookingResponse = manager
					.bookTicket(bookingRequest);
			return responseMessageGenerator.getMessage(bookingResponse);
		} catch (RequestCanNotBeProcessedException e) {
			return String.format("Failed! %s", e.getMessage());
		}
	}

}
