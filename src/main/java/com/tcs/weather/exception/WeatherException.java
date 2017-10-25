package com.tcs.weather.exception;

/**
 * @author amrutha
 * 
 *         Class for creating all Exceptions that may probably occur in the
 *         project.
 */

public class WeatherException extends Exception {
	private static final long serialVersionUID = 1L;

	private final String customErrorMessage;

	/**
	 * @param message
	 *            : Custom messages for each exceptions
	 */
	public WeatherException(String message) {
		this.customErrorMessage = message;
	}

	/**
	 * @param cause
	 *            : What is the cause of the exception
	 * @param message
	 *            : Custom messages for each exceptions
	 */
	public WeatherException(Throwable cause, String message) {
		super(cause);
		this.customErrorMessage = message;
	}

	@Override
	public String getMessage() {
		return this.customErrorMessage;
	}
}
