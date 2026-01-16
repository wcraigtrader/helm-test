package com.blacksky.helmtest.writer.service;

import com.blacksky.helmtest.common.dto.MessageDTO;
import java.time.OffsetDateTime;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {
    private final RabbitTemplate rabbitTemplate;
    private final String queue;
    private final String version;
    private final String hostname;

    public PublisherService(RabbitTemplate rabbitTemplate,
                            @Value("${helmtest.queue:helmtest.queue}") String queue,
                            @Value("${app.version:0.1.0}") String version,
                            @Value("${HOSTNAME:${COMPUTERNAME:unknown}}") String hostname) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
        this.version = version;
        this.hostname = hostname;
    }

    @Scheduled(fixedRateString = "${publish.rate.ms:1000}")
    public void publish() {
        MessageDTO msg = new MessageDTO();
        msg.setTime(OffsetDateTime.now().toString());
        msg.setVersion(version);
        msg.setHostname(hostname == null ? "unknown" : hostname);
        rabbitTemplate.convertAndSend(queue, msg);
    }
}
