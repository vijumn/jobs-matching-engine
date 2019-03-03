package code.challenge.jobs.matching.engine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import code.challenge.jobs.matching.engine.constants.Constants;
import code.challenge.jobs.matching.engine.exception.JobsMatcherException;
import code.challenge.jobs.matching.engine.resource.job.Job;
import code.challenge.jobs.matching.engine.service.JobsMatcherService;

/**
 * Resource for matching-jobs
 *
 */
@RestController
public class JobsMatcherController {

	@Autowired
	private JobsMatcherService jobsMatcherService;

	/**
	 * This api takes a workerId and return no more than three appropriate jobs
	 * for that Worker.
	 * 
	 * @param workerId
	 * @return List of matched jobs
	 * @throws JobsMatcherException
	 */
	@RequestMapping(value = Constants.JOB_MATCHER_API_URL, produces = "application/hal+json", method = RequestMethod.GET)
	public ResponseEntity<List<Job>> findMatchingJobs(@RequestParam Integer workerId) throws JobsMatcherException {

		List<Job> response = jobsMatcherService.findMatchingJobs(workerId);
		return new ResponseEntity<List<Job>>(response, response.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);

	}

}
