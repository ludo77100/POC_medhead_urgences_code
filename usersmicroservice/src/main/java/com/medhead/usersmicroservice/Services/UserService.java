package com.medhead.usersmicroservice.Services;

import com.medhead.usersmicroservice.Entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    List<User> allUsers();
}
