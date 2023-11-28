package com.umc5th.study.web.controller;

import com.umc5th.study.base.BaseResponse;
import com.umc5th.study.converter.TempConverter;
import com.umc5th.study.service.TempQueryService;
import com.umc5th.study.web.dto.TempResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TempRestController {

    private final TempQueryService tempQueryService;

    @GetMapping("/test")
    public BaseResponse<TempResponse.TempTestDTO> testAPI() {
        return BaseResponse.onSuccess(TempConverter.toTempTestDTO());
    }

    @GetMapping("/exception")
    public BaseResponse<TempResponse.TempExceptionDTO> exceptionAPI(@RequestParam Integer flag) {
        tempQueryService.checkFlag(flag);
        return BaseResponse.onSuccess(TempConverter.toTempExceptionDTO(flag));
    }

}
