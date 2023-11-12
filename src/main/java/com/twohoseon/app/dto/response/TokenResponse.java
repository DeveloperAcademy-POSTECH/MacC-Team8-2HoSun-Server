package com.twohoseon.app.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.twohoseon.app.common.StatusEnumSerializer;
import com.twohoseon.app.enums.StatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * @author : hyunwoopark
 * @version : 1.0.0
 * @package : twohoseon
 * @name : TokenResponse
 * @date : 11/11/23 3:22 AM
 * @modifyed : $
 **/
@Data
@Schema(name = "PostResponseDTO", description = "게시글 응답 DTO")
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenResponse {
    @Schema(name = "status", type = "int", description = "응답 상태")
    @JsonSerialize(using = StatusEnumSerializer.class)
    private StatusEnum status;

    @Schema(name = "message", type = "int", description = "응답 메시지")
    private String message;

    @Schema(name = "data", type = "TokenDTO", description = "응답 데이터")
    private TokenDTO data;
}
