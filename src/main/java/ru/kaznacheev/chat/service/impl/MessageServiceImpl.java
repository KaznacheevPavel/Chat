package ru.kaznacheev.chat.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.kaznacheev.chat.dto.ResponseMessage;
import ru.kaznacheev.chat.entity.Account;
import ru.kaznacheev.chat.entity.Message;
import ru.kaznacheev.chat.exception.MainException;
import ru.kaznacheev.chat.repository.AccountRepository;
import ru.kaznacheev.chat.repository.MessageRepository;
import ru.kaznacheev.chat.service.MessageService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private AccountRepository accountRepository;
    private MessageRepository messageRepository;

    @Override
    public ResponseMessage saveMessage(UUID senderId, String timestamp, String text) {
        Optional<Account> account = accountRepository.findById(senderId);
        if (account.isEmpty()) {
            throw new MainException(HttpStatus.BAD_REQUEST, "Неверный id отправителя");
        }
        if (text.contains("FILE_ID:")) {
            text = text.replace("FILE_ID:", "");
            text = "FILE: http://localhost:8080/api/v1/files/" + text;
        }
        Message message = Message.builder()
                .senderId(account.get())
                .sentTimestamp(LocalDateTime.parse(timestamp, DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm:ss")))
                .text(text)
                .build();
        messageRepository.save(message);
        return ResponseMessage.builder()
                .senderName(account.get().getUsername())
                .timestamp(timestamp)
                .text(text)
                .build();
    }

    @Override
    public List<ResponseMessage> getAllMessage() {
        List<Message> messages = messageRepository.findAll();
        List<ResponseMessage> response = new ArrayList<>(messages.size());
        for (Message message : messages) {
            ResponseMessage responseMessage = ResponseMessage.builder()
                    .senderName(message.getSenderId().getUsername())
                    .timestamp(message.getSentTimestamp().format(DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm:ss")))
                    .text(message.getText())
                    .build();
            response.add(responseMessage);
        }
        return response;
    }

}
