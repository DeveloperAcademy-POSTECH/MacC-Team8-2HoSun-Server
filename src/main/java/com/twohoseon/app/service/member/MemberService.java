package com.twohoseon.app.service.member;

import com.twohoseon.app.entity.Member;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

/**
 * @author : hyunwoopark
 * @version : 1.0.0
 * @package : twohoseon
 * @name : MemberService
 * @date : 2023/10/07 4:20 PM
 * @modifyed : $
 **/
public interface MemberService extends UserDetailsService {
    Optional<Member> findByProviderId(String providerId);
}
