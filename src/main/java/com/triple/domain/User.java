package com.triple.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString(of = {"id", "userUUID", "name", "mileage"})
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String userUUID;

    private String name;

    private int mileage;

    public User(String name) {
        this.name = name;
    }
}
