package com.umc5th.study.converter;

import com.umc5th.study.web.dto.TempResponse;

public class TempConverter {

    public static TempResponse.TempTestDTO toTempTestDTO() {
        return TempResponse.TempTestDTO.builder()
                                       .test("This is Test!")
                                       .build();
    }

    public static TempResponse.TempExceptionDTO toTempExceptionDTO(Integer flag) {
        return TempResponse.TempExceptionDTO.builder()
                                            .flag(flag)
                                            .build();
    }
}
