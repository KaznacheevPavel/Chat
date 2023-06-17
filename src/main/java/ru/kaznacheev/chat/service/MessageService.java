package ru.kaznacheev.chat.service;

import ru.kaznacheev.chat.dto.ResponseMessage;

import java.util.List;
import java.util.UUID;

public interface MessageService {

    ResponseMessage saveMessage(UUID senderId, String timestamp, String text);
    List<ResponseMessage> getAllMessage();

}
