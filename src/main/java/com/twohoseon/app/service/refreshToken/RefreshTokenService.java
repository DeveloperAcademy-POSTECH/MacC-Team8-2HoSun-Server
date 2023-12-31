package com.twohoseon.app.service.refreshToken;

import com.twohoseon.app.dto.request.member.LogoutRequest;
import com.twohoseon.app.dto.response.JWTToken;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author : hyunwoopark
 * @version : 1.0.0
 * @package : twohoseon
 * @name : RefreshTokenService
 * @date : 10/16/23 3:58 PM
 * @modifyed : $
 **/
public interface RefreshTokenService {

    void saveRefreshToken(String refreshToken, String identifier, long expirationTime);

    void saveRefreshTokenFromTokenDTO(JWTToken tokenDTO, String providerId);

    JWTToken renewToken(String refreshToken);

    void logout(HttpServletRequest request, LogoutRequest logoutRequest);
}
