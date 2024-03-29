package test.java.com.mtb.smsbooking;

import static org.junit.Assert.assertEquals;
import main.java.com.mtb.dataaccess.DataAccessInterface;
import main.java.com.mtb.model.Show;
import main.java.com.mtb.model.SimpleTheatre;
import main.java.com.mtb.model.Theatre;
import main.java.com.mtb.service.BookingService;
import main.java.com.mtb.smsbooking.MessageParser;
import main.java.com.mtb.smsbooking.ResponseMessageGenerator;
import main.java.com.mtb.smsbooking.SMSBookingInterface;

import org.junit.Before;
import org.junit.Test;

public class SMSBookingInterfaceTest {

	private SMSBookingInterface smsBookingInterface;

	@Before
	public void setup() {
		DataAccessInterface dataAccessInterface = new DataAccessInterface();
		Theatre theatre = new SimpleTheatre(50);
		Show show = new Show(theatre);
		show.setTime("11am");
		show.setDate("03/09/14");
		dataAccessInterface.addShow(show);
		BookingService bookingService = new BookingService(dataAccessInterface);
		MessageParser messageParser = new MessageParser();
		ResponseMessageGenerator responseMessageGenerator = new ResponseMessageGenerator();
		smsBookingInterface = new SMSBookingInterface(messageParser,
				responseMessageGenerator, bookingService);
		// smsBookingInterface = Factory.getSmsBookingInterface();
	}

	@Test
	public void shouldReturnSuccessMessageWithTicketForValidBookingMessage() {
		// given
		String request = "Vipul 999 03/09/14 11am 2";
		String expectedResponse = "Success! Seats: 1, 2";
		// when
		String actualResponse = smsBookingInterface.bookTicket(request);

		// then
		assertEquals(expectedResponse, actualResponse);
	}

	@Test
	public void shouldReturnFailureWhenNoMoreSeatsAvailable() {
		// given
		String request = "Mittal 999 03/09/14 11am 2";

		String initialBookingRequest = "init 3232 03/09/14 11am 49";
		smsBookingInterface.bookTicket(initialBookingRequest);
		String expectedResponse = "Failed! Seats not available";

		// when
		String actualResponse = smsBookingInterface.bookTicket(request);

		// then
		assertEquals(expectedResponse, actualResponse);
	}

	@Test
	public void shouldReturnFailureDuplicateRequest() {
		// given
		String request = "Vipul 999 03/09/14 11am 2";
		String initialBookingRequest = "Vipul 999 03/09/14 11am 2";
		smsBookingInterface.bookTicket(initialBookingRequest);
		String expectedResponse = "Failed! Duplicate request";

		// when
		String actualResponse = smsBookingInterface.bookTicket(request);

		// then
		assertEquals(expectedResponse, actualResponse);
	}

	@Test
	public void shouldReturnFailureInvalidRequest() {
		// given
		String request = "Vipul 999 03/09/14 11am";

		String expectedResponse = "Failed! Invalid booking";

		// when
		String actualResponse = smsBookingInterface.bookTicket(request);

		// then
		assertEquals(expectedResponse, actualResponse);
	}

	@Test
	public void shouldReturnShowNotAvailable() {
		// given
		String request = "Vipul 999 03/09/15 11am 2";

		String expectedResponse = "Failed! Show not available";

		// when
		String actualResponse = smsBookingInterface.bookTicket(request);

		// then
		assertEquals(expectedResponse, actualResponse);
	}

}
