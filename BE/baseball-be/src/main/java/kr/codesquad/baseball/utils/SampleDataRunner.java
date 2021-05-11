package kr.codesquad.baseball.utils;

import kr.codesquad.baseball.user.User;
import kr.codesquad.baseball.user.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SampleDataRunner {
    @Bean
    public ApplicationRunner saveUser(UserRepository userRepository) {
        return args -> userRepository.saveAll(
                Arrays.asList(
                        new User("test"),
                        new User("test2")
                )
        );
    }
}
