package com.graduation.restvoting.service;

import com.graduation.restvoting.model.User;
import com.graduation.restvoting.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.graduation.restvoting.util.ValidationUtil.*;

/**
 * {@link UserService} for {@link com.graduation.restvoting.repository.UserRepository}.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User create(User user) {
        Objects.requireNonNull(user, "User must not be null.");
        return userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public User get(int id) {
        return checkNotFoundWithId(userRepository.get(id), id);
    }

    public User getByEmail(String email) {
        Objects.requireNonNull(email, "Email must not be null.");
        return checkNotFound(userRepository.getByEmail(email), "email=" + email);
    }

    @Transactional
    public void delete(int id) {
        checkNotFoundWithId(userRepository.get(id), id);
        userRepository.delete(id);
    }

    @Transactional
    public void update(User user) {
        Objects.requireNonNull(user, "User must not be null.");
        checkNotFoundWithId(userRepository.save(user), user.getId());
    }
}
