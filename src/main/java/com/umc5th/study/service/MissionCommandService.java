package com.umc5th.study.service;

import com.umc5th.study.domain.Mission;
import com.umc5th.study.web.dto.request.MissionRequestDto;

public interface MissionCommandService {

    Mission createMission(Long storeId, MissionRequestDto.CreateMissionRequestDto request);

}
