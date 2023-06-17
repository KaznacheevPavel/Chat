package ru.kaznacheev.chat.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kaznacheev.chat.dto.RegistrationDto;
import ru.kaznacheev.chat.service.AccountService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/accounts")
@Validated
@AllArgsConstructor
public class AccountController {

    private AccountService accountService;

    @PostMapping
    public HttpStatus registration(@Valid @RequestBody RegistrationDto dto) {
        accountService.registration(dto.getUsername(), dto.getPassword());
        return HttpStatus.OK;
    }

}
