package code.challenge.jobs.matching.engine.service;

import java.util.List;

import code.challenge.jobs.matching.engine.exception.JobsMatcherException;
import code.challenge.jobs.matching.engine.resource.job.Job;

public interface JobsMatcherService {

	/**
	 * This service takes a workerId and return no more than three appropriate
	 * jobs for that Worker.
	 * 
	 * @param workerId
	 * @return List of matching jobs
	 * @throws JobsMatcherException
	 */
	public List<Job> findMatchingJobs(Integer workerId) throws JobsMatcherException;

}
