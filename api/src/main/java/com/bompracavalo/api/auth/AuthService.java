package com.bompracavalo.api.auth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bompracavalo.api.auth.dtos.AuthenticationResponse;
import com.bompracavalo.api.config.JwtService;
import com.bompracavalo.api.user.RoleEntity;
import com.bompracavalo.api.user.UserEntity;
import com.bompracavalo.api.user.UserService;
import com.bompracavalo.api.util.Util;
import com.google.gson.Gson;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    public AuthenticationResponse authenticateUser(String accessToken) {
        try {
            URL url = new URL("https://www.googleapis.com/oauth2/v3/userinfo");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("Authorization", "Bearer " + accessToken);

            if (connection.getResponseCode() == 200) {

                BufferedReader response = new BufferedReader(new InputStreamReader((connection.getInputStream())));

                String jsonToString = Util.convertJsonToString(response);

                Gson gson = new Gson();
                UserEntity user = gson.fromJson(jsonToString, UserEntity.class);

                user.setRole(RoleEntity.USER);

                userService.saveNewUser(user);

                Map<String, Object> infos = new HashMap<>();
                infos.put("sub", user.getSub());
                infos.put("name", user.getName());
                infos.put("image", user.getPicture());

                var jwt = jwtService.generateToken(infos, user);

                AuthenticationResponse jwtResponse = new AuthenticationResponse(jwt);

                return jwtResponse;
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
