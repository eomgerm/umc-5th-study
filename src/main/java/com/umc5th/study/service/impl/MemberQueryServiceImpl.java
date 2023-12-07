package com.umc5th.study.service.impl;

import com.umc5th.study.base.code.status.ErrorStatus;
import com.umc5th.study.domain.Member;
import com.umc5th.study.domain.Review;
import com.umc5th.study.exception.handler.MemberException;
import com.umc5th.study.repository.MemberRepository;
import com.umc5th.study.repository.ReviewRepository;
import com.umc5th.study.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Override
    public Page<Review> getMyReview(Long userId, Integer page) {
        Member author = memberRepository.findById(userId).orElseThrow(() -> new MemberException(ErrorStatus.MEMBER_NOT_FOUND));

        return reviewRepository.findAllByAuthor(author, PageRequest.of(page - 1, 10));
    }
}
