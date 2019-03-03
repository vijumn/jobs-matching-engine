package code.challenge.jobs.matching.engine.restclient;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import code.challenge.jobs.matching.engine.constants.Constants;
import code.challenge.jobs.matching.engine.exception.JobsMatcherException;
import code.challenge.jobs.matching.engine.resource.worker.Worker;

@Component
public class WorkersRestApiClient implements RestApiClient {

	@Autowired
	private RestOperations restTemplate;
	
	/* (non-Javadoc)
	 * @see code.challenge.jobs.matching.engine.restclient.RestApiClient#callRestApi()
	 */
	public List<Worker> callRestApi() throws JobsMatcherException {

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(Constants.WORKERS_API_URL);

		@SuppressWarnings("unchecked")
		List<Worker> response = restTemplate.getForObject(builder.buildAndExpand().toUri(), ArrayList.class);
		return response;
	}
}
