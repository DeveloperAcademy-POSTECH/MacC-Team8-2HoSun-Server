package com.twohoseon.app.dto.request.review;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : hyunwoopark
 * @version : 1.0.0
 * @package : twohoseon
 * @name : ReviewCreateRequestDTO
 * @date : 11/5/23 2:36 AM
 * @modifyed : $
 **/
@Schema(name = "ReviewRequestDTO", description = "후기 생성 요청 DTO")
@Getter
public class ReviewRequestDTO {
    //TODO Controller, Service에서 변경점 수정
    @Schema(name = "title", description = "후기 제목")
    private String title;
    @Schema(name = "contents", description = "후기 내용")
    private String contents;
    @Schema(name = "price", description = "제품 가격")
    private int price;
    @Schema(name = "image", description = "후기 이미지")
    private MultipartFile image;
    @Schema(name = "isPurchased", description = "구매 여부")
    private boolean isPurchased;
}
