package com.twohoseon.app.service.member;

import com.twohoseon.app.dto.ConsumerTypeRequest;
import com.twohoseon.app.dto.request.member.ProfileRequest;
import com.twohoseon.app.dto.response.mypage.BlockedMember;
import com.twohoseon.app.dto.response.profile.ProfileInfo;
import com.twohoseon.app.entity.member.Member;
import com.twohoseon.app.service.CommonService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

/**
 * @author : hyunwoopark
 * @version : 1.0.0
 * @package : twohoseon
 * @name : MemberService
 * @date : 2023/10/07 4:20 PM
 * @modifyed : $
 **/
public interface MemberService extends UserDetailsService, CommonService {
    void setUserProfile(ProfileRequest profileRequest, MultipartFile imageFile);

    boolean validateDuplicateUserNickname(String userNickname);

    Optional<Member> findByProviderId(String providerId);

    void registerToken(String deviceToken);

    void setConsumptionTendency(ConsumerTypeRequest consumptionTendencyRequestDTO);

    void deleteMember();

    void deleteAppleMember(String providerId);

//    void deleteSubscriptions(Member reqMember);


    ProfileInfo getProfile();

    void blockMember(Long memberId);

    void unblockMember(Long memberId);

    List<BlockedMember> getBlockedMembers();
}
