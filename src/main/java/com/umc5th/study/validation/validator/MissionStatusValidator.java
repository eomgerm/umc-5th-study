package com.umc5th.study.validation.validator;

import com.umc5th.study.base.code.status.ErrorStatus;
import com.umc5th.study.validation.annotation.ValidateMissionStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class MissionStatusValidator implements ConstraintValidator<ValidateMissionStatus, Integer> {

    @Override
    public void initialize(ValidateMissionStatus constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        boolean isValid = switch (value) {
            case 0, 1 -> true;
            default -> false;
        };

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_STATUS_INVALID.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
