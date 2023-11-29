package com.umc5th.study.validation.annotation;

import com.umc5th.study.validation.validator.MissionExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MissionExistValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistMission {

    String message() default "존재하지 않는 미션입니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
