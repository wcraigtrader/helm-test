package com.blacksky.helmtest.reader.controller;

import com.blacksky.helmtest.common.dto.MessageDTO;
import com.blacksky.helmtest.reader.service.MessageService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessageController {
    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @GetMapping
    public List<MessageDTO> list() {
        return service.getLastMessages();
    }
}
