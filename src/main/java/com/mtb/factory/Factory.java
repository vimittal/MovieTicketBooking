package main.java.com.mtb.factory;

import main.java.com.mtb.dataaccess.DataAccessInterface;
import main.java.com.mtb.model.Show;
import main.java.com.mtb.model.SimpleTheatre;
import main.java.com.mtb.service.BookingService;
import main.java.com.mtb.smsbooking.MessageParser;
import main.java.com.mtb.smsbooking.ResponseMessageGenerator;
import main.java.com.mtb.smsbooking.SMSBookingInterface;

public class Factory {
	private static SMSBookingInterface smsBookingInterface;
	static {
		DataAccessInterface dataAccessInterface = new DataAccessInterface();
		Show show = new Show(new SimpleTheatre(50));
		show.setTime("11am");
		show.setDate("03/09/14");
		dataAccessInterface.addShow(show);
		show = new Show(new SimpleTheatre(50));
		show.setTime("3pm");
		show.setDate("03/09/14");
		dataAccessInterface.addShow(show);
		show = new Show(new SimpleTheatre(50));
		show.setTime("8pm");
		show.setDate("03/09/14");
		dataAccessInterface.addShow(show);
		show.setTime("11am");
		show.setDate("04/09/14");
		dataAccessInterface.addShow(show);
		show = new Show(new SimpleTheatre(50));
		show.setTime("3pm");
		show.setDate("04/09/14");
		dataAccessInterface.addShow(show);
		show = new Show(new SimpleTheatre(50));
		show.setTime("8pm");
		show.setDate("04/09/14");
		dataAccessInterface.addShow(show);

		BookingService bookingService = new BookingService(dataAccessInterface);
		MessageParser messageParser = new MessageParser();
		ResponseMessageGenerator responseMessageGenerator = new ResponseMessageGenerator();
		smsBookingInterface = new SMSBookingInterface(messageParser,
				responseMessageGenerator, bookingService);
	}

	public static SMSBookingInterface getSmsBookingInterface() {
		return smsBookingInterface;
	}
}
