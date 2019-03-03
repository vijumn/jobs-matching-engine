FORMAT: 1A
APIVERSION: 1.0
APIDOMAIN: Email 

# Job matching Engine
Provides an api that takes a workerId and return no more than three appropriate matching jobs for that Worker.

## Authentication and Authorization
Currently no authentication or authorization is implemented. Any user can access the api.

## Media Types
The media type for response is application/json.

## Error States
The common [HTTP Response Status Codes](https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html) are used.

## Matching Jobs [GET /v1/matching-jobs]


+ Request

    + Parameters
    
    		workerId

    + Header
	
			No request header		
    
    + Body
        
           No request body
          
+ Response 200 (application/json)

    + Body

           [\\] # (The is the response created or generated when its successful. JSON format.)            
 	
	   [
	    {
	        "driverLicenseRequired": false,
	        "requiredCertificates": [
	            "Outstanding Memory Award"
	        ],
	        "location": {
	            "longitude": "14.082219",
	            "latitude": "50.180255"
	        },
	        "billRate": "$14.98",
	        "workersRequired": 2,
	        "startDate": "2015-11-12T07:23:56.19Z",
	        "about": "Non veniam ipsum esse consectetur cillum ipsum quis aliquip. In eiusmod excepteur laborum laboris quis cupidatat consectetur exercitation veniam nisi. Commodo officia cillum laboris velit pariatur mollit deserunt mollit. Commodo sit proident aliquip officia. Irure deserunt ullamco quis aliqua eu eu ullamco qui eu minim. Tempor pariatur est ullamco duis reprehenderit. Est qui non Lorem et.",
	        "jobTitle": "The Resinator",
	        "company": "Centice",
	        "guid": "562f66aa7f96c1f61adfd108",
	        "jobId": 29
	    },
	    {
	        "driverLicenseRequired": false,
	        "requiredCertificates": [
	            "Healthy Living Promoter"
	        ],
	        "location": {
	            "longitude": "14.017374",
	            "latitude": "50.075484"
	        },
	        "billRate": "$14.50",
	        "workersRequired": 5,
	        "startDate": "2015-11-11T20:45:02.016Z",
	        "about": "Reprehenderit aute in tempor commodo sint magna. Culpa et sit sint proident laborum consectetur. Esse commodo officia id incididunt adipisicing. Culpa incididunt eu culpa deserunt eiusmod. Proident aliqua id deserunt sint ea ea excepteur officia.",
	        "jobTitle": "Director of First Impressions",
	        "company": "Eplode",
	        "guid": "562f66aaabcc1195f72254b5",
	        "jobId": 33
	    }
	]            

+ Response 404 (application/json)
			
			[\\] # (The is the response created or generated when there is no matching job found for the given workerId. Empty array. JSON format.)
			
			[]
				  
+ Response 400 (application/json)
			
			[\\] # (The is the response created or generated when its bad request. JSON format.)
			
	{
	    "timestamp": "2019-03-03T17:55:35.027+0000",
	    "status": 400,
	    "error": "Bad Request",
	    "message": "Failed to convert value of type 'java.lang.String' to required type 'java.lang.Integer'; nested exception is java.lang.NumberFormatException: For input string: \"XX\"",
	    "path": "//v1/matching-jobs"
	}	

+ Response 500 (application/json)
    	
    		[\\] # (The is the response created or generated when its internal server error. JSON format.)
    		
	{
    "message": "Internal Server Error",
	}	
