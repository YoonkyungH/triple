package com.triple.repository;

import com.triple.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void 유저등록() {
        User user = new User("userA");

        User savedUser = userRepository.save(user);
        User findUser = userRepository.findById(savedUser.getId()).get();

        assertThat(findUser.getId()).isEqualTo(user.getId());
        assertThat(findUser.getUserUUID()).isEqualTo(user.getUserUUID());
        assertThat(findUser.getName()).isEqualTo(user.getName());
        assertThat(findUser.getMileage()).isEqualTo(user.getMileage());

        assertThat(findUser).isEqualTo(user);
    }

}
