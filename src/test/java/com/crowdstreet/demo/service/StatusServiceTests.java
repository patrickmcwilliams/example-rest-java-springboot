package com.crowdstreet.demo.service;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.crowdstreet.demo.data.dao.StatusRepository;
import com.crowdstreet.demo.data.model.Status;
import com.crowdstreet.demo.data.model.Status.StatusTypes;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StatusServiceTests {

    @Mock
    private StatusRepository statusRepository;
    @InjectMocks
    private StatusService statusService;

    @Before 
    public void initMocks() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testAddStatus() {
        ArgumentCaptor<Status> statusArgCapture = ArgumentCaptor.forClass(Status.class);
        try {
            Status status = new Status();
            when(statusRepository.save(statusArgCapture.capture())).thenReturn(status);
            long result = statusService.addStatus(status);
            assert(result == 0);
        } catch (Exception e) {
            fail("should not have excepted");
        }
    }

    @Test
    public void testPostCallback() {
        ArgumentCaptor<Long> statusArgCapture = ArgumentCaptor.forClass(Long.class);
        Optional<Status> statusOptional = Optional.of(new Status());
        when(statusRepository.findById(statusArgCapture.capture())).thenReturn(statusOptional);
        try {
            statusService.postCallback(StatusTypes.STARTED.toString(), new Long(1));
        } catch (Exception e) {
            fail("should not have excepted");
        }
    }

    @Test
    public void testPostCallbackWithWrongStatus() {
        ArgumentCaptor<Long> statusArgCapture = ArgumentCaptor.forClass(Long.class);
        Optional<Status> statusOptional = Optional.of(new Status());
        when(statusRepository.findById(statusArgCapture.capture())).thenReturn(statusOptional);
        try {
            statusService.postCallback(StatusTypes.ERROR.toString(), new Long(1));
            fail("should have excepted");
        } catch (Exception e) {
            assert(e.getMessage().equals("Need to use PUT for any status other than STARTED"));
        }
    }
}
