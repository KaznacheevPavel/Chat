package ru.kaznacheev.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ExceptionDto {

    private String timestamp;
    private String message;

}
