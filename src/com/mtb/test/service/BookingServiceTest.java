package com.mtb.test.service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.mtb.main.dataaccess.DataAccessInterface;
import com.mtb.main.exception.RequestCanNotBeProcessedException;
import com.mtb.main.model.BookingRequest;
import com.mtb.main.model.Show;
import com.mtb.main.model.SimpleTheatre;
import com.mtb.main.model.Theatre;
import com.mtb.main.model.Ticket;
import com.mtb.main.service.BookingService;

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
