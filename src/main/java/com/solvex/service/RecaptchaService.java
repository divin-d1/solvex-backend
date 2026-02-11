package com.solvex.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class RecaptchaService {
    @Value("${recaptcha.secret}")
    private String secret;
    private final RestTemplate restTemplate = new RestTemplate();
    public boolean validate(String token) {

        String url = "https://www.google.com/recaptcha/api/siteverify";

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("secret", secret);
        body.add("response", token);

        ResponseEntity<Map> response =
                restTemplate.postForEntity(url, body, Map.class);

        return Boolean.TRUE.equals(response.getBody().get("success"));
    }
}