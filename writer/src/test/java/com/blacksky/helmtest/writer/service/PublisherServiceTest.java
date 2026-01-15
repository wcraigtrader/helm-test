package com.blacksky.helmtest.writer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.blacksky.helmtest.common.dto.MessageDTO;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

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
        MessageDTO dto = (MessageDTO) sent;
        assertEquals(version, dto.getVersion());
        assertEquals(hostname, dto.getHostname());
        assertNotNull(dto.getTime());
    }
}
