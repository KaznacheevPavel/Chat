package ru.kaznacheev.chat.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class MainException extends RuntimeException{

    private HttpStatus httpStatus;
    private String message;

}
