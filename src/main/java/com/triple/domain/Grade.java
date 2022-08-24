package com.triple.domain;

import com.triple.domain.member.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name"})
public class Grade {

    @Id
    @GeneratedValue
    @Column(name = "grade_id")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "grade")
    List<Member> members = new ArrayList<>();

    public Grade(String name) {
        this.name = name;
    }
}
