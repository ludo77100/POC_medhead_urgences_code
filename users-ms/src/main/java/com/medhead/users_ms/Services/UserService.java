package com.medhead.users_ms.Services;

import com.medhead.users_ms.DTO.UserDTO;
import com.medhead.users_ms.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(User user);

    Optional<User> findById(Long id);

    Optional<User> findByPseudo(String pseudo);

    List<UserDTO> findAll();

    boolean deleteUser(Long id);

    User toggleActivation(Long id);
}
