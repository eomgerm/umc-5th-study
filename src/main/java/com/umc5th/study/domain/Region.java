package com.umc5th.study.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
public class Region {

    @Id
    private Integer regionId;

    @Column(nullable = false, length = 10)
    private String name;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private List<Store> stores;
}
