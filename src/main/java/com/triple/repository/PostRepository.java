package com.triple.repository;

import com.triple.domain.Category;
import com.triple.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {

    String findByPostUUID(Long id);

    @Modifying
    @Query("update Post set content= :content, userUUID= :userUUID, placeUUID= :placeUUID, category= :category, postUUID= :postUUID where postUUID= :postUUID")
    void updatePost(@Param("content") String content,
               @Param("userUUID") String userUUID,
               @Param("placeUUID") String placeUUID,
               @Param("category") Category category,
               @Param("postUUID") String postUUID);

    @Modifying
    @Query("delete from Post where userUUID= :userUUID and postUUID= :postUUID")
    void deletePost(@Param("userUUID") String userUUID, @Param("postUUID") String postUUID);

    @Query("select count(p.id) from Post p where p.userUUID= :writerUUID and p.placeUUID= :placeUUID")
    int existsPost(@Param("writerUUID") String writerUUID, @Param("placeUUID") String placeUUID);


    @Query("select count(p.id) from Post p where p.placeUUID = :placeUUID")
    int exitsFirstReview(@Param("placeUUID") String placeUUID);

    Post findPostsByPlaceUUIDEquals(String postUUID);
}
