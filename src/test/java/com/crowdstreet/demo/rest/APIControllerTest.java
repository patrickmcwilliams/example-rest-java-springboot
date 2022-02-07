package com.crowdstreet.demo.rest;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.crowdstreet.demo.data.model.Request;

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

	@Test
	public void getHello() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(501))
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

	// @Test
	// public void countInsertPost1() throws Exception {
	// 	Status count = new Status();
	// 	count.setStatus(StatusTypes.INIT);
	// 	String countJSON = count.getAsJSON();
	// 	mvc.perform(MockMvcRequestBuilders.post("/count/insert/")
	// 		.contentType(MediaType.APPLICATION_JSON)
	// 		.content(countJSON))
	// 		.andExpect(status().isOk())
	// 		.andExpect(content().string(equalTo("Successfully inserted with a value of 1")));
	// }

	// @Test
	// public void countGet2() throws Exception {
	// 	this.countInsertPost0();
	// 	this.countInsertPost1();
	// 	mvc.perform(MockMvcRequestBuilders.get("/count/2").accept(MediaType.APPLICATION_JSON))
	// 			.andExpect(status().isOk())
	// 			.andExpect(content().string(equalTo("{\"id\":2,\"value\":1}")));
	// }
}