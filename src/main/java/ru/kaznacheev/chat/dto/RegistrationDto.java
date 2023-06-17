package ru.kaznacheev.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kaznacheev.chat.validator.annotation.RegistrationConstraint;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@RegistrationConstraint
public class RegistrationDto {

    @NotBlank(message = "Логин не может быть пустым")
    @Size(min = 2, max = 16, message = "Минимальная длина логина 2 символа, максимальная 16")
    private String username;

    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 8, max = 64, message = "Минмальная длина пароля 8 символов, максимальная 64")
    private String password;

}
