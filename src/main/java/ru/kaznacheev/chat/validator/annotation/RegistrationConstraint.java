package ru.kaznacheev.chat.validator.annotation;

import ru.kaznacheev.chat.validator.RegistrationValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = RegistrationValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RegistrationConstraint {
    String message() default "Ошибка регистрации";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
