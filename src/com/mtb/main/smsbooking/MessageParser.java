package com.mtb.main.smsbooking;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mtb.main.exception.RequestCanNotBeProcessedException;
import com.mtb.main.model.BookingRequest;

public class MessageParser {

	public BookingRequest getRequest(String request)
			throws RequestCanNotBeProcessedException {
		if (request == null)
			throw new RequestCanNotBeProcessedException("Invalid booking");

		String[] requestParts = request.split(" ");

		if (requestParts.length != 5)
			throw new RequestCanNotBeProcessedException("Invalid booking");

		if (!isvalidDate(requestParts[2]))
			throw new RequestCanNotBeProcessedException("Invalid date");

		if (!isvalidTime(requestParts[3]))
			throw new RequestCanNotBeProcessedException("Invalid time");

		if (!isValidSeats(requestParts[4]))
			throw new RequestCanNotBeProcessedException("Invalid seat number");

		return new BookingRequest(requestParts[0], requestParts[1],
				requestParts[2], requestParts[3],
				Integer.parseInt(requestParts[4]));
	}

	private boolean isvalidDate(String date) {
		String pattern = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
		return matchRegex(date, pattern);
	}

	private boolean isvalidTime(String time) {
		String pattern = "^[0-9]{1,2}[a,p]m$";
		return matchRegex(time, pattern);
	}

	private boolean isValidSeats(String seats) {
		String pattern = "^[0-9]+$";
		return matchRegex(seats, pattern);
	}

	private boolean matchRegex(String input, String pattern) {
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(input);
		return m.find();
	}

}
