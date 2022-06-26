package com.triple.service;

import com.triple.controller.MileageForm;
import com.triple.domain.Mileage;
import com.triple.repository.UserRepository;
import com.triple.repository.MileageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MileageService {

    private final MileageRepository mileageRepository;
    private final UserRepository userRepository;

    @Transactional
    public void saveMileage(MileageForm mileageForm) {
        int totalMileage = 0;

        if(mileageForm.isPhoto()) {
            totalMileage++;
        }
        if(mileageForm.isContent()) {
            totalMileage++;
        }
        if(mileageForm.isFirstReview()) {
            totalMileage++;
        }
        Mileage mileage = new Mileage(mileageForm.getWriterUUID(),
                mileageForm.getPostUUID(),
                totalMileage,
                mileageForm.isPhoto(),
                mileageForm.isContent(),
                mileageForm.isFirstReview());

        int oldMileage = userRepository.getUserMileage(mileage.getWriterUUID());
        int newMileage = oldMileage + totalMileage;

        userRepository.setUserMileage(newMileage, mileageForm.getWriterUUID());
        mileageRepository.save(mileage);
    }

    @Transactional
    public void updateMileage(MileageForm mileageForm) {
        int totalMileage = 0;

        if(mileageRepository.getFirstReview(mileageForm.getPostUUID())) {
            mileageForm.setFirstReview(true);
        } else  {
            mileageForm.setFirstReview(false);
        }

        if(!mileageRepository.getPhoto(mileageForm.getPostUUID())) {
            if(mileageForm.isPhoto()) {
                totalMileage++;
            }
        } else {
            if(!mileageForm.isPhoto()) {
                totalMileage--;
            }
        }

        if(!mileageRepository.getContent(mileageForm.getPostUUID())) {
            if(mileageForm.isContent()) {
                totalMileage++;
            }
        } else {
            if(!mileageForm.isContent()) {
                totalMileage--;
            }
        }

        Mileage mileage = new Mileage(mileageForm.getWriterUUID(),
                mileageForm.getPostUUID(),
                totalMileage,
                mileageForm.isPhoto(),
                mileageForm.isContent(),
                mileageForm.isFirstReview());

        int oldMileage = userRepository.getUserMileage(mileage.getWriterUUID());
        int newMileage = oldMileage + totalMileage;

        userRepository.setUserMileage(newMileage, mileageForm.getWriterUUID());
        mileageRepository.save(mileage);
    }

    @Transactional
    public void deleteMileage(String userUUID, String postUUID) {
        int totalMileage = 0;

        if(mileageRepository.getPhoto(postUUID)) {
            totalMileage--;
        }

        if(mileageRepository.getContent(postUUID)) {
            totalMileage--;
        }

        if(mileageRepository.getFirstReview(postUUID)) {
            totalMileage--;
        }


        Mileage mileage = new Mileage(userUUID, postUUID, totalMileage, false, false, false);

        int oldMileage = userRepository.getUserMileage(userUUID);
        int newMileage = oldMileage + totalMileage;

        userRepository.setUserMileage(newMileage, userUUID);
        mileageRepository.save(mileage);
    }
}
