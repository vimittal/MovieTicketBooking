package com.mtb.main.factory;

import com.mtb.main.dataaccess.DataAccessInterface;
import com.mtb.main.manager.BookingManager;
import com.mtb.main.model.Show;
import com.mtb.main.model.SimpleTheatre;
import com.mtb.main.model.Theatre;
import com.mtb.main.service.BookingService;
import com.mtb.main.smsbooking.MessageParser;
import com.mtb.main.smsbooking.ResponseMessageGenerator;
import com.mtb.main.smsbooking.SMSBookingInterface;

public class Factory {
	private static SMSBookingInterface smsBookingInterface;
	static {
		DataAccessInterface dataAccessInterface = new DataAccessInterface();
		Theatre theatre = new SimpleTheatre(50);
		Show show = new Show(theatre);
		show.setTime("11am");
		show.setDate("03/09/14");
		dataAccessInterface.addShow(show);
		show = new Show(theatre);
		show.setTime("3pm");
		show.setDate("03/09/14");
		dataAccessInterface.addShow(show);
		show = new Show(theatre);
		show.setTime("8pm");
		show.setDate("03/09/14");
		dataAccessInterface.addShow(show);
		show.setTime("11am");
		show.setDate("04/09/14");
		dataAccessInterface.addShow(show);
		show = new Show(theatre);
		show.setTime("3pm");
		show.setDate("04/09/14");
		dataAccessInterface.addShow(show);
		show = new Show(theatre);
		show.setTime("8pm");
		show.setDate("04/09/14");
		dataAccessInterface.addShow(show);

		BookingService bookingService = new BookingService(dataAccessInterface);
		BookingManager manager = new BookingManager(bookingService);
		MessageParser messageParser = new MessageParser();
		ResponseMessageGenerator responseMessageGenerator = new ResponseMessageGenerator();
		smsBookingInterface = new SMSBookingInterface(messageParser,
				responseMessageGenerator, manager);
	}

	public static SMSBookingInterface getSmsBookingInterface() {
		return smsBookingInterface;
	}
}
