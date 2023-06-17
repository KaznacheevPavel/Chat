package ru.kaznacheev.chat.validator;

import lombok.AllArgsConstructor;
import ru.kaznacheev.chat.dto.RegistrationDto;
import ru.kaznacheev.chat.entity.Account;
import ru.kaznacheev.chat.repository.AccountRepository;
import ru.kaznacheev.chat.validator.annotation.RegistrationConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@AllArgsConstructor
public class RegistrationValidator implements ConstraintValidator<RegistrationConstraint, RegistrationDto> {

    private AccountRepository accountRepository;

    @Override
    public boolean isValid(RegistrationDto registrationDto, ConstraintValidatorContext constraintValidatorContext) {
        Optional<Account> user = accountRepository.findByUsername(registrationDto.getUsername());
        if (user.isPresent()) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Такое имя пользователя уже используется").addConstraintViolation();
            return false;
        }
        return true;
    }

}
