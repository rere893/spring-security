package com.example.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidPriceValidator.class)
@Documented
public @interface ValidPrice {
    String message() default "価格は100〜1000000の範囲である必要があります";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}