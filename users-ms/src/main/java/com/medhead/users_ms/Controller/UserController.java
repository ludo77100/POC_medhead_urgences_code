package com.medhead.users_ms.Controller;


import com.medhead.users_ms.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService ;
}
