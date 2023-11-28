package com.umc5th.study.domain.enums;

import com.umc5th.study.domain.common.BaseEnum;
import com.umc5th.study.utils.EnumConverter;
import jakarta.persistence.Converter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserStatus implements BaseEnum {
    ACTIVE(0, "활성화"),
    INACTIVE(1, "비활성화"),
    ;

    private final Integer value;
    private final String description;

    @Override
    public Integer getValue() {
        return value;
    }

    @Converter
    public static class UserStatusConverter extends EnumConverter<UserStatus> {

        public UserStatusConverter() {
            super(UserStatus.class);
        }
    }
}
