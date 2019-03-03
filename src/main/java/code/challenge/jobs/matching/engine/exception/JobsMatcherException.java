package code.challenge.jobs.matching.engine.exception;

/**
 * This exception is thrown for any issue in the job matching engine
 *
 */
public class JobsMatcherException extends Exception {

	private static final long serialVersionUID = 1L;

	public JobsMatcherException(String message) {
		super(message);
	}

	public JobsMatcherException(String message, Throwable t) {
		super(message, t);
	}
}
