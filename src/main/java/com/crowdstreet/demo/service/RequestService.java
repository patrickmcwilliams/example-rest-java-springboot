package com.crowdstreet.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crowdstreet.demo.vendor.Example;
import com.crowdstreet.demo.data.model.ExampleRequest;
import com.crowdstreet.demo.data.model.Request;
import com.crowdstreet.demo.data.model.Status;
import com.crowdstreet.demo.data.model.Status.StatusTypes;
import com.crowdstreet.demo.exceptions.RequestException;

@Service
public class RequestService {
    private final String CALLBACK_URL = "/callback/";

    @Autowired
    private Example exampleAPI;
    @Autowired
    private StatusService statusService;

    public long makeRequest(Request request) throws RequestException{
        Status status;
        long id;
        try{
            status = new Status(StatusTypes.INIT);
            id = statusService.addStatus(status);

            ExampleRequest exampleRequest = new ExampleRequest();
            exampleRequest.setBody(request);
            exampleRequest.setCallback(this.generateCallbackURL(id));
            exampleAPI.sendRequest(exampleRequest);
        }
        catch(Exception e){
            throw new RequestException(e.getMessage());
        }
        return id;
    }

    private String generateCallbackURL(long id){
        StringBuilder builder = new StringBuilder();
        builder.append(this.CALLBACK_URL).append(Long.toString(id));
        return builder.toString();
    }
}
