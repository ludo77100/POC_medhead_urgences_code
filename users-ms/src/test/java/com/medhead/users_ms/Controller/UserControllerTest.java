package com.medhead.users_ms.Controller;

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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

        String userJson = "{ \"pseudo\": \"testuser\", \"password\": \"password\", \"email\": \"email@test.fr\", \"activated\": true }";

        // When & Then
        mockMvc.perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"userId\":1,\"pseudo\":\"testuser\",\"password\":\"encodedPassword\", \"email\": \"email@test.fr\", \"activated\": true}"));
    }
}

