package com.umc5th.study.domain;

import com.umc5th.study.domain.common.BaseEntity;
import com.umc5th.study.domain.enums.Gender;
import com.umc5th.study.domain.enums.SocialType;
import com.umc5th.study.domain.enums.UserStatus;
import com.umc5th.study.domain.mapping.MemberFoodPreference;
import com.umc5th.study.domain.mapping.MemberMission;
import com.umc5th.study.domain.mapping.MemberTermAgreement;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, length = 20)
    private String name;

    @Convert(converter = Gender.GenderConverter.class)
    @Column(columnDefinition = "TINYINT")
    private Gender gender;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false, length = 200)
    private String address;

    @Column(nullable = false)
    private Integer point;

    @Convert(converter = SocialType.SocialTypeConverter.class)
    @Column(columnDefinition = "TINYINT DEFAULT NULL")
    private SocialType socialType;

    @Convert(converter = UserStatus.UserStatusConverter.class)
    @Column(columnDefinition = "TINYINT DEFAULT 0")
    private UserStatus status;

    private LocalDateTime inactiveDate;

    @Column(nullable = false, length = 200)
    private String email;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberFoodPreference> foodPreferences = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberMission> missions = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberTermAgreement> termAgreements = new ArrayList<>();

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();
}
