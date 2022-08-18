package com.triple.controller.argumentresolver;

import com.triple.controller.SessionConst;
import com.triple.domain.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * 파라미터가 해당 resolver에 의해 수행되는 타입인지 체크하여 boolean을 반환
     * true가 반환될 경우, resolverArgument method 실행
     *
     * 타입 체크시 class를 .isAssignableForm() 사용 또는 Annotation을 새로 생성하여 체크할 수 있음
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        log.info("supportsParameter 실행");

        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);
        boolean hasMemberType = Member.class.isAssignableFrom(parameter.getParameterType());

        return hasLoginAnnotation && hasMemberType;
    }

    /**
     * 실제 파라미터와 바인딩하여 반환할 object를 생성하는 메소드
     * 컨트롤러 호출 직전 필요한 파라미터 정보를 생성해줌
     *
     * NativeWebRequest Object에 접근하여 client request의 파라미터를 컨트롤러보다 우선으로 받아 작업 가능
     *
     * 해당 핸들러 메소드 안 파라미터에서 바인딩을 원하는 객체 타입에 맞체 반환
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        log.info("resolveArgument 실행");

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = request.getSession(false);

        if (session == null) {
            return null;
        }

        return session.getAttribute(SessionConst.LOGIN_MEMBER);
    }
}
