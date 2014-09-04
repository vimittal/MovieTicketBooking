package main.java.com.mtb.exception;

public class RequestCanNotBeProcessedException extends Exception {

	private static final long serialVersionUID = 1L;
	private String message;

	public RequestCanNotBeProcessedException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
