package com.github.herowzz.springfuse.rest.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
public class BaseControllerTest {

	@Autowired
	protected MockMvc mvc;

}
