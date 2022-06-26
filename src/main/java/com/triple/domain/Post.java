package com.triple.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString(of = {"id", "category", "postUUID", "userUUID", "content", "placeUUID"})
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Category category;  // 리뷰의 경우 "REVIEW"

    private String postUUID;

    private String userUUID;

    private String content;

    private String placeUUID;

    public Post(Category category, String userUUID, String content, String placeUUID) {
        this.category = category;
        this.userUUID = userUUID;
        this.content = content;
        this.placeUUID = placeUUID;
    }
}
