
package com.triple.controller.member;

import com.triple.domain.member.Member;
import com.triple.domain.member.MemberRepository2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members2")
public class MemberController2 {

    private final MemberRepository2 memberRepository2;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("member") Member member) {
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute Member member, BindingResult result) {
        if(result.hasErrors()) {
            return "members/addMemberForm";
        }

        memberRepository2.save(member);
        return "redirect:/";
    }
}
