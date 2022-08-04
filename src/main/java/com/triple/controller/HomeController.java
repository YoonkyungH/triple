package com.triple.controller;

import com.triple.controller.session.SessionManager;
import com.triple.domain.member.Member;
import com.triple.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

//    @GetMapping("/")
    public String home() {
        return "home";
    }

    /**
     * version 1
     */
//    @GetMapping("/")
//    public String homeLogin(
//            @CookieValue(name = "memberId", required = false) Long memberId, Model model
//    ) {
//
//        if(memberId == null) {
//            return "home";
//        }
//
//        // 로그인
//        Member loginMember = memberRepository.findById(memberId);
//        if (loginMember == null) {
//            return "home";
//        }
//
//        model.addAttribute("member", loginMember);
//        return "loginHome";
//    }

    /**
     * version 2
     */
    @GetMapping("/")
    public String homeLoginV2(HttpServletRequest request, Model model) {
        // 세션 관리자에 저장된 회원 정보 조회
        Member member = (Member)sessionManager.getSession(request);
        if (member == null) {
            return "home";
        }

        // 로그인
        model.addAttribute("member", member);
        return "loginHome";
    }
}