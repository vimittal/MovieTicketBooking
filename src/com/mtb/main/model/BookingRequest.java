package com.mtb.main.model;

public class BookingRequest {

	private int seats;
	private String time;
	private String date;
	private String mobileNumber;
	private String name;

	public BookingRequest(String name, String mobileNumber, String date,
			String time, int seats) {
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.date = date;
		this.time = time;
		this.seats = seats;
	}

	public int getSeats() {
		return seats;
	}

	public String getTime() {
		return time;
	}

	public String getDate() {
		return date;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((mobileNumber == null) ? 0 : mobileNumber.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + seats;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookingRequest other = (BookingRequest) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (mobileNumber == null) {
			if (other.mobileNumber != null)
				return false;
		} else if (!mobileNumber.equals(other.mobileNumber))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (seats != other.seats)
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}

}
