package code.challenge.jobs.matching.engine.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * This is used as the response body for any Internal Server Error in job
 * matching engine
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResponse {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
