package com.ceitechs.service.apis.rest.resources.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author iddymagohe on 10/23/16.
 * @since 1.0
 */
public class StringFutureDateValidatorImpl implements ConstraintValidator<StringFutureDateValidator, String> {

    DateTimeFormatter formatter = null;

    @Override
    public void initialize(StringFutureDateValidator constraintAnnotation) {
        formatter = DateTimeFormatter.ofPattern(constraintAnnotation.datePattern());
    }


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        LocalDate date = LocalDate.parse(value, formatter);
        return date.isAfter(LocalDate.now());
    }
}
