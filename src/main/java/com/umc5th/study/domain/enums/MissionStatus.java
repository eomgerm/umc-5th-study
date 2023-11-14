package com.umc5th.study.domain.enums;

import com.umc5th.study.domain.common.BaseEnum;
import com.umc5th.study.utils.EnumConverter;
import jakarta.persistence.Converter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MissionStatus implements BaseEnum {
    CHALLENGING(0, "도전 중"),
    COMPLETE(1, "완료"),
    ;

    private final Integer value;
    private final String description;

    @Override
    public Integer getValue() {
        return value;
    }

    @Converter
    public static class MissionStatusConverter extends EnumConverter<MissionStatus> {

        public MissionStatusConverter() {
            super(MissionStatus.class);
        }
    }
}
