package com.triple.service;

import com.triple.domain.User;
import com.triple.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void create(User user) {
        userRepository.save(user);
    }

    @Transactional
    public int getMileage(String userUUID) {
        return userRepository.getUserMileage(userUUID);
    }

}
