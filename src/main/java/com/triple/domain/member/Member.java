package com.triple.domain.member;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"id", "loginId", "password", "name", "age"})
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String loginId;
    private String password;
    private String name;
    private int age;

    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
