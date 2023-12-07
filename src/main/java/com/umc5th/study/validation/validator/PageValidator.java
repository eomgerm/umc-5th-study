package com.umc5th.study.validation.validator;

import com.umc5th.study.base.code.status.ErrorStatus;
import com.umc5th.study.validation.annotation.ValidatePage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;


@Component
public class PageValidator implements ConstraintValidator<ValidatePage, Integer> {

    @Override
    public void initialize(ValidatePage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        boolean isValid = value >= 0;

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.PAGE_BELOW_ZERO.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
