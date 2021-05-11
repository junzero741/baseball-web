package kr.codesquad.baseball.user;

import org.springframework.data.annotation.Id;

public class User {
    @Id
    private Long id;
    private String name;

    public User(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
