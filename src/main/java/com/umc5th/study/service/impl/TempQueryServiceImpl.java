package com.umc5th.study.service.impl;

import com.umc5th.study.base.code.status.ErrorStatus;
import com.umc5th.study.exception.handler.TempHandler;
import com.umc5th.study.service.TempQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TempQueryServiceImpl implements TempQueryService {

    @Override
    public void checkFlag(Integer flag) {
        if (flag == 1) {
            throw new TempHandler(ErrorStatus.TEMP_EXCEPTION);
        }
    }
}
