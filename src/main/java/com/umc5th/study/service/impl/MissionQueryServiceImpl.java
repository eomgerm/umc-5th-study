package com.umc5th.study.service.impl;

import com.umc5th.study.repository.MissionRepository;
import com.umc5th.study.service.MissionQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;

    @Override
    public Boolean isMissionExists(Long missionId) {
        return missionRepository.existsById(missionId);
    }
}
