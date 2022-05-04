package com.graduation.restvoting.service;

import com.graduation.restvoting.model.Role;
import com.graduation.restvoting.model.User;
import com.graduation.restvoting.repository.UserRepository;
import com.graduation.restvoting.util.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@DisplayName("Unit-level testing for UserService")
class UserServiceTest extends AbstractServiceTest {

    @Mock
    public UserRepository userRepository;

    @InjectMocks
    public UserService userService;

    private User actual;

    @BeforeEach
    void setup() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository);
        actual = new User(1, "Admin", "admin@gmail.com", "admin", true,
                Collections.singleton(Role.ADMIN));
    }

    @Test
    void create() {
        final User user = new User(null, "Guest", "guest@gmail.com", "guest", true,
                Collections.singleton(Role.USER));

        lenient().when(userRepository.getByEmail(user.getEmail())).thenThrow(NotFoundException.class);
        given(userRepository.save(user)).willAnswer(invocation -> invocation.getArgument(0));

        final User savedUser = userService.create(user);

        assertEquals(user, savedUser);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void getAll() {
        final List<User> users = new ArrayList<>();
        users.add(actual);

        when(userRepository.getAll()).thenReturn(users);
        final List<User> allUsers = userService.getAll();

        assertEquals(users, allUsers);
        verify(userRepository, times(1)).getAll();
    }

    @Test
    void get() {
        final int id = actual.getId();

        when(userRepository.get(id)).thenReturn(actual);
        final User expected = userService.get(id);

        assertEquals(expected, actual);
    }

    @Test
    void getByEmail() {
        final String email = actual.getEmail();

        when(userRepository.getByEmail(email)).thenReturn(actual);
        final User expected = userService.getByEmail(email);

        assertEquals(expected, actual);
    }

    @Test
    void delete() {
        final int id = actual.getId();
        userRepository.delete(id);

        assertThrows(NotFoundException.class, () -> userService.get(id));
    }

    @Test
    void update() {
        actual.setName("Updated Name");
        actual.setEmail("updated@gmail.com");
        actual.setPassword("updated");

        given(userRepository.save(actual)).willAnswer(invocation -> invocation.getArgument(0));
        userService.update(actual);

        assertEquals("Updated Name", actual.getName());
        assertEquals("updated@gmail.com", actual.getEmail());
    }
}