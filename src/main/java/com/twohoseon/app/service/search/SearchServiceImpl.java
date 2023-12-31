package com.twohoseon.app.service.search;

import com.twohoseon.app.dto.response.post.PostSummary;
import com.twohoseon.app.entity.member.Member;
import com.twohoseon.app.enums.post.PostStatus;
import com.twohoseon.app.enums.post.VisibilityScope;
import com.twohoseon.app.repository.post.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : yongjukim
 * @version : 1.0.0
 * @package : twohoseon
 * @name : SearchServiceImpl
 * @date : 2023/10/22
 * @modifyed : $
 **/

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final PostRepository postRepository;

    @Override
    @Transactional
    public List<PostSummary> getSearchByKeyword(PostStatus postStatus, VisibilityScope visibilityScope, Pageable pageable, String keyword) {
        Member reqMember = getMemberFromRequest();
        switch (postStatus) {
            case ACTIVE -> {
                return postRepository.findActivePostsByKeyword(visibilityScope, reqMember, pageable, keyword);
            }
            case CLOSED -> {
                return postRepository.findClosedPostsByKeyword(visibilityScope, reqMember, pageable, keyword);
            }
            case REVIEW -> {
                return postRepository.findReviewPostsByKeyword(visibilityScope, reqMember, pageable, keyword);
            }
        }
        return null;
//        return postRepository.findAllPostsByKeyword(pageable, keyword, member.getId());
    }

}
