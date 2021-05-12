package kr.codesquad.baseball.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void findUser() {
        assertThat(userRepository.findUserById(1L).getName())
                .isEqualTo("test");
    }

    @Test
    void saveUser() {
        User user = new User("test");
        userRepository.save(user);
    }
}
