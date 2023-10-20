package com.twohoseon.app.controller;

import com.twohoseon.app.dto.request.PostCommentRequestDTO;
import com.twohoseon.app.dto.request.PostCreateRequestDTO;
import com.twohoseon.app.dto.request.VoteCreateRequestDTO;
import com.twohoseon.app.dto.response.GeneralResponseDTO;
import com.twohoseon.app.dto.response.PostResponseDTO;
import com.twohoseon.app.entity.post.PostComment;
import com.twohoseon.app.enums.StatusEnum;
import com.twohoseon.app.service.post.PostCommentService;
import com.twohoseon.app.service.post.PostLikeService;
import com.twohoseon.app.service.post.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author : hyunwoopark
 * @version : 1.0.0
 * @package : twohoseon
 * @name : PostRestController
 * @date : 10/18/23 4:04 PM
 * @modifyed : $
 **/

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Post", description = "게시글 관련 API")
@RequestMapping("/api/posts")
public class PostRestController {
    private final PostService postService;
    private final PostLikeService postLikeService;
    private final PostCommentService postCommentService;

    @Operation(summary = "게시글 작성")
    @PostMapping
    public ResponseEntity<GeneralResponseDTO> createPost(@RequestBody PostCreateRequestDTO postCreateRequestDTO) {
        postService.createPost(postCreateRequestDTO);
        GeneralResponseDTO responseDTO = GeneralResponseDTO.builder()
                .status(StatusEnum.OK)
                .message("success")
                .build();
        return ok(responseDTO);
    }

    @GetMapping
    @Operation(summary = "게시글 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 조회 성공", useReturnTypeSchema = true),
    })
    public ResponseEntity<PostResponseDTO> fetchPosts(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);

        PostResponseDTO responseDTO = PostResponseDTO.builder()
                .status(StatusEnum.OK)
                .message("")
                .data(postService.fetchPosts(pageable))
                .build();
        return ok(responseDTO);
    }

    @PostMapping("/{postId}/votes")
    public ResponseEntity<GeneralResponseDTO> vote(@PathVariable Long postId, @RequestBody VoteCreateRequestDTO voteCreateRequestDTO) {
        postService.createVote(postId, voteCreateRequestDTO.getVoteType());
        GeneralResponseDTO responseDTO = GeneralResponseDTO.builder()
                .status(StatusEnum.OK)
                .message("success")
                .build();
        return ok(responseDTO);
    }

    //TODO 댓글 작성
    @PostMapping("/api/postComments/create")
    public ResponseEntity<GeneralResponseDTO> createPostComment(@RequestBody PostCommentRequestDTO postCommentRequestDTO) {

        postCommentService.commentCreate(postCommentRequestDTO);

        GeneralResponseDTO.GeneralResponseDTOBuilder responseDTOBuilder = GeneralResponseDTO.builder();

        responseDTOBuilder
                .status(StatusEnum.OK)
                .message("create success");

        return ResponseEntity.ok(responseDTOBuilder.build());
    }

    //TODO 댓글 조회
    @GetMapping("/api/postComments/read")
    public ResponseEntity<GeneralResponseDTO> readPostComment(@RequestBody Map<String, Long> postCommentRequest) {

        List<PostComment> postCommentList = postCommentService.commentRead(postCommentRequest.get("postId"));

        GeneralResponseDTO.GeneralResponseDTOBuilder responseDTOBuilder = GeneralResponseDTO.builder();

        responseDTOBuilder
                .status(StatusEnum.OK)
                .message("create success")
                .data(postCommentList);

        return ResponseEntity.ok(responseDTOBuilder.build());
    }

    //TODO 좋아요
    @PostMapping("/api/postLikes/insert")
    public ResponseEntity<GeneralResponseDTO> insertPostLike(@RequestBody Map<String, Long> postLikeRequest) {

        postLikeService.insert(postLikeRequest.get("postId"));

        GeneralResponseDTO.GeneralResponseDTOBuilder responseDTOBuilder = GeneralResponseDTO.builder();

        responseDTOBuilder
                .status(StatusEnum.OK)
                .message("check success");

        return ResponseEntity.ok(responseDTOBuilder.build());
    }

    //TODO 좋아요 취소
    @DeleteMapping("/api/postLikes/delete")
    public ResponseEntity<GeneralResponseDTO> deletePostLike(@RequestBody Map<String, Long> postLikeRequest) {

        postLikeService.delete(postLikeRequest.get("postId"));

        GeneralResponseDTO.GeneralResponseDTOBuilder responseDTOBuilder = GeneralResponseDTO.builder();

        responseDTOBuilder
                .status(StatusEnum.OK)
                .message("delete success");

        return ResponseEntity.ok(responseDTOBuilder.build());
    }
}
