# Crowdstreet Takehome Backend

Overview 
-------- 


Implement a small api for interacting with a callback based not yet created third-party service. 
Requirements 
------------ 
Implement an application that can be used as an intermediary for a not yet created third-party service. 
The third party service accepts a document request payload which includes a callback url and will respond to that url when the document is ready, which may be up to 10 business days later. 
Your application is expected to have four endpoints. These may be implemented as a stateful service, or multiple stateless services/functions connected to a store. The state must be stored somewhere (if in memory, please note how to store at rest). Each should respond to an http request and follow the REST conventions for methods. The endpoints should meet the following requirements: 
1. POST a request 
POST /request 
BODY: Object { 
"body": String 
} 
RETURNS String 
This should accept a JSON body consisting of one key, "body", which is a string. Doing this should initiate a request to the third-party service. It will also create a unique identifier for this request we can later reference. 
The request to the third party service should be a stubbed out, to call 
`http://example.com/request" with the following payload:
BODY Object: { 
"body": {body}, 
"callback": "/callback/{id}" 
} 
Comments or other indicators on how you would actually call this service are recommended, as well as any additional concerns around error handling/logging. 
2. POST callback 
POST /callback/{id} 
BODY String 
RETURNS 204 
This URL should be sent in the original request. Afterward, the service will send an initial POST with the text string `STARTED` to indicate it's they received the request. 
3. PUT callback 
PUT /callback/{id} 
BODY Object { 
"status": String, 
"detail": String 
} 
RETURNS 204 
At some later date, the third party service will PUT status updates to this callback URL, each which will have 
a json object with the keys of `status` and `detail`. The status will be one of `PROCESSED`, `COMPLETED` or `ERROR`. The detail will be a text string. 
4. GET status 
GET /status/{id} 
RETURNS Object { 
"status": String, 
"detail": String, 
"body": String 
} 
Finally, given the unique ID, we should be able to get the status of the request from our application. It will give us the status, detail and original body. 
Technologies 
------------
We encourage you to use those technologies you are most comfortable with. However, the primary tools you'll be expected to use at this job are Java and the AWS technology stack (including SQL). Displaying familiarity with these 
is useful and encouraged. Solutions that stray too far will not be considered. 
Questions 
--------- 
If you have any questions on the task presented here, we encourage you to reach out to you representative and we will strive to get you answers in a timely fashion. 
Thank you for your interest and taking the time to complete this. We know your time is valuable and we hope this experience helps both sides get a sense of the other in a quicker, more efficient manner.



This readme is here for you to give us any information about your project. 

It could be why you made certain decisions, what you left out or would change, and any other instructions or things you would like us to know when looking over your work. 

Refer to Instructions.md for how to use the boilerplate code! Happy coding!