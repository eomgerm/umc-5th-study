package com.umc5th.study.converter;

import com.umc5th.study.domain.Member;
import com.umc5th.study.domain.Mission;
import com.umc5th.study.domain.Store;
import com.umc5th.study.domain.enums.MissionStatus;
import com.umc5th.study.domain.mapping.MemberMission;
import com.umc5th.study.web.dto.request.MissionRequestDto;
import com.umc5th.study.web.dto.response.MissionResponseDto;
import java.util.List;
import org.springframework.data.domain.Page;

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

    public static MissionResponseDto.MissionPreviewListResponseDto toMissionPreviewListResponseDto(Page<Mission> missions) {
        List<MissionResponseDto.MissionPreviewResponseDto> missionPreviewResponseDtoList = missions.stream()
                                                                                                   .map(MissionConverter::toMissionPreviewResponseDto)
                                                                                                   .toList();

        return MissionResponseDto.MissionPreviewListResponseDto.builder()
                                                               .missionList(missionPreviewResponseDtoList)
                                                               .listSize(missionPreviewResponseDtoList.size())
                                                               .totalElements(missions.getTotalElements())
                                                               .isFirst(missions.isFirst())
                                                               .isLast(missions.isLast())
                                                               .totalPage(missions.getTotalPages())
                                                               .build();
    }

    public static MissionResponseDto.MissionPreviewResponseDto toMissionPreviewResponseDto(Mission mission) {
        return MissionResponseDto.MissionPreviewResponseDto.builder()
                                                           .missionId(mission.getMissionId())
                                                           .storeName(mission.getStore().getName())
                                                           .reward(mission.getReward())
                                                           .contents(mission.getContents())
                                                           .build();
    }
}
