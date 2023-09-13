package com.bompracavalo.api.auth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bompracavalo.api.user.UserEntity;
import com.bompracavalo.api.user.UserService;
import com.bompracavalo.api.util.Util;
import com.google.gson.Gson;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    public String authenticateUser(String accessToken) {
        try {
            URL url = new URL("https://www.googleapis.com/oauth2/v3/userinfo");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("Authorization", "Bearer " + accessToken);

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
