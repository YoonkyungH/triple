package com.triple.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString(of = {"id", "postUUID", "photoUUID"})
public class Photo extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;

    private String postUUID;

    private String photoUUID;

    private char del;

}
