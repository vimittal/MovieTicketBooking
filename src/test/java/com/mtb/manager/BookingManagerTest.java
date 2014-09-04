package test.java.com.mtb.manager;

import static org.junit.Assert.assertEquals;
import main.java.com.mtb.dataaccess.DataAccessInterface;
import main.java.com.mtb.exception.RequestCanNotBeProcessedException;
import main.java.com.mtb.manager.BookingManager;
import main.java.com.mtb.model.BookingRequest;
import main.java.com.mtb.model.BookingResponse;
import main.java.com.mtb.model.Show;
import main.java.com.mtb.model.SimpleTheatre;
import main.java.com.mtb.model.Theatre;
import main.java.com.mtb.service.BookingService;

import org.junit.Test;

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
