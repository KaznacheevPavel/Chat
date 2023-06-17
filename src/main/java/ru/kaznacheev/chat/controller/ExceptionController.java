package ru.kaznacheev.chat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.kaznacheev.chat.dto.ExceptionDto;
import ru.kaznacheev.chat.dto.ListOfExceptionDto;
import ru.kaznacheev.chat.exception.MainException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    public ResponseEntity<ExceptionDto> handleException(MainException exception) {
        ExceptionDto response = new ExceptionDto(
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:ms").format(LocalDateTime.now()),
                exception.getMessage());
        return new ResponseEntity<>(response, exception.getHttpStatus());
    }

    @ExceptionHandler
    private ResponseEntity<ListOfExceptionDto> handleException(MethodArgumentNotValidException exception) {
        List<String> errors = new ArrayList<>();
        for (ObjectError objectError : exception.getAllErrors()) {
            errors.add(objectError.getDefaultMessage());
        }
        ListOfExceptionDto response = new ListOfExceptionDto(
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:ms").format(LocalDateTime.now()),
                errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
