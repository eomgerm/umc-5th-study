package com.umc5th.study.repository;

import com.umc5th.study.domain.Region;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Integer> {

    Optional<Region> findByName(String name);

    Boolean existsByName(String name);
}
