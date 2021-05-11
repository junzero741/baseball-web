package kr.codesquad.baseball.utils;

import kr.codesquad.baseball.user.User;
import kr.codesquad.baseball.user.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SampleDataRunner {
    @Bean
    public ApplicationRunner saveUser(UserRepository userRepository) {
        return args -> userRepository.save(new User("test"));
    }
}
