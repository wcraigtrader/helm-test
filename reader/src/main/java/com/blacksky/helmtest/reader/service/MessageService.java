package com.blacksky.helmtest.reader.service;

import com.blacksky.helmtest.common.dto.MessageDTO;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MessageService {
    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);
    private final Deque<MessageDTO> deque = new LinkedList<>();
    private final ReentrantLock lock = new ReentrantLock();
    private static final int MAX = 100;

    @RabbitListener(queues = "${ctrader.queue:ctrader.queue}")
    public void receive(MessageDTO msg) {
        if (msg == null) {
            return;
        }
        logger.info("Received message: {}", msg);
        lock.lock();
        try {
            if (deque.size() >= MAX) {
                deque.removeFirst();
            }
            deque.addLast(msg);
        } finally {
            lock.unlock();
        }
    }

    public List<MessageDTO> getLastMessages() {
        lock.lock();
        try {
            return new ArrayList<>(deque);
        } finally {
            lock.unlock();
        }
    }
}
