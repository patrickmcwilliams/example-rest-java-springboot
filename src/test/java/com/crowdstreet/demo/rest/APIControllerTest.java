package com.crowdstreet.demo.rest;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import com.crowdstreet.demo.data.dao.StatusRepository;
import com.crowdstreet.demo.data.model.CallBackRequest;
import com.crowdstreet.demo.data.model.Request;
import com.crowdstreet.demo.data.model.Status;
import com.crowdstreet.demo.data.model.Status.StatusTypes;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class APIControllerTest {

	@Autowired
	private MockMvc mvc;
	@Autowired
	StatusRepository statusRepository;

	@BeforeClass
	public void setup() throws Exception{
		statusRepository.deleteAll();
		Request request = new Request();
		request.setBody("test");
		mvc.perform(MockMvcRequestBuilders.post("/request")
			.contentType(MediaType.APPLICATION_JSON)
			.content(request.getAsJSON()))
			.andExpect(status().isOk())
			.andExpect(content().string(equalTo("1")));
	}

	@Test
	public void getHello() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(403))
				.andExpect(content().string(equalTo("no thanks")));
	}

	@Test
	public void testRequestRoute() throws Exception {
		Request request = new Request();
		request.setBody("test");
		mvc.perform(MockMvcRequestBuilders.post("/request")
			.contentType(MediaType.APPLICATION_JSON)
			.content(request.getAsJSON()))
			.andExpect(status().isOk())
			.andExpect(content().string(equalTo("1")));
	}

	@Test
	public void testRequestRouteBadRequest() throws Exception {
		Object request = new Object();
		mvc.perform(MockMvcRequestBuilders.post("/request")
			.contentType(MediaType.APPLICATION_JSON)
			.content(request.toString()))
			.andExpect(status().is(400));
	}

	@Test
	public void testCallbackPost() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/callback/1")
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.content(StatusTypes.STARTED.toString()))
			.andExpect(status().isNoContent());
		Optional<Status> status =	statusRepository.findById(new Long(1));
		if (status.isPresent()){
			assert(status.get().getStatus().equals(StatusTypes.STARTED));
		}
		else{
			fail("callback did not update or retrieve data");
		}
	}

	@Test
	public void testCallbackPostNotStartedType() throws Exception {
		try {
			mvc.perform(MockMvcRequestBuilders.post("/callback/1")
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.content(StatusTypes.ERROR.toString()))
			.andExpect(status().isBadGateway());
		} catch (Exception e) {
			fail("STARTED should be the only accepted body");
		}

	}

	@Test
	public void testCallbackPut() throws Exception {
		CallBackRequest request = new CallBackRequest();
		request.setStatus(StatusTypes.COMPLETED);
		request.setDetail("test detail");
		mvc.perform(MockMvcRequestBuilders.put("/callback/1")
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.content(request.getAsJSON()))
			.andExpect(status().isNoContent());
		Optional<Status> status = statusRepository.findById(new Long(1));
		if (status.isPresent()){
			assert(status.get().getStatus().equals(StatusTypes.COMPLETED));
			assert(status.get().getDetail().equals("test detail"));
		}
		else{
			fail("callback did not update or retrieve data");
		}
	}

	@Test
	public void testGetStatus() throws Exception {
		this.testCallbackPut();

		mvc.perform(MockMvcRequestBuilders.get("/callback/1"))
			.andExpect(status().isOk())
			.andExpect(content().string("{\"id\":1,\"status\":\"COMPLETED\",\"detail\":\"test detail\"}"));
		Optional<Status> status = statusRepository.findById(new Long(1));
		if (status.isPresent()){
			assert(status.get().getStatus().equals(StatusTypes.COMPLETED));
			assert(status.get().getDetail().equals("test detail"));
		}
		else{
			fail("callback did not update or retrieve data");
		}
	}
}