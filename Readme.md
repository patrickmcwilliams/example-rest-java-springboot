# Backend

### Implement a small api for interacting with a callback based not yet created third-party service. 
____
## Specs
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
_______
## Technologies 
I kept this implementation simple and stayed with H2, but by using hibernate, the implementation to use MySQL or any other database is a simple change of the connection strings in application-prod.properties. Using H2 for DEV, makes tests easier and more replicable. The use of the prod profile could switch this and use a persistant store.

____
## Using
Makke sure docker engine is running

```
?> ./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=demo/status-service
?> docker run -p 8080:8080 -t demo/status-service
```
Thenn navigate to http://localhost:8080 to confirm the service is up. It should simply say "no thanks"
