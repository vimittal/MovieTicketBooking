package com.mtb.main.model;

public interface Theatre {

	public abstract boolean areSeatsAvailable(int seats);

	public abstract String[] book(int seats);

}