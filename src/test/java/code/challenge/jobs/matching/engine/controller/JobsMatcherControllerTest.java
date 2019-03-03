package code.challenge.jobs.matching.engine.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import code.challenge.jobs.matching.engine.config.TestConfig;
import code.challenge.jobs.matching.engine.constants.Constants;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = TestConfig.class)
public class JobsMatcherControllerTest {

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	/**
	 * This is to test jobs matcher api. This will execute the code from controller.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testJobsMatcherApi() throws Exception {

		String url = Constants.JOB_MATCHER_API_URL + "?workerId=" + "46";

		this.mockMvc.perform(get(url)).andDo(print())
				.andExpect(status().isOk());

	}
}
