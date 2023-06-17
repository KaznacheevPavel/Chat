package ru.kaznacheev.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ListOfExceptionDto {

    private String timestamp;
    private List<String> messages;

}
