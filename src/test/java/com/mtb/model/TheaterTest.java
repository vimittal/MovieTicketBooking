package test.java.com.mtb.model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import main.java.com.mtb.model.SimpleTheatre;
import main.java.com.mtb.model.Theatre;

import org.junit.Test;

public class TheaterTest {

	@Test
	public void shouldVarifyWhenSeatsAreAvailabel() {
		// given
		Theatre theatre = new SimpleTheatre(50);

		// then
		assertTrue(theatre.areSeatsAvailable(2));
	}

	@Test
	public void shouldVarifyWhenSeatsAllSeatsAreBooked() {
		// given
		Theatre theatre = new SimpleTheatre(2);
		theatre.book(2);

		// then
		assertFalse(theatre.areSeatsAvailable(2));
	}

	@Test
	public void shouldReturnSeatNumbersAfterSuccessfulBooking() {
		// given
		Theatre theatre = new SimpleTheatre(50);
		String[] expectedSeats = { "1", "2" };

		// when
		String[] actualSeats = theatre.book(2);

		// then
		assertArrayEquals(expectedSeats, actualSeats);
	}

}
