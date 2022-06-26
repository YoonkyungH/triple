package com.triple.repository;

import com.triple.domain.Mileage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MileageRepository extends JpaRepository<Mileage, Long> {

    @Query("select m.content from Mileage m where m.postUUID= :postUUID")
    Boolean getContent(@Param("postUUID") String postUUID);

    @Query("select m.photo from Mileage m where m.postUUID= :postUUID")
    Boolean getPhoto(@Param("postUUID") String postUUID);

    @Query("select m.firstReview from Mileage m where m.postUUID= :postUUID")
    Boolean getFirstReview(@Param("postUUID") String postUUID);
}
