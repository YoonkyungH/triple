package com.triple.domain.member;

import com.triple.domain.Grade;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString(of = {"id", "loginId", "password", "name", "age"})
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String loginId;
    private String password;
    private String name;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grade_id")  // 멤버별 등급
    private Grade grade;

    public Member(String name) {
        this.name = name;
    }

    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Member(String name, int age, Grade grade) {
        this.name = name;
        this.age = age;

        if(grade != null) {
            changeGrade(grade);
        }
    }

    /**
     * 양방향 연관관계 한 번에 처리하기 위한 편의 메소드
     */
    public void changeGrade(Grade grade) {
        this.grade = grade;
        grade.getMembers().add(this);
    }
}
