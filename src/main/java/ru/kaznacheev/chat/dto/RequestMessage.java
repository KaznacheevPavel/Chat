package ru.kaznacheev.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class RequestMessage {

    private UUID senderId;
    private String timestamp;
    private String text;

}
