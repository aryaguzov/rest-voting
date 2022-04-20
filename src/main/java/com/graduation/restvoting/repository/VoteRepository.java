package com.graduation.restvoting.repository;

import com.graduation.restvoting.model.Vote;

import java.util.List;

/**
 * {@link VoteRepository} for handling {@link com.graduation.restvoting.model.Vote} entity.
 */
public interface VoteRepository {

    /**
     * Save a provided {@link Vote} entity.
     *
     * @param vote provided vote.
     */
    Vote save(Vote vote);

    /**
     * Get all provided {@link Vote} entities.
     */
    List<Vote> getAll();

    /**
     * Get a specific {@link Vote} entity.
     *
     * @param id provided vote id.
     */
    Vote get(int id);

    /**
     * Delete a specific {@link Vote} entity.
     *
     * @param id provided vote id.
     */
    void delete(int id);
}
