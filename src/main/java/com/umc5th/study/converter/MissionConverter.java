package com.umc5th.study.converter;

import com.umc5th.study.domain.Member;
import com.umc5th.study.domain.Mission;
import com.umc5th.study.domain.Store;
import com.umc5th.study.domain.enums.MissionStatus;
import com.umc5th.study.domain.mapping.MemberMission;
import com.umc5th.study.web.dto.request.MissionRequestDto;
import com.umc5th.study.web.dto.response.MissionResponseDto;

public class MissionConverter {

    public static Mission toMission(MissionRequestDto.CreateMissionRequestDto request, Store store) {
        return Mission.builder()
                      .deadline(request.getDeadline())
                      .reward(request.getReward())
                      .contents(request.getContents())
                      .store(store)
                      .build();
    }

    public static MissionResponseDto.CreateMissionResponseDto toCreateMissionResponseDto(Mission mission) {
        return MissionResponseDto.CreateMissionResponseDto.builder()
                                                          .missionId(mission.getMissionId())
                                                          .build();
    }

    public static MemberMission toMemberMission(Mission mission, Member member) {
        return MemberMission.builder()
                            .member(member)
                            .mission(mission)
                            .status(MissionStatus.CHALLENGING)
                            .build();
    }

    public static MissionResponseDto.ChallengeMissionResponseDto toChallengeMissionResponseDto(MemberMission challenge) {
        return MissionResponseDto.ChallengeMissionResponseDto.builder()
                                                             .challengeId(challenge.getId())
                                                             .build();
    }
}
