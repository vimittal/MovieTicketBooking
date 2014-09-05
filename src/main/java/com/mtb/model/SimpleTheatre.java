package main.java.com.mtb.model;

public class SimpleTheatre implements Theatre {
	private int totalSeats;
	private int bookedSeats;

	public SimpleTheatre(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	@Override
	public boolean areSeatsAvailable(int seats) {
		return bookedSeats + seats <= totalSeats;
	}

	@Override
	public String[] book(int seats) {
		String[] result = new String[seats];
		for (int i = 0; i < seats; i++) {
			result[i] = ++bookedSeats + "";
		}
		return result;
	}

}
