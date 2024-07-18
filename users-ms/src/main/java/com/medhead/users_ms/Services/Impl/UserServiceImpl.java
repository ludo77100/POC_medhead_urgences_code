package com.medhead.users_ms.Services.Impl;

import com.medhead.users_ms.Dao.UserRepository;
import com.medhead.users_ms.Services.UserService;
import com.medhead.users_ms.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository ;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Enregistre un utilisateur en encodant son mot de passe.
     *
     * Cette méthode prend un mot de passe en clair, l'encode en utilisant
     * l'implémentation fournie de {@link PasswordEncoder}, et retourne
     * le mot de passe encodé.
     *
     * @param plainPassword le mot de passe en clair de l'utilisateur
     * @return le mot de passe encodé
     */
    public String registerUser(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }

    /**
     * Vérifie si un mot de passe en clair correspond à un mot de passe haché.
     *
     * Cette méthode compare un mot de passe en clair avec un mot de passe haché
     * en utilisant l'implémentation fournie de {@link PasswordEncoder}.
     *
     * @param plainPassword le mot de passe en clair à vérifier
     * @param hashedPassword le mot de passe haché contre lequel vérifier
     * @return {@code true} si le mot de passe en clair correspond au mot de passe haché, {@code false} sinon
     */
    public boolean checkPassword(String plainPassword, String hashedPassword) {
        return passwordEncoder.matches(plainPassword, hashedPassword);
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(registerUser(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByPseudo(String pseudo) {
        return Optional.ofNullable(userRepository.findByPseudo(pseudo));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
