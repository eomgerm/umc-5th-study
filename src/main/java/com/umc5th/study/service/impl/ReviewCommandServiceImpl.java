package com.umc5th.study.service.impl;

import com.umc5th.study.base.code.status.ErrorStatus;
import com.umc5th.study.converter.ReviewConverter;
import com.umc5th.study.domain.Member;
import com.umc5th.study.domain.Review;
import com.umc5th.study.domain.Store;
import com.umc5th.study.exception.handler.MemberException;
import com.umc5th.study.exception.handler.StoreException;
import com.umc5th.study.repository.MemberRepository;
import com.umc5th.study.repository.ReviewRepository;
import com.umc5th.study.repository.StoreRepository;
import com.umc5th.study.service.ReviewCommandService;
import com.umc5th.study.web.dto.request.ReviewRequestDto.CreateReviewRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public Review createReview(Long storeId, CreateReviewRequestDto request, Long memberId) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new StoreException(ErrorStatus.STORE_NOT_FOUND));

        Review review = ReviewConverter.toReview(request);

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberException(ErrorStatus.MEMBER_NOT_FOUND));

        review.setStore(store);
        review.setAuthor(member);

        reviewRepository.save(review);

        return review;
    }
}
