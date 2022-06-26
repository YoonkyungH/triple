package com.triple.controller;

import com.triple.domain.Place;
import com.triple.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @PostMapping("/place/new")
    public void create(@RequestBody PlaceForm placeForm) {
        Place place = new Place();

        place.setName(placeForm.getName());
        place.setPlaceUUID(UUID.randomUUID().toString());

        placeService.savePlace(place);
    }
}
