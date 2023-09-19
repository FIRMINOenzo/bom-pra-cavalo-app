package com.bompracavalo.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveNewUser(UserEntity user) {
        if (!userRepository.findBySub(user.getSub()).isPresent()) {
            userRepository.save(user);
        }
    }

}
