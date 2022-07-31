package com.triple.thymeleaf.basic;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

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

        model.addAttribute("data2", "Spring");

        return "basic/basic";
    }

}
