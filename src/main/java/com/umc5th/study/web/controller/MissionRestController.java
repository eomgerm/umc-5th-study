package com.umc5th.study.web.controller;

import com.umc5th.study.base.BaseResponse;
import com.umc5th.study.base.code.status.SuccessStatus;
import com.umc5th.study.converter.MissionConverter;
import com.umc5th.study.domain.Mission;
import com.umc5th.study.domain.mapping.MemberMission;
import com.umc5th.study.service.MissionCommandService;
import com.umc5th.study.validation.annotation.ExistMission;
import com.umc5th.study.validation.annotation.ExistStore;
import com.umc5th.study.web.dto.request.MissionRequestDto;
import com.umc5th.study.web.dto.response.MissionResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequiredArgsConstructor
@Tag(name = "미션 관련 API")
public class MissionRestController {

    private final MissionCommandService missionCommandService;

    @PostMapping("/api/stores/{storeId}/missions")
    @Operation(summary = "가게 미션 추가 API", description = "가게에 미션을 추가합니다.")
    public ResponseEntity<BaseResponse<MissionResponseDto.CreateMissionResponseDto>> createMission(
        @ExistStore @PathVariable("storeId") Long storeId,
        @RequestBody @Valid MissionRequestDto.CreateMissionRequestDto request) {
        Mission newMission = missionCommandService.createMission(storeId, request);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(BaseResponse.of(SuccessStatus.CREATED,
                                 MissionConverter.toCreateMissionResponseDto(newMission)));
    }

    @PostMapping("/api/missions/{missionId}/challenges")
    @Operation(summary = "미션 도전 API", description = "미션을 도전합니다.")
    public ResponseEntity<BaseResponse<MissionResponseDto.ChallengeMissionResponseDto>> challengeMission(
        @ExistMission @PathVariable("missionId") Long missionId) {
        MemberMission challenge = missionCommandService.challengeMission(missionId, 1L);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(BaseResponse.of(SuccessStatus.CREATED, MissionConverter.toChallengeMissionResponseDto(challenge)));
    }
}
