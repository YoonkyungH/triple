package com.triple.controller;

import com.triple.domain.*;
import com.triple.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PhotoService photoService;
    private final MileageService mileageService;

    @PostMapping("/events")
    public String events(@RequestBody PostForm postForm) {

        if(postForm.getAction().equals("DEL")) {  // 게시물 삭제
            postService.deletePost(postForm.getUserId(), postForm.getReviewId());

            mileageService.deleteMileage(postForm.getUserId(), postForm.getReviewId());

            photoService.deletePhoto(postForm.getReviewId());
        } else {    // 게시물 등록&수정
            Post post = new Post();
            MileageForm mileageForm = new MileageForm();

            post.setCategory(postForm.getType());
            post.setContent(postForm.getContent());
            post.setPlaceUUID(postForm.getPlaceId());


            if(postForm.getContent().length() == 0) {
                mileageForm.setContent(false);
            } else {
                mileageForm.setContent(true);
            }

            post.setUserUUID(postForm.getUserId());
            mileageForm.setWriterUUID(postForm.getUserId());

            if(postForm.getAction().equals("ADD")) {  // 등록
                String postUUID = UUID.randomUUID().toString();

                post.setPostUUID(postUUID);
                mileageForm.setPostUUID(postUUID);

                if(postForm.getAttachedPhotoIds().size() != 0) {
                    for (String attachedPhotoId : postForm.getAttachedPhotoIds()) {
                        Photo photo = new Photo();

                        photo.setPhotoUUID(attachedPhotoId);
                        photo.setPostUUID(postUUID);
                        photoService.savePhoto(photo);
                    }
                    mileageForm.setPhoto(true);
                } else {
                    mileageForm.setPhoto(false);
                }

                if(postService.exitsFirstReview(postForm.getPlaceId()) == 0) {
                    mileageForm.setFirstReview(true);
                } else {
                    mileageForm.setFirstReview(false);
                }

                postService.savePost(post, postForm.getAction());
                mileageService.saveMileage(mileageForm);

            } else if(postForm.getAction().equals("MOD")) {   // 수정
                post.setPostUUID(postForm.getReviewId());
                mileageForm.setPostUUID(postForm.getReviewId());


                photoService.setDelY(postForm.getReviewId());

                if(postForm.getAttachedPhotoIds().size() != 0) {
                    for (String attachedPhotoId : postForm.getAttachedPhotoIds()) {

                        if(photoService.existsPhoto(attachedPhotoId, postForm.getReviewId()) > 0) {
                            photoService.setDelN(attachedPhotoId);
                        } else {
                            Photo photo = new Photo();

                            photo.setPhotoUUID(attachedPhotoId);
                            photo.setPostUUID(postForm.getReviewId());
                            photo.setDel('N');
                            photoService.savePhoto(photo);
                        }
                    }
                    photoService.deleteDelY(postForm.getReviewId());
                    mileageForm.setPhoto(true);
                } else {
                    mileageForm.setPhoto(false);
                }

                postService.updatePost(post);
                mileageService.updateMileage(mileageForm);
            }
        }


        return "redirect:/";
    }

}
