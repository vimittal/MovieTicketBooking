package test.java.com.mtb.smsbooking;

import static org.junit.Assert.assertEquals;
import main.java.com.mtb.exception.RequestCanNotBeProcessedException;
import main.java.com.mtb.model.BookingRequest;
import main.java.com.mtb.smsbooking.MessageParser;

import org.junit.Test;

public class MessageParserTest {

	@Test
	public void shouldReturnBookingRequestForAValidMessage()
			throws RequestCanNotBeProcessedException {
		// given
		MessageParser messageParser = new MessageParser();
		BookingRequest expectedRequest = new BookingRequest("Vipul", "999",
				"03/09/14", "11am", 2);
		// when
		BookingRequest actualRequest = messageParser
				.getRequest("Vipul 999 03/09/14 11am 2");
		// then
		assertEquals(expectedRequest, actualRequest);
	}

	@Test(expected = RequestCanNotBeProcessedException.class)
	public void shouldThroughExceptionWhenMessageIsNull()
			throws RequestCanNotBeProcessedException {
		// given
		MessageParser smsCommunication = new MessageParser();
		// when
		smsCommunication.getRequest(null);
	}

	@Test(expected = RequestCanNotBeProcessedException.class)
	public void shouldThroughExceptionWhenIncorrectMessageFormat()
			throws RequestCanNotBeProcessedException {
		// given
		MessageParser smsCommunication = new MessageParser();
		// when
		smsCommunication.getRequest("Vipul 999 03/09/14 11am");
	}

	@Test(expected = RequestCanNotBeProcessedException.class)
	public void shouldThroughExceptionWhenWrongDateFormat()
			throws RequestCanNotBeProcessedException {
		// given
		MessageParser smsCommunication = new MessageParser();
		// when
		smsCommunication.getRequest("Vipul 999 03/14 11am 2");
	}

	@Test(expected = RequestCanNotBeProcessedException.class)
	public void shouldThroughExceptionWhenWrongTimeFormat()
			throws RequestCanNotBeProcessedException {
		// given
		MessageParser smsCommunication = new MessageParser();
		// when
		smsCommunication.getRequest("Vipul 999 03/09/14 11c 2");
	}

	@Test(expected = RequestCanNotBeProcessedException.class)
	public void shouldThroughExceptionWhenInvalidSeats()
			throws RequestCanNotBeProcessedException {
		// given
		MessageParser smsCommunication = new MessageParser();
		// when
		smsCommunication.getRequest("Vipul 999 03/09/14 11am 2a");
	}
}
