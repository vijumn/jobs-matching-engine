package code.challenge.jobs.matching.engine.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import code.challenge.jobs.matching.engine.exception.JobsMatcherException;
import code.challenge.jobs.matching.engine.resource.job.Job;
import code.challenge.jobs.matching.engine.resource.worker.Worker;
import code.challenge.jobs.matching.engine.restclient.JobsRestApiClient;
import code.challenge.jobs.matching.engine.restclient.WorkersRestApiClient;


@Service
public class JobsMatcherServiceImpl implements JobsMatcherService {

	@Autowired
	WorkersRestApiClient workersRestApiClient;
	
	@Autowired
	JobsRestApiClient jobsRestApiClient;
	
	@Autowired
	JobMatcher jobMatcher;
	
	
	/* (non-Javadoc)
	 * @see code.challenge.jobs.matching.engine.service.JobsMatcherService#findMatchingJobs(java.lang.Integer)
	 */
	public List<Job> findMatchingJobs(Integer workerId) throws JobsMatcherException {

		List<Worker> allWorkers = workersRestApiClient.callRestApi();
		
		List<Job> allJobs = jobsRestApiClient.callRestApi();
		
		Worker worker = getWorker(workerId, allWorkers);
		if (worker == null || !worker.getIsActive()){
			return new ArrayList<Job>();
		}
		
		List<Job> matchingJobs = jobMatcher.findMatchingJobs(worker, allJobs);
		
		return matchingJobs;
	}

	private Worker getWorker(Integer workerId, List<Worker> workers) {

		for (int i = 0; i < workers.size(); i++) {
			Worker worker = new ObjectMapper().convertValue(workers.get(i), Worker.class);
			if (worker.getUserId() == workerId) {
				return worker;
			}
		}
		return null;
	}
}
