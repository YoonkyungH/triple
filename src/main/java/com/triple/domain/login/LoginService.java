package com.triple.domain.login;

import com.triple.domain.member.Member;
import com.triple.domain.member.MemberRepository2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository2 memberRepository2;

    /**
     * password가 일치하면 회원을 반환, 다르면 null을 반환
     */
    public Member login(String loginId, String password) {
        return memberRepository2.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
