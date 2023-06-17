package ru.kaznacheev.chat.controller;

import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ru.kaznacheev.chat.dto.RequestMessage;
import ru.kaznacheev.chat.dto.ResponseMessage;
import ru.kaznacheev.chat.service.MessageService;

@Controller
@AllArgsConstructor
public class ChatController {

    private MessageService messageService;

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ResponseMessage greeting(@Payload RequestMessage message) {
        return messageService.saveMessage(message.getSenderId(), message.getTimestamp(), message.getText());
    }

}
