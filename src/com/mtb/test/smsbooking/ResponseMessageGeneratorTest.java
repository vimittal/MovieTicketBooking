package com.mtb.test.smsbooking;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mtb.main.model.BookingResponse;
import com.mtb.main.model.Ticket;
import com.mtb.main.smsbooking.ResponseMessageGenerator;

public class ResponseMessageGeneratorTest {

	@Test
	public void shouldReturnSuccessfulResponseWithTicket() {
		// given
		BookingResponse bookingResponse = new BookingResponse();
		ResponseMessageGenerator responseMessageGenerator = new ResponseMessageGenerator();
		bookingResponse.setResult("Success");
		Ticket ticket = new Ticket(null, new String[] { "1", "2", "3" });
		bookingResponse.setTicket(ticket);
		String expectedResponse = "Success! Seats: 1, 2, 3";
		// when
		String actualResponse = responseMessageGenerator
				.getMessage(bookingResponse);

		// then
		assertEquals(expectedResponse, actualResponse);
	}

}
