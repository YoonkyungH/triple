package com.triple.service;

import com.triple.domain.Category;
import com.triple.domain.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
@Rollback(value = false)
public class PostServiceTest {

    @Autowired
    PostService postService;

    @Test
    public void 게시물게시() {

    }

}
