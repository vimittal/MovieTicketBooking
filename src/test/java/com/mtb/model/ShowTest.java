package test.java.com.mtb.model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import main.java.com.mtb.model.Show;
import main.java.com.mtb.model.SimpleTheatre;
import main.java.com.mtb.model.Theatre;

import org.junit.Test;

public class ShowTest {

	@Test
	public void shouldVarifyIfSeatsNoMoreSeatsAreAvailable() {
		// given
		Theatre theatre = new SimpleTheatre(50);
		Show show = new Show(theatre);
		show.book(50);
		// then
		assertFalse(show.areSeatsAvailable(2));
	}

	@Test
	public void shouldVarifyIfSeatsAreAvailable() {
		// given
		Theatre theatre = new SimpleTheatre(50);
		Show show = new Show(theatre);
		show.book(10);
		// then
		assertTrue(show.areSeatsAvailable(2));
	}

	@Test
	public void shouldReturnSeatNumbersAfterSuccessfulBooking() {
		// given
		Theatre theatre = new SimpleTheatre(50);
		Show show = new Show(theatre);
		String[] expectedSeats = { "1", "2" };

		// when
		String[] actualSeats = show.book(2);

		// then
		assertArrayEquals(expectedSeats, actualSeats);
	}
}
