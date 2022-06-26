package com.triple.controller;

import com.triple.domain.Category;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter @Setter
@ToString(of = {"type", "action", "reviewId", "content", "attachedPhotoIds", "userId", "placeId"})
public class PostForm {

    private Category type;
    private String action;
    private String reviewId;
    private String content;
    private List<String> attachedPhotoIds;
    private String userId;
    private String placeId;
}
