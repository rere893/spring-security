package com.example.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidISBNValidator.class)
@Documented
public @interface ValidISBN {
    String message() default "ISBNの形式が正しくありません";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}