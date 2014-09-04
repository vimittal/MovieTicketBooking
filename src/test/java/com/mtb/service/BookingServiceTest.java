package test.java.com.mtb.service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import main.java.com.mtb.dataaccess.DataAccessInterface;
import main.java.com.mtb.exception.RequestCanNotBeProcessedException;
import main.java.com.mtb.model.BookingRequest;
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
	public void shouldIdentifyDuplicateBookingRequest() {
		dataAccessInterface.addBookingRequest(new BookingRequest("Vipul",
				"999", "02/09/14", "11am", 2));
		// given
		BookingService bookingService = new BookingService(dataAccessInterface);
		BookingRequest request = new BookingRequest("Vipul", "999", "02/09/14",
				"11am", 2);
		// then
		assertTrue(bookingService.isDuplicateRequest(request));
	}

	@Test
	public void shouldValidateBookingRequest() {
		// given
		Theatre theatre = new SimpleTheatre(50);
		Show show = new Show(theatre);
		show.setTime("11am");
		show.setDate("02/09/14");
		dataAccessInterface.addShow(show);
		BookingService bookingService = new BookingService(dataAccessInterface);
		BookingRequest request = new BookingRequest("Vipul", "999", "02/09/14",
				"11am", 2);
		// then
		assertTrue(bookingService.isShowAvailable(request));
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
		Ticket expectedTicket = new Ticket(show, new String[] { "1", "2" });

		// when
		Ticket actual = bookingService.book(request);

		// then
		assertArrayEquals(expectedTicket.getSeats(), actual.getSeats());
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
		// assertTrue(bookingService.isShowAvailable(request));
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
		// assertTrue(bookingService.isShowAvailable(request));
	}
}
