package com.triple.thymeleaf.basic;

import com.triple.domain.User2;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/basic")
@NoArgsConstructor
public class BasicController {

    @GetMapping("/thymeleaf-basic")
    public String thymeleafBasic(Model model) {
        // 단순 텍스트
        model.addAttribute("data", "Thymeleaf <b>TEST</b>");

        // 날짜 포맷팅
        model.addAttribute("localDateTime", LocalDateTime.now());

        // URL 링크
        // 링크 눌러 이동된 페이지의 URL 확인해보기
        // http://localhost:8080/ex/data1?param2=data2
        model.addAttribute("param1", "data1");
        model.addAttribute("param2", "data2");

        // 리터럴(소스코드상 고정된 값) 사용시 '' 또는 ||로 감싸 사용
        model.addAttribute("data2", "Spring");

        // 연산
        model.addAttribute("nullData", null);

        // 속성값 설정
        model.addAttribute("isChecked", true);
        model.addAttribute("isNotChecked", false);

        // 반복, 조건부 평가, 블록
        addUsers(model);

        // 자바스크립트 인라인
        model.addAttribute("user", new User2("userA", 10));
        addUsers(model);

        return "basic/basic";
    }

    // 반복
    private void addUsers(Model model) {
        List<User2> list = new ArrayList<>();

        list.add(new User2("userA", 10));
        list.add(new User2("userB", 20));
        list.add(new User2("userC", 30));

        model.addAttribute("users", list);
    }

}
