package code.challenge.jobs.matching.engine.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import code.challenge.jobs.matching.engine.constants.Constants;
import code.challenge.jobs.matching.engine.resource.job.Job;
import code.challenge.jobs.matching.engine.resource.job.Location;
import code.challenge.jobs.matching.engine.resource.worker.JobSearchAddress;
import code.challenge.jobs.matching.engine.resource.worker.Worker;
import code.challenge.jobs.matching.engine.utility.DistanceCalculator;

@Component
public class JobMatcher {

	/**
	 * Here is the matching algorithm
	 * 
	 * @param worker
	 * @param jobs
	 * @return List of matching jobs
	 */
	public List<Job> findMatchingJobs(Worker worker, List<Job> jobs) {
		List<Job> almostMatchingJobs = new ArrayList<Job>();
		List<Job> matchingJobs = new ArrayList<Job>();
		int counter = 0;

		for (int i = 0; i < jobs.size(); i++) {
			Job job = new ObjectMapper().convertValue(jobs.get(i), Job.class);

			if (isMandatoryMatchingCriteriaMet(worker, job)) {
				job = setMatchPercentage(worker.getCertificates(), job);
				almostMatchingJobs.add(job);
				if (job.getMatchPercentage() == 100.00) {
					counter++;
					matchingJobs.add(job);
				}
				if (counter == Constants.MAX_MATCHING_JOBS_REQUIRED) {
					return matchingJobs; /*
											 * Return when the first three 100%
											 * matching jobs are found
											 */
				}
			}
		}
		sortJobsBasedOnMatchPercentage(almostMatchingJobs);
		matchingJobs = new ArrayList<Job>();
		for (int i = 0; (i < Constants.MAX_MATCHING_JOBS_REQUIRED && i < almostMatchingJobs.size()); i++) {
			matchingJobs.add(almostMatchingJobs.get(i));
		}
		return matchingJobs;
	}

	private void sortJobsBasedOnMatchPercentage(List<Job> almostMatchingJobs) {
		Comparator<Job> matchPercentageComparator = new Comparator<Job>() {
			public int compare(Job job1, Job job2) {
				return job1.getMatchPercentage().compareTo(job2.getMatchPercentage());
			}
		};
		almostMatchingJobs.sort(matchPercentageComparator);
	}

	private Job setMatchPercentage(List<String> certificates, Job job) {
		int certMatchCounter = 0;
		for (String requiredCertificate : job.getRequiredCertificates()) {
			if (certificates.contains(requiredCertificate)) {
				certMatchCounter++;
			}
		}
		Double matchPercentage = ((double) (certMatchCounter / job.getRequiredCertificates().size())) * 100;
		job.setMatchPercentage(matchPercentage);
		return job;
	}

	private boolean isMandatoryMatchingCriteriaMet(Worker worker, Job job) {

		if (isDriverLicenceRequirementMatch(worker.getHasDriversLicense(), job.getDriverLicenseRequired())) {
			if (isLocationMatch(worker.getJobSearchAddress(), job.getLocation())) {
				if (isJobTitleMatch(worker.getSkills(), job.getJobTitle())) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean isJobTitleMatch(List<String> workerSkills, String jobTitle) {
		return workerSkills.contains(jobTitle);
	}

	private boolean isLocationMatch(JobSearchAddress jobSearchAddress, Location jobLocation) {
		try {
			double distance = DistanceCalculator.findDistance(Double.valueOf(jobSearchAddress.getLatitude()),
					Double.valueOf(jobSearchAddress.getLongitude()), Double.valueOf(jobLocation.getLatitude()),
					Double.valueOf(jobLocation.getLongitude()));
			if (distance > jobSearchAddress.getMaxJobDistance()) {
				return false;
			} else {
				return true;
			}
		} catch (NullPointerException ex) {
			return false;
		}
	}

	private boolean isDriverLicenceRequirementMatch(boolean isWorkerHasLicence, boolean isLicenceRequiredForJob) {
		if (isLicenceRequiredForJob) {
			return isLicenceRequiredForJob == isWorkerHasLicence;
		} else {
			return true;
		}
	}
}
