package com.medhead.users_ms.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medhead.users_ms.Services.UserService;
import com.medhead.users_ms.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc ;

    @MockBean
    private UserService userService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void whenRegisterUser_thenReturnsCreatedUser() throws Exception {
        // Given
        User user = new User();
        user.setPseudo("testuser");
        user.setPassword("password");

        User savedUser = new User();
        savedUser.setUserId(1L);
        savedUser.setPseudo("testuser");
        savedUser.setPassword("encodedPassword");
        savedUser.setEmail("email@test.fr");
        savedUser.setActivated(true);

        when(userService.saveUser(any(User.class))).thenReturn(savedUser);
        when(passwordEncoder.encode(any(String.class))).thenReturn("encodedPassword");

        String userJson = "{ \"pseudo\": \"testuser\", " +
                "\"password\": \"password\", " +
                "\"email\": \"email@test.fr\", " +
                "\"activated\": true }";

        // When & Then
        mockMvc.perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"userId\":1," +
                        "\"pseudo\":\"testuser\"," +
                        "\"password\":\"encodedPassword\"," +
                        "\"email\": \"email@test.fr\", " +
                        "\"activated\": true}"));
    }

    @Test
    public void whenGetUserById_thenReturnsUser() throws Exception {
        // Given
        Long userId = 1L;
        User user = new User();
        user.setUserId(userId);
        user.setPseudo("testuser");
        user.setPassword("encodedPassword");
        user.setEmail("email@test.fr");
        user.setActivated(true);

        when(userService.findById(userId)).thenReturn(Optional.of(user));

        // When & Then
        mockMvc.perform(get("/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"userId\":1," +
                        "\"pseudo\":\"testuser\"," +
                        "\"password\":\"encodedPassword\", " +
                        "\"email\": \"email@test.fr\", " +
                        "\"activated\": true}"));
    }

    @Test
    public void whenGetUserByPseudo_thenReturnsUser() throws Exception {
        // Given
        String pseudo = "testuser";
        User user = new User();
        user.setUserId(1L);
        user.setPseudo(pseudo);
        user.setPassword("encodedPassword");
        user.setEmail("email@test.fr");
        user.setActivated(true);

        when(userService.findByPseudo(pseudo)).thenReturn(Optional.of(user));

        // When & Then
        mockMvc.perform(get("/users/pseudo/{pseudo}", pseudo)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"userId\":1," +
                        "\"pseudo\":\"testuser\"," +
                        "\"password\":\"encodedPassword\"," +
                        "\"email\": \"email@test.fr\"," +
                        "\"activated\": true}"));
    }

    @Test
    public void whenGetUsersList_thenReturnsAllUsers() throws Exception {
        User user1 = new User();
        user1.setUserId(1L);
        user1.setPseudo("testuser1");
        user1.setPassword("encodedPassword1");
        user1.setEmail("email1@test.fr");
        user1.setActivated(true);

        User user2 = new User();
        user2.setUserId(2L);
        user2.setPseudo("testuser2");
        user2.setPassword("encodedPassword2");
        user2.setEmail("email2@test.fr");
        user2.setActivated(true);

        List<User> users = Arrays.asList(user1, user2);

        when(userService.findAll()).thenReturn(users);

        // When & Then
        mockMvc.perform(get("/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(users)));
    }

}

