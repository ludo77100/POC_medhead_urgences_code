package com.medhead.users_ms.Services.Impl;

import com.medhead.users_ms.Dao.UserRepository;
import com.medhead.users_ms.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository ;

}
