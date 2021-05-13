package kr.codesquad.baseball.github;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class GithubController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/githublogin")
    public String github(String code) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> oauth = restTemplate.postForObject(
                "https://github.com/login/oauth/access_token",
                new HashMap<String, Object>() {{
                    put("client_id", "bc5c1424c0266ffe37c3");
                    put("client_secret", "cfb3b1858d1b2d9da6215e3478fe21671de7ae09");
                    put("code", code);
                }},
                HashMap.class
        );

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Authorization: token "+oauth.get("access_token"));

//                oauth.get("access_token"));


        HttpEntity<Void> httpEntity = new HttpEntity<>(httpHeaders);

        Map<String, Object> result = restTemplate.exchange("https://api.github.com/user", HttpMethod.GET, httpEntity, HashMap.class).getBody();

        return "code";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
