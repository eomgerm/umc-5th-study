package com.umc5th.study.utils;

import com.umc5th.study.domain.common.BaseEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Objects;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Converter
public class EnumConverter<T extends BaseEnum> implements AttributeConverter<T, Integer> {

    private final Class<T> enumClass;

    @Override
    public Integer convertToDatabaseColumn(T attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public T convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        T[] enumConstants = enumClass.getEnumConstants();
        for (T constant : enumConstants) {
            if (Objects.equals(constant.getValue(), dbData)) {
                return constant;
            }
        }

        throw new IllegalArgumentException();
    }
}
