package code.challenge.jobs.matching.engine.restclient;

import java.util.List;

import org.springframework.stereotype.Component;

import code.challenge.jobs.matching.engine.exception.JobsMatcherException;

/**
 * This class defines the abstraction for doing any rest api call
 *
 */
@Component
public interface RestApiClient {

	/**
	 * Set the required request parameters, request header and request body
	 * where ever applicable and calls the rest api
	 * 
	 * @return Response of the api as a List
	 * @throws JobsMatcherException
	 */
	List callRestApi() throws JobsMatcherException;
}
