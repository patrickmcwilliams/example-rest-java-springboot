package com.crowdstreet.demo.service;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import com.crowdstreet.demo.data.dao.StatusRepository;
import com.crowdstreet.demo.data.model.Status;

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
}
