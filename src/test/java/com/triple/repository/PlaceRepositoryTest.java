package com.triple.repository;

import com.triple.domain.Place;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
public class PlaceRepositoryTest {

    @Autowired
    PlaceRepository placeRepository;

    @Test
    public void 장소등록() {
        Place place = new Place("placeA");

        Place savePlace = placeRepository.save(place);
        Place findPlace = placeRepository.findById(savePlace.getId()).get();

        assertThat(findPlace.getId()).isEqualTo(place.getId());
        assertThat(findPlace.getPlaceUUID()).isEqualTo(place.getPlaceUUID());
        assertThat(findPlace.getName()).isEqualTo(place.getName());

        assertThat(findPlace).isEqualTo(place);
    }
}
