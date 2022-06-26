package com.triple.repository;

import com.triple.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Query("select count(p.id) from Place p where p.name= :name")
    int existsPlace(@Param("name") String name);

}
