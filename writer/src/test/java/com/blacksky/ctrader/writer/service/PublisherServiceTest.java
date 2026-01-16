package com.blacksky.ctrader.writer.service;

import com.blacksky.ctrader.writer.dto.MessageDTO;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PublisherServiceTest {

	@Test
	public void publish_sendsMessageWithVersionAndHostname() {
		RabbitTemplate rt = mock(RabbitTemplate.class);
		String queue = "test.queue";
		String version = "v-test";
		String hostname = "host-test";

		PublisherService svc = new PublisherService(rt, queue, version, hostname);
		svc.publish();

		ArgumentCaptor<Object> cap = ArgumentCaptor.forClass(Object.class);
		verify(rt, times(1)).convertAndSend(eq(queue), cap.capture());
		Object sent = cap.getValue();
		assertNotNull(sent);
		// MessageDTO in writer module extends common DTO; check via reflection or cast
		MessageDTO dto = (MessageDTO) sent;
		assertEquals(version, dto.getVersion());
		assertEquals(hostname, dto.getHostname());
		assertNotNull(dto.getTime());
	}
}
