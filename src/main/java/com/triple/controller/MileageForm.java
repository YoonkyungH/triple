package com.triple.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString(of = {"writerUUID", "postUUID", "mileage", "photo", "content", "firstReview"})
public class MileageForm {

    private String writerUUID;
    private String postUUID;
    private int mileage;

    private boolean photo;
    private boolean content;
    private boolean firstReview;

}
