package com.crowdstreet.demo.vendor;

import com.crowdstreet.demo.data.model.ExampleRequest;
import com.crowdstreet.demo.data.model.Request;
import com.crowdstreet.demo.exceptions.APIException;
import com.crowdstreet.demo.service.HTTPService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class Example {
    private enum Actions {
        request;
    }
    private final String EXAMPLE_URL = "https://example.com/";

    @Autowired
    HTTPService http;

    public void sendRequest(ExampleRequest request) throws APIException {
        if (request.getBody() == null){
            throw new APIException("body cannot be null");
        }
        try {
            String endpoint = this.getEndpoint(Actions.request);
            http.post(endpoint, request.getAsJSON());
        } catch (Exception e) {
            throw new APIException(e.getMessage());
        }
    }

    private String getEndpoint(Actions action){
        StringBuilder builder = new StringBuilder();
        builder.append(this.EXAMPLE_URL).append(action.toString());
        return builder.toString();
    }
}
