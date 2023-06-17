package ru.kaznacheev.chat.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kaznacheev.chat.dto.ResponseMessage;
import ru.kaznacheev.chat.service.MessageService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/messages")
@AllArgsConstructor
public class MessageController {

    private MessageService messageService;

    @GetMapping()
    public List<ResponseMessage> getAll() {
        return messageService.getAllMessage();
    }

}
