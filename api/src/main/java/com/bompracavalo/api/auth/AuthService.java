package com.bompracavalo.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

@Service
public class AuthService {

    @Autowired
    private RestTemplate restTemplate;

    public String authenticateUser(String code) {

        String getUserInfoURL = "https://www.googleapis.com/oauth2/v3/userinfo";

        HttpHeaders header = new HttpHeaders();
        header.set("Authorization", String.format("Bearer %s", code));

        HttpEntity<String> entity = new HttpEntity<>("", header);

        ResponseEntity<String> UserInfoResponse = restTemplate.exchange(
                getUserInfoURL,
                HttpMethod.GET,
                entity,
                String.class);

        DocumentContext documentContext = JsonPath.parse(UserInfoResponse.getBody());

        String content = documentContext.read("$.*");

        System.out.println(content);

        return content;
    }
}
