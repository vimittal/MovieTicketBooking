package test.java.com.mtb.service;

import static org.junit.Assert.*;
import main.java.com.mtb.dataaccess.DataAccessInterface;
import main.java.com.mtb.exception.RequestCanNotBeProcessedException;
import main.java.com.mtb.model.BookingRequest;
import main.java.com.mtb.model.BookingResponse;
import main.java.com.mtb.model.Show;
import main.java.com.mtb.model.SimpleTheatre;
import main.java.com.mtb.model.Theatre;
import main.java.com.mtb.model.Ticket;
import main.java.com.mtb.service.BookingService;

import org.junit.Before;
import org.junit.Test;

public class BookingServiceTest {

	private DataAccessInterface dataAccessInterface;

	@Before
	public void setup() {
		dataAccessInterface = new DataAccessInterface();
	}

	@Test
	public void shouldBookTheShowForValidBookingRequest()
			throws RequestCanNotBeProcessedException {
		// given
		Theatre theatre = new SimpleTheatre(50);
		Show show = new Show(theatre);
		show.setTime("11am");
		show.setDate("02/09/14");
		dataAccessInterface.addShow(show);
		BookingService bookingService = new BookingService(dataAccessInterface);
		BookingRequest request = new BookingRequest("Vipul", "999", "02/09/14",
				"11am", 2);
		Ticket ticket = new Ticket(show, new String[] { "1", "2" });
		BookingResponse expectedResponse = new BookingResponse();
		expectedResponse.setResult("Success");
		expectedResponse.setTicket(ticket);
		// when
		BookingResponse actualResponse = bookingService.book(request);

		// then
		assertEquals(expectedResponse.getResult(), actualResponse.getResult());
		assertArrayEquals(expectedResponse.getTicket().getSeats(),
				actualResponse.getTicket().getSeats());
	}

	@Test(expected = RequestCanNotBeProcessedException.class)
	public void shouldThroughExceptionWhenShowIsNotAvaialble()
			throws RequestCanNotBeProcessedException {
		// given
		Theatre theatre = new SimpleTheatre(50);
		Show show = new Show(theatre);
		show.setTime("11am");
		show.setDate("02/09/14");
		show.book(49);
		dataAccessInterface.addShow(show);
		BookingService bookingService = new BookingService(dataAccessInterface);
		BookingRequest request = new BookingRequest("Vipul", "999", "02/09/14",
				"11am", 2);
		// then
		bookingService.book(request);
	}

	@Test(expected = RequestCanNotBeProcessedException.class)
	public void shouldThroughExceptionForDuplicateRequest()
			throws RequestCanNotBeProcessedException {
		// given
		dataAccessInterface.addBookingRequest(new BookingRequest("Vipul",
				"999", "02/09/14", "11am", 2));
		// given
		BookingService bookingService = new BookingService(dataAccessInterface);
		BookingRequest request = new BookingRequest("Vipul", "999", "02/09/14",
				"11am", 2);

		// then
		bookingService.book(request);
	}

	// @Test(expected = RequestCanNotBeProcessedException.class)
	public void shouldThroughExceptionIfShowIsNotAvailable()
			throws RequestCanNotBeProcessedException {
		// given
		BookingService bookingService = new BookingService(dataAccessInterface);
		BookingRequest request = new BookingRequest("Vipul", "999", "02/09/14",
				"11am", 2);

		// then
		bookingService.book(request);
	}
}
