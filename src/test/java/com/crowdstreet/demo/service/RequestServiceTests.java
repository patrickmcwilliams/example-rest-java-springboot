package com.crowdstreet.demo.service;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.crowdstreet.demo.data.model.ExampleRequest;
import com.crowdstreet.demo.data.model.Request;
import com.crowdstreet.demo.data.model.Status;
import com.crowdstreet.demo.data.model.Status.StatusTypes;
import com.crowdstreet.demo.exceptions.RequestException;
import com.crowdstreet.demo.vendor.Example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class RequestServiceTests {
    
    @Mock
    private Example exampleAPI;
    @Mock
    private StatusService statusService;
    @InjectMocks
    private RequestService requestService;

    @Before 
    public void initMocks() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testMakeRequest() throws Exception{
        ArgumentCaptor<ExampleRequest> exampleArgCapture = ArgumentCaptor.forClass(ExampleRequest.class);
        ArgumentCaptor<Status> statusArgCapture = ArgumentCaptor.forClass(Status.class);
        try {
            doNothing().when(exampleAPI).sendRequest(exampleArgCapture.capture());
            when(statusService.addStatus(statusArgCapture.capture())).thenReturn(new Long(1));
            Request request = new Request();
            request.setBody("test");
            long id = requestService.makeRequest(request);
            //TODO: break up tests
            assert(id == 1);
            assert(exampleArgCapture.getValue().getBody().getBody().equals("test"));
            assert(exampleArgCapture.getValue().getCallback().equals("/callback/1"));
            assert(statusArgCapture.getValue().getStatus() == StatusTypes.INIT);
        } catch (Exception e) {
            fail("should not have excepted");
        }
    }

    @Test
    public void testMakeRequestWithException() throws Exception{
        try {
            doCallRealMethod().when(exampleAPI).sendRequest(any());
            long id = requestService.makeRequest(new Request());
            if(id == 1){
                fail("should have excepted");
            };
        } catch (Exception e) {
           assert(e.getClass()==RequestException.class);
        }
    }
}
