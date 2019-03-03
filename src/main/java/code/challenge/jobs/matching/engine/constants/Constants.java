package code.challenge.jobs.matching.engine.constants;

public class Constants {

	private Constants() {
	}	 
	public static final String JOB_MATCHER_API_URL = "/v1/matching-jobs";
	public static final String JOBS_API_URL = "http://test.swipejobs.com/api/jobs";
	public static final String WORKERS_API_URL = "http://test.swipejobs.com/api/workers";
	public static final String EXCEPTION_ON_API_CALL = "Exception occured while callng rest api";
	public static final Integer MAX_MATCHING_JOBS_REQUIRED = 3;
}
