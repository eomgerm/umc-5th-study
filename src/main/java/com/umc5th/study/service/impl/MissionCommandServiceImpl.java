package com.umc5th.study.service.impl;

import com.umc5th.study.base.code.status.ErrorStatus;
import com.umc5th.study.converter.MissionConverter;
import com.umc5th.study.domain.Member;
import com.umc5th.study.domain.Mission;
import com.umc5th.study.domain.Store;
import com.umc5th.study.domain.mapping.MemberMission;
import com.umc5th.study.exception.handler.MemberException;
import com.umc5th.study.exception.handler.MissionException;
import com.umc5th.study.exception.handler.StoreException;
import com.umc5th.study.repository.MemberMissionRepository;
import com.umc5th.study.repository.MemberRepository;
import com.umc5th.study.repository.MissionRepository;
import com.umc5th.study.repository.StoreRepository;
import com.umc5th.study.service.MissionCommandService;
import com.umc5th.study.web.dto.request.MissionRequestDto.CreateMissionRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;


    @Override
    @Transactional
    public Mission createMission(Long storeId, CreateMissionRequestDto request) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new StoreException(ErrorStatus.STORE_NOT_FOUND));
        System.out.println(request.getContents());

        return missionRepository.save(MissionConverter.toMission(request, store));
    }

    @Override
    @Transactional
    public MemberMission challengeMission(Long missionId, Long memberId) {
        Mission mission = missionRepository.findById(missionId).orElseThrow(() -> new MissionException(ErrorStatus.MISSION_NOT_FOUND));
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberException(ErrorStatus.MEMBER_NOT_FOUND));

        MemberMission challenge = MissionConverter.toMemberMission(mission, member);

        memberMissionRepository.save(challenge);

        return challenge;
    }
}
