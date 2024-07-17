package com.medhead.users_ms.Services;

import com.medhead.users_ms.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(User user);

    Optional<User> findById(Long id);

    Optional<User> findByPseudo(String pseudo);

    List<User> findAll();
}
