package code.challenge.jobs.matching.engine.service;

import static org.junit.Assert.assertEquals;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import code.challenge.jobs.matching.engine.config.TestConfig;
import code.challenge.jobs.matching.engine.exception.JobsMatcherException;
import code.challenge.jobs.matching.engine.resource.job.Job;

/**
 * These tests are written based on the current responses from swipejobs api.
 * Better to mock the rest api call in such scenarios to avoid possibility
 * of unexpected responses from the external apis.
 * 
 * Also, assert is required on individual fields in the response.
 * 
 * @throws JobsMatcherException
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = TestConfig.class)
public class JobsMatcherServiceTest {

	@Autowired
	JobsMatcherService jobsMatcherService;

	@Test
	public void findMatchingJobsTest() throws JobsMatcherException {
		Integer workerId = 46;
		List<Job> jobs = jobsMatcherService.findMatchingJobs(workerId);
		assertEquals(2, jobs.size());
	}
	
	@Test
	public void findMatchingJobsTestForNotFound() throws JobsMatcherException {
		Integer workerId = 1;
		List<Job> jobs = jobsMatcherService.findMatchingJobs(workerId);
		assertEquals(0, jobs.size());
	}
}
