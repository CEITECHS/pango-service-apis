package com.ceitechs.service.apis.rest.resources.validators;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;

/**
 * @author iddymagohe on 10/23/16.
 */
@Documented
@Constraint(validatedBy = StringFutureDateValidatorImpl.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@NotEmpty(message = "Value can not be null or empty")
@ReportAsSingleViolation
public @interface StringFutureDateValidator {
    String message() default "date value is not valid";

    String datePattern() default "yyyy-MM-dd";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
