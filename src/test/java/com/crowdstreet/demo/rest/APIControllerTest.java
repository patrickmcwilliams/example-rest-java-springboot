package com.crowdstreet.demo.rest;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.crowdstreet.demo.data.model.Counts;

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
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("Greetings from Spring Boot!")));
	}

	@Test
	public void countInsertPost0() throws Exception {
		Counts count = new Counts();
		String countJSON = count.getAsJSON();
		mvc.perform(MockMvcRequestBuilders.post("/count/insert/")
			.contentType(MediaType.APPLICATION_JSON)
			.content(countJSON))
			.andExpect(status().isOk())
			.andExpect(content().string(equalTo("Successfully inserted with a value of 0")));
	}

	@Test
	public void countInsertPost1() throws Exception {
		Counts count = new Counts();
		count.setValue(1);
		String countJSON = count.getAsJSON();
		mvc.perform(MockMvcRequestBuilders.post("/count/insert/")
			.contentType(MediaType.APPLICATION_JSON)
			.content(countJSON))
			.andExpect(status().isOk())
			.andExpect(content().string(equalTo("Successfully inserted with a value of 1")));
	}

	@Test
	public void countGet2() throws Exception {
		this.countInsertPost0();
		this.countInsertPost1();
		mvc.perform(MockMvcRequestBuilders.get("/count/2").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("{\"id\":2,\"value\":1}")));
	}
}