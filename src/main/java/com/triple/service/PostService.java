package com.triple.service;

import com.triple.repository.PostRepository;
import com.triple.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public void savePost(Post post, String action) {
        if(action.equals("ADD")) {
            if(postRepository.existsPost(post.getUserUUID(), post.getPlaceUUID()) == 0) {
                postRepository.save(post);
            }
        } else if(action.equals("MOD")) {
            postRepository.save(post);
        }
    }

    @Transactional
    public void updatePost(Post post) {
        postRepository.updatePost(post.getContent(), post.getUserUUID(), post.getPlaceUUID(), post.getCategory(), post.getPostUUID());
    }

    @Transactional
    public void deletePost(String userUUID, String postUUID) {
        postRepository.deletePost(userUUID, postUUID);
    }


    public int exitsFirstReview(String placeUUID) {
        return postRepository.exitsFirstReview(placeUUID);
    }
}
