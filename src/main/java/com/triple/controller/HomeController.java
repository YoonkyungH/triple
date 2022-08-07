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
import javax.servlet.http.HttpSession;

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
//    @GetMapping("/")
//    public String homeLoginV2(HttpServletRequest request, Model model) {
//        // 세션 관리자에 저장된 회원 정보 조회
//        Member member = (Member)sessionManager.getSession(request);
//        if (member == null) {
//            return "home";
//        }
//
//        // 로그인
//        model.addAttribute("member", member);
//        return "loginHome";
//    }

    /**
     * version 3
     */
    @GetMapping("/")
    public String homeLogin(HttpServletRequest request, Model model) {
        // 세션이 없으면 home
        // getSession의 옵션을 false로 주지 않으면 새로 생성해버리기 때문에 꼭 false로
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "home";
        }

        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        // 세션이 회원 데이터가 없으면 home
        if (loginMember == null) {
            return "home";
        }

        // 세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginMember);
        return "loginHome";
    }
}
