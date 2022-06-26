package com.triple.repository;

import com.triple.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    @Query("select count(p.id) from Photo p where p.photoUUID= :photoUUID and p.postUUID= :postUUID")
    int existsPhoto(@Param("photoUUID") String photoUUID, @Param("postUUID") String postUUID);

    @Modifying
    @Query("delete from Photo where postUUID= :postUUID")
    void deletePhoto(@Param("postUUID") String postUUID);

    @Modifying
    @Query("update Photo p set p.del= 'Y' where p.postUUID= :postUUID")
    int setDelY(@Param("postUUID") String postUUID);

    @Modifying
    @Query("update Photo p set p.del= 'N' where p.photoUUID= :photoUUID")
    int setDelN(@Param("photoUUID") String photoUUID);

    @Modifying
    @Query("delete from Photo where del= 'Y' and postUUID= :postUUID")
    void deleteDelY(@Param("postUUID") String postUUID);
}
