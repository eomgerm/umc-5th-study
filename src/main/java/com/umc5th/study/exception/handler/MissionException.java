package com.umc5th.study.exception.handler;

import com.umc5th.study.base.code.BaseCode;
import com.umc5th.study.exception.BaseException;

public class MissionException extends BaseException {

    public MissionException(BaseCode code) {
        super(code);
    }
}
