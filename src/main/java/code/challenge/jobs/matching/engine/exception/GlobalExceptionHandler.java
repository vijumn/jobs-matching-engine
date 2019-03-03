package code.challenge.jobs.matching.engine.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class is used as spring global exception handler
 *
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@ResponseBody
public class GlobalExceptionHandler {

	private static final String INTERNAL_SERVER_ERROR_MESSAGE = "Internal Server Error";


	@ExceptionHandler(value = JobsMatcherException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionResponse handleTransformFailed(HttpServletRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setMessage(INTERNAL_SERVER_ERROR_MESSAGE);
		return exceptionResponse;
	}

	
}
