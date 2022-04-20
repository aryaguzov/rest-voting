package com.graduation.restvoting.repository;

import com.graduation.restvoting.model.User;

import java.util.List;

/**
 * {@link UserRepository} for handling {@link com.graduation.restvoting.model.User} entity.
 */
public interface UserRepository {

    /**
     * Save a provided {@link User} entity.
     *
     * @param user provided user.
     */
    User save(User user);

    /**
     * Get all provided {@link User} entities.
     */
    List<User> getAll();

    /**
     * Get a specific {@link User} entity.
     *
     * @param id provided user id.
     */
    User get(int id);

    /**
     * Get a specific {@link User} entity.
     *
     * @param email provided user email.
     */
    User getByEmail(String email);

    /**
     * Delete a specific {@link User} entity.
     *
     * @param id provided user id.
     */
    void delete(int id);
}
