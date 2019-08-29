package com.sahit.crud;

import com.sahit.crud.model.User;
import com.sahit.crud.repository.UserRepository;
import com.sahit.crud.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

import static junit.framework.TestCase.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

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


	@Test
	public void findUserById() {
		try {
			ResultActions resultActions = mockMvc.perform(get("/user/1"));
			MockHttpServletResponse mockResponse = resultActions.andReturn()
					.getResponse();
			if(mockResponse.getContentType()!=null) {
				mockMvc.perform(get("/user/1" )).andDo(print())
						.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
						.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Sahit"))
						.andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Kumar"))
						.andExpect(MockMvcResultMatchers.jsonPath("$.email").value("sahit@test.com"));
			}
			else {
				fail("failed");
			}
		}
		catch(Exception e) {
			fail("failed");
		}
	}

}
