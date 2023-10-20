package com.twohoseon.app.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * @author : yongjukim
 * @version : 1.0.0
 * @package : twohoseon
 * @name : PostCommentUpdateRequestDTO
 * @date : 2023/10/21
 * @modifyed : $
 **/

@Getter
@Schema(name = "PostCommentUpdateRequestDTO", description = "댓글 수정 요청 DTO")
public class PostCommentUpdateRequestDTO {
    @Schema(name = "postId", description = "포스트 ID")
    Long postId;
    @Schema(name = "postCommentId", description = "댓글 ID")
    Long postCommentId;
    @Schema(name = "content", description = "수정할 내용")
    String content;
}