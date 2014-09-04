package main.java.com.mtb.smsbooking;

import main.java.com.mtb.model.BookingResponse;

public class ResponseMessageGenerator {

	public String getMessage(BookingResponse bookingResponse) {

		StringBuffer ticketString = new StringBuffer("");
		String[] seats = bookingResponse.getTicket().getSeats();
		for (int i = 0; i < seats.length - 1; i++) {
			ticketString.append(seats[i]);
			ticketString.append(", ");
		}
		ticketString.append(seats[seats.length - 1]);
		return String.format("%s! Seats: %s", bookingResponse.getResult(),
				ticketString);
	}
}
