package com.twohoseon.app.service.search;

import com.twohoseon.app.dto.response.post.SearchPostInfo;
import com.twohoseon.app.enums.post.PostStatus;
import com.twohoseon.app.service.CommonService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author : yongjukim
 * @version : 1.0.0
 * @package : twohoseon
 * @name : SearchService
 * @date : 2023/10/22
 * @modifyed : $
 **/
public interface SearchService extends CommonService {
    @Transactional
    List<SearchPostInfo> getSearchByKeyword(PostStatus postStatus, Pageable pageable, String keyword);
}
