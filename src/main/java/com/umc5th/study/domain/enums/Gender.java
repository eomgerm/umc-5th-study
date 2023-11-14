package com.umc5th.study.domain.enums;

import com.umc5th.study.domain.common.BaseEnum;
import com.umc5th.study.utils.EnumConverter;
import jakarta.persistence.Converter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Gender implements BaseEnum {
    MALE(0, "남성"),
    FEMALE(1, "여성"),
    OTHERS(2, "기타"),
    HIDDEN(3, "비공개"),
    ;

    private final Integer value;
    private final String description;

    @Override
    public Integer getValue() {
        return value;
    }

    @Converter
    public static class GenderConverter extends EnumConverter<Gender> {

        public GenderConverter() {
            super(Gender.class);
        }
    }
}
