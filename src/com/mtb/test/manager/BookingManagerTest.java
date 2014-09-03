package com.mtb.test.manager;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mtb.main.dataaccess.DataAccessInterface;
import com.mtb.main.exception.RequestCanNotBeProcessedException;
import com.mtb.main.manager.BookingManager;
import com.mtb.main.model.BookingRequest;
import com.mtb.main.model.BookingResponse;
import com.mtb.main.model.Show;
import com.mtb.main.model.SimpleTheatre;
import com.mtb.main.model.Theatre;
import com.mtb.main.service.BookingService;

public class BookingManagerTest {

	@Test
	public void shouldGetSuccessResponseForValidBookingRequest()
			throws RequestCanNotBeProcessedException {
		// Given
		DataAccessInterface dataAccessInterface = new DataAccessInterface();
		Theatre theatre = new SimpleTheatre(50);
		Show show = new Show(theatre);
		show.setTime("11am");
		show.setDate("02/09/14");
		dataAccessInterface.addShow(show);
		BookingService bookingService = new BookingService(dataAccessInterface);
		BookingManager manager = new BookingManager(bookingService);
		BookingRequest bookingRequest = new BookingRequest("vipul", "999",
				"02/08/14", "11am", 2);
		String expectedResult = "Success";

		// When
		BookingResponse response = manager.bookTicket(bookingRequest);

		// Then
		assertEquals(expectedResult, response.getResult());
	}

	@Test(expected = RequestCanNotBeProcessedException.class)
	public void shouldGetFailedResponseShowIsNotAvailable()
			throws RequestCanNotBeProcessedException {
		// Given
		DataAccessInterface dataAccessInterface = new DataAccessInterface();
		Theatre theatre = new SimpleTheatre(50);
		Show show = new Show(theatre);
		show.setTime("11am");
		show.setDate("02/09/14");
		show.book(49);
		dataAccessInterface.addShow(show);
		BookingService bookingService = new BookingService(dataAccessInterface);
		BookingManager manager = new BookingManager(bookingService);
		BookingRequest bookingRequest = new BookingRequest("vipul", "999",
				"02/08/14", "11am", 2);

		// When
		manager.bookTicket(bookingRequest);

	}

}
