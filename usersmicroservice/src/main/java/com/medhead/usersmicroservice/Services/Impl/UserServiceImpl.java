package com.medhead.usersmicroservice.Services.Impl;

import com.medhead.usersmicroservice.Entities.User;
import com.medhead.usersmicroservice.Repositories.UserRepository;
import com.medhead.usersmicroservice.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository ;

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }

}
