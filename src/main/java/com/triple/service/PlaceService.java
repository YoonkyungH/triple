package com.triple.service;

import com.triple.domain.Place;
import com.triple.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    @Transactional
    public void savePlace(Place place) {
        if(placeRepository.existsPlace(place.getName()) == 0) {
            placeRepository.save(place);
        }
    }


}
