package com.triple.controller.login;

import com.triple.controller.SessionConst;
import com.triple.controller.session.SessionManager;
import com.triple.domain.login.LoginService;
import com.triple.domain.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final SessionManager sessionManager;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "login/loginForm";
    }

    /**
     * version 1
     */
//    @PostMapping("/login")
//    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletResponse response) {
//        if(bindingResult.hasErrors()) {
//            return "login/loginForm";
//        }
//
//        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
//        log.info("login? {}", loginMember);
//
//        if (loginMember == null) {
//            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
//            return "login/loginForm";
//        }
//
//        // 로그인 성공 처리
//        log.info("loginMember.getId()? {}", loginMember.getId());
//        log.info("String.valueOf(loginMember.getId())? {}", String.valueOf(loginMember.getId()));
//
//        Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
//        log.info("cookie? {}", idCookie);
//        response.addCookie(idCookie);
//
//        return "redirect:/";
//    }
//
//
//    @PostMapping("/logout")
//    public String logout(HttpServletResponse response) {
//        expireCookie(response, "memberId");
//        return "redirect:/";
//    }

    /**
     * version 2
     */
//    @PostMapping("/login")
//    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletResponse response) {
//        if (bindingResult.hasErrors()) {
//            return "login/loginForm";
//        }
//
//        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
//        log.info("login? {}", loginMember);
//
//        if (loginMember == null) {
//            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
//            return "login/loginForm";
//        }
//
//        // 로그인 성공 처리
//        // 세셩 관리자를 통해 세션 생성, 회원 데이터 보관
//        sessionManager.createSession(loginMember, response);
//
//        return "redirect:/";
//    }
//
//    @PostMapping("/logout")
//    public String logout(HttpServletRequest request) {
//        sessionManager.expire(request);
//        return "redirect:/";
//    }

    /**
     * version 3
     */
//    @PostMapping("/login")
//    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletRequest request) {
//        if (bindingResult.hasErrors()) {
//            return "login/loginForm";
//        }
//
//        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
//        log.info("login? {}", loginMember);
//
//        if (loginMember == null) {
//            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
//            return "login/loginForm";
//        }
//
//        // 로그인 성공 처리
//        // 세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성
//        HttpSession session = request.getSession();
//        // 세션에 로그인 회원 정보 보관
//        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
//
//        return "redirect:/";
//    }
//
//    @PostMapping("/logout")
//    public String logout(HttpServletRequest request) {
//        // 세션 삭제
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            session.invalidate();
//        }
//
//        return "redirect:/";
//    }

    /**
     * version 4
     * 로그인 이후 redirect 처리
     */
    @PostMapping("/login")
    public String loginV4(
            @Valid @ModelAttribute LoginForm form, BindingResult bindingResult,
            @RequestParam(defaultValue = "/") String redirectURL,
            HttpServletRequest request)
    {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
        log.info("login? {}", loginMember);

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        //로그인 성공 처리

        //세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성
        HttpSession session = request.getSession();
        //세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        //redirectURL 적용
        return "redirect:" + redirectURL;
    }


    /**
     * 서버에서 해당 쿠키의 종료 날짜를 0으로 지정 -> 해당 쿠키 즉시 종료(시간을 예전 시간으로 돌려 쿠키 삭제)
     */
    private void expireCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
