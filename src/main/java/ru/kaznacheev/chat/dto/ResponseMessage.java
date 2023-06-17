package ru.kaznacheev.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ResponseMessage {

    private String senderName;
    private String timestamp;
    private String text;

}
