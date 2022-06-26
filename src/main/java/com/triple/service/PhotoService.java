package com.triple.service;

import com.triple.controller.PostForm;
import com.triple.domain.Photo;
import com.triple.repository.PhotoRepository;
import com.triple.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final PostRepository postRepository;

    @Transactional
    public void savePhoto(Photo photo) {
        photoRepository.save(photo);
    }

    @Transactional
    public int existsPhoto(String photoUUID, String postUUID) {
        return photoRepository.existsPhoto(photoUUID, postUUID);
    }

    @Transactional
    public void setDelY(String postUUID) {
        photoRepository.setDelY(postUUID);
    }

    @Transactional
    public void setDelN(String photoUUID) {
        photoRepository.setDelN(photoUUID);
    }

    @Transactional
    public void deleteDelY(String postUUID) {
        photoRepository.deleteDelY(postUUID);
    }

    @Transactional
    public void deletePhoto(String postUUID) {
        photoRepository.deletePhoto(postUUID);
    }

}
