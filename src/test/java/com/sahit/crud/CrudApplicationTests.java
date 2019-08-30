package com.sahit.crud;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sahit.crud.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrudApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	WebApplicationContext context;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

	}


	/**
	 * Test for creating user
	 * @throws Exception
	 */
	@Test
	public void createUser() throws Exception
	{
		mockMvc.perform( MockMvcRequestBuilders
				.post("/user/create")
				.content(asJsonString(new User("skr", "j", "skr@test.com")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("skr"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("j"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.email").value("skr@test.com"));
	}

	/**
	 * Test for Empty FirstName
	 * @throws Exception
	 */
	@Test
	public void failCreateUserEmptyFirstName() throws Exception
	{
		mockMvc.perform( MockMvcRequestBuilders
				.post("/user/create")
				.content(asJsonString(new User("", "j", "skr@test.com")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isBadRequest())
				.andExpect(content().string("First Name is required"));
	}

	/**
	 * Test for Empty LastName
	 * @throws Exception
	 */
	@Test
	public void failCreateUserEmptyLastName() throws Exception
	{
		mockMvc.perform( MockMvcRequestBuilders
				.post("/user/create")
				.content(asJsonString(new User("skr", "", "skr@test.com")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isBadRequest())
				.andExpect(content().string("Last Name is required"));
	}

	/**
	 * Test for Empty Email
	 * @throws Exception
	 */
	@Test
	public void failCreateUserEmptyEmail() throws Exception
	{
		mockMvc.perform( MockMvcRequestBuilders
				.post("/user/create")
				.content(asJsonString(new User("skr", "j", "")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isBadRequest())
				.andExpect(content().string("Email is required"));
	}

	/**
	 * Test for Invalid Email
	 * @throws Exception
	 */
	@Test
	public void failCreateUserInvalidEmail() throws Exception
	{
		mockMvc.perform( MockMvcRequestBuilders
				.post("/user/create")
				.content(asJsonString(new User("skr", "j", "skr")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isBadRequest())
				.andExpect(content().string("Invalid Email"));
	}

	/**
	 * Test for Empty LastName and Empty Email
	 * @throws Exception
	 */
	@Test
	public void failCreateUserEmptyLastNameAndEmptyEmail() throws Exception
	{
		mockMvc.perform( MockMvcRequestBuilders
				.post("/user/create")
				.content(asJsonString(new User("skr", "", "")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isBadRequest())
				.andExpect(content().string("Last Name, Email are required"));
	}

	/**
	 * Test for Empty FirstName and Empty Email
	 * @throws Exception
	 */
	@Test
	public void failCreateUserEmptyFirstNameAndEmptyEmail() throws Exception
	{
		mockMvc.perform( MockMvcRequestBuilders
				.post("/user/create")
				.content(asJsonString(new User("", "j", "")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isBadRequest())
				.andExpect(content().string("First Name, Email are required"));
	}

	/**
	 * Test for Empty FirstName and Empty LastName
	 * @throws Exception
	 */
	@Test
	public void failCreateUserEmptyFirstNameAndEmptyLastName() throws Exception
	{
		mockMvc.perform( MockMvcRequestBuilders
				.post("/user/create")
				.content(asJsonString(new User("", "", "skr@test.com")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isBadRequest())
				.andExpect(content().string("First Name, Last Name are required"));
	}

	/**
	 * Test for Empty FirstName and Empty LastName and Empty Email
	 * @throws Exception
	 */
	@Test
	public void failCreateUserEmptyFirstNameAndEmptyLastNameAndEmptyEmail() throws Exception
	{
		mockMvc.perform( MockMvcRequestBuilders
				.post("/user/create")
				.content(asJsonString(new User("", "", "")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isBadRequest())
				.andExpect(content().string("First Name, Last Name, Email are required"));
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Test for getting user details
	 * @throws Exception
	 */
	@Test
	public void findUserById() throws Exception
	{
		mockMvc.perform( MockMvcRequestBuilders
				.get("/user/{id}", 1)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Sahit"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Kumar"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.email").value("sahit@test.com"));
	}

}
