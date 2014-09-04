package test.java.com.mtb.smsbooking;

import static org.junit.Assert.assertEquals;
import main.java.com.mtb.model.BookingResponse;
import main.java.com.mtb.model.Ticket;
import main.java.com.mtb.smsbooking.ResponseMessageGenerator;

import org.junit.Test;

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
