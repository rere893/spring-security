package com.example.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidISBNValidator implements ConstraintValidator<ValidISBN, String> {
    @Override
    public void initialize(ValidISBN annotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // @NotNullで別途チェック
        }

        // ISBN-10形式: 978-1234567890 または ISBN-13形式: 9781234567890
        return value.matches("^[0-9]{3}-[0-9]{10}$|^[0-9]{13}$");
    }
}