package com.bompracavalo.api.auth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bompracavalo.api.user.UserEntity;
import com.bompracavalo.api.user.UserService;
import com.bompracavalo.api.util.Util;
import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;

@Service
public class AuthService {

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private UserService userService;

    public String authenticateUser(String accessToken) {
        try {
            URL url = new URL("https://www.googleapis.com/oauth2/v3/userinfo");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("Authorization", "Bearer " + accessToken);

            // HttpHeaders headers = new HttpHeaders();
            // headers.set("Authorization", "Bearer " + accessToken);

            // HttpEntity<String> entity = new HttpEntity<>(headers);

            // ResponseEntity<String> userInfoResponse = restTemplate.exchange(
            // getUserInfoURL,
            // HttpMethod.GET,
            // entity,
            // String.class);

            if (connection.getResponseCode() == 200) {

                BufferedReader response = new BufferedReader(new InputStreamReader((connection.getInputStream())));

                String jsonToString = Util.convertJsonToString(response);

                Gson gson = new Gson();
                UserEntity user = gson.fromJson(jsonToString, UserEntity.class);

                userService.saveNewUser(user);

                return user.toString();
            } else {
                System.out.println("Chamada != 200 OK");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
