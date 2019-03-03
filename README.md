# jobs-matching-engine

This is a job matching engine that presents workers with appropriate Jobs. Refer to the api Blueprint in the project to understand the request/ response format and supported http codes.

## Matching Algorithm
	1. Get all workers and all jobs using the provided external apis
	2. Identify the given Worker using input workerId
	3. Return immediately with NotFound, if that worker is not available or that worker is not active
	4. Check if all mandatory matching criteria met (mandatory criteria is decided based on assumption)
		4.1. Driving license requirement match?
		4.2. Location match?
		4.3. Job title match?
	5. If mandatory matching criteria not met, return with NotFound.
	6. Else, set the matching percentage for each job(worker certificates matching divided by total required certificates)
	7. Sort the above list of almost matching workers based on match percentage, and send the most matching three jobs.
	8. In case if three 100% matching jobs are found, return immediately with those jobs.

## Installation Process

## Prerequisites
	
	1. Java 8 or above
	2. Maven
	3. Java IDE. Eg. Eclipse (optional)

## Steps for build and run
	1. Clone or download the repository
	2. Run maven commands. 
		Option 2.1 mvn clean install -> This will create executable jar jobs.matching.engine-0.0.1-SNAPSHOT.jar in target. Run the jar.
		Option 2.2 mvn spring-boot:run -> This will run the application as a stand alone service
	4. That's all ! You can access the api using URL http://localhost:8080//v1/matching-jobs?workerId=8. Refer Blueprint for details.

## Future possible enhancements
    1. Authentication and authorization
    2. Exception handling
    3. Logging
    4. Api connection pooling
	5. Lot more unit testing to cover all scenarios
    6. Properties hardcoded in java files has to be moved to a separate properties file to make it configurable

Note: This is not a complete solution for job matching. Some more enhancements as mentioned above are required to make the solution production ready.
