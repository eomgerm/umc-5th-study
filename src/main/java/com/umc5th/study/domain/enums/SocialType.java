package com.umc5th.study.domain.enums;

import com.umc5th.study.domain.common.BaseEnum;
import com.umc5th.study.utils.EnumConverter;
import jakarta.persistence.Converter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SocialType implements BaseEnum {
    KAKAO(0, "카카오"),
    GOOGLE(1, "구글"),
    NAVER(2, "네이버"),
    APPLE(3, "애플"),
    ;

    private final Integer value;
    private final String description;

    @Override
    public Integer getValue() {
        return null;
    }

    @Converter
    public static class SocialTypeConverter extends EnumConverter<SocialType> {

        public SocialTypeConverter() {
            super(SocialType.class);
        }
    }
}
