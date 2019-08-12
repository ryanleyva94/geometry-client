package com.leyva.geometry.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GeometryClientApplicationTests {

	@MockBean
	private RabbitListener rabbitListener;

	@Test
	public void contextLoads() {
	}

}
