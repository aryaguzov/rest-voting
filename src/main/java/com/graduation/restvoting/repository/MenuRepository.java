package com.graduation.restvoting.repository;

import com.graduation.restvoting.model.Menu;

import java.util.List;

/**
 * {@link MenuRepository} for handling {@link com.graduation.restvoting.model.Menu} entity.
 */
public interface MenuRepository {

    /**
     * Save a provided {@link Menu} entity.
     *
     * @param menu provided menu.
     */
    Menu save(Menu menu);

    /**
     * Get all provided {@link Menu} entities.
     */
    List<Menu> getAll();

    /**
     * Get a specific {@link Menu} entity.
     *
     * @param id provided menu id.
     */
    Menu get(int id);

    /**
     * Delete a specific {@link Menu} entity.
     *
     * @param id provided menu id.
     */
    void delete(int id);

    /**
     * Update a specific {@link Menu} entity.
     *
     * @param id provided menu id.
     */
    void updateRestaurant(int id);
}
