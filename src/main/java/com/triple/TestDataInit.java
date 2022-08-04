package com.triple;

import com.triple.domain.Category;
import com.triple.domain.Post;
import com.triple.domain.member.Member;
import com.triple.domain.member.MemberRepository;
import com.triple.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    /**
     * 테스트 데이터
     */
    @PostConstruct
    public void init() {
        postRepository.save(new Post(Category.REVIEW, "userA", "contentA", "placeA"));
        postRepository.save(new Post(Category.REVIEW, "userB", "contentB", "placeB"));

        Member member = new Member();
        member.setId(1L);
        member.setLoginId("userA");
        member.setPassword("userA!");
        member.setName("유저A");

        memberRepository.save(member);
    }
}
