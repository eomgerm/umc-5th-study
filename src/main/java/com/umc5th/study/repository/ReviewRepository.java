package com.umc5th.study.repository;

import com.umc5th.study.domain.Member;
import com.umc5th.study.domain.Review;
import com.umc5th.study.domain.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findAllByStore(Store store, PageRequest pageRequest);

    Page<Review> findAllByAuthor(Member author, Pageable pageable);
}
