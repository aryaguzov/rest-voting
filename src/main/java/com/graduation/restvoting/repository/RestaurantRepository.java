package com.graduation.restvoting.repository;

import com.graduation.restvoting.model.Restaurant;

import java.util.List;

/**
 * {@link RestaurantRepository} for handling {@link com.graduation.restvoting.model.Restaurant} entity.
 */
public interface RestaurantRepository {

    /**
     * Save a provided {@link Restaurant} entity.
     *
     * @param restaurant provided restaurant.
     */
    Restaurant save(Restaurant restaurant);

    /**
     * Get all provided {@link Restaurant} entities.
     */
    List<Restaurant> getAll();

    /**
     * Get a specific {@link Restaurant} entity.
     *
     * @param id provided restaurant id.
     */
    Restaurant get(int id);

    /**
     * Delete a specific {@link Restaurant} entity.
     *
     * @param id provided restaurant id.
     */
    void delete(int id);

    /**
     * Get a specific {@link Restaurant} entity with meals.
     *
     * @param id provided restaurant id.
     */
    Restaurant getWithMeals(int id);
}
