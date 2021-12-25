package be.kdg.bierproject.exceptions;

/**
 * @author Maxim Derboven
 * @version 1.0 23/12/2021 19:48
 */
public class BierException extends RuntimeException {
	public BierException() {
	}

	public BierException(String message) {
		super(message);
	}

	public BierException(String message, Throwable cause) {
		super(message, cause);
	}

	public BierException(Throwable cause) {
		super(cause);
	}

	public BierException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
