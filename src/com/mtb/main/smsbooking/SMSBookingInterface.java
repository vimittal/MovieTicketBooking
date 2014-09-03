package com.mtb.main.smsbooking;

import com.mtb.main.exception.RequestCanNotBeProcessedException;
import com.mtb.main.manager.BookingManager;
import com.mtb.main.model.BookingRequest;
import com.mtb.main.model.BookingResponse;

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
