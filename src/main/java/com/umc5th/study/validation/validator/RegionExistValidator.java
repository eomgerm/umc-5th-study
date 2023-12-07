package com.umc5th.study.validation.validator;

import com.umc5th.study.base.code.status.ErrorStatus;
import com.umc5th.study.service.StoreQueryService;
import com.umc5th.study.validation.annotation.ExistRegion;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegionExistValidator implements ConstraintValidator<ExistRegion, String> {

    private final StoreQueryService storeQueryService;

    @Override
    public void initialize(ExistRegion constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean isValid = storeQueryService.isRegionExists(value);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.REGION_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
