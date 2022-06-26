package com.triple.repository;

import com.triple.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select m.userUUID from User m where m.name= :name")
    String findUserUUID(@Param("name") String name);

    @Query("select m.mileage from User m where m.userUUID= :userUUID")
    int getUserMileage(@Param("userUUID") String userUUID);

    @Modifying
    @Query("UPDATE User set mileage= :mileage where userUUID= :userUUID")
    int setUserMileage(@Param("mileage") int mileage, @Param("userUUID") String userUUID);
}
