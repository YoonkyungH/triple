package com.triple.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "writerUUID", "mileage", "photo", "content", "firstReview"})
public class Mileage extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String writerUUID;
    private String postUUID;

    private int mileage;

    private boolean photo;
    private boolean content;
    private boolean firstReview;


    public Mileage(String writerUUID, String postUUID, int mileage, boolean photo, boolean content, boolean firstReview) {
        this.writerUUID = writerUUID;
        this.postUUID = postUUID;
        this.mileage = mileage;
        this.photo = photo;
        this.content = content;
        this.firstReview = firstReview;
    }
}
