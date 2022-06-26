package com.triple.repository;

import com.triple.domain.Category;
import com.triple.domain.User;
import com.triple.domain.Place;
import com.triple.domain.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(false)
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PlaceRepository placeRepository;

    @Test
    public void 게시물테스트() {
        User user = new User("userA");
        userRepository.save(user);

        Place place = new Place("placeA");
        placeRepository.save(place);

        Post post = new Post(Category.REVIEW, user.getUserUUID(), "new!", place.getPlaceUUID());

        Post savedPost = postRepository.save(post);
        Post findPost = postRepository.findById(savedPost.getId()).get();

        assertThat(findPost.getId()).isEqualTo(post.getId());
        assertThat(findPost.getPostUUID()).isEqualTo(post.getPostUUID());
        assertThat(findPost.getCategory()).isEqualTo(Category.REVIEW);
        assertThat(findPost.getUserUUID()).isEqualTo(user.getUserUUID());
        assertThat(findPost.getContent()).isEqualTo(post.getContent());
        assertThat(findPost.getPlaceUUID()).isEqualTo(place.getPlaceUUID());

        assertThat(findPost).isEqualTo(post);
    }

}
