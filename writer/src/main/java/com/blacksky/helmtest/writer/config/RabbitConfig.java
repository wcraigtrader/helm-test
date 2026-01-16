package com.blacksky.helmtest.writer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class RabbitConfig {
    @Bean
    public Queue helmtestQueue(Environment env) {
        String q = env.getProperty("helmtest.queue", "helmtest.queue");
        return new Queue(q, true);
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
