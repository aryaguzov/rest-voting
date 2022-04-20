package com.graduation.restvoting.repository.datajpa;

import com.graduation.restvoting.model.Restaurant;
import com.graduation.restvoting.repository.RestaurantRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of {@link com.graduation.restvoting.repository.RestaurantRepository} interface.
 */
@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private final CrudRestaurantRepository crudRestaurantRepository;

    public RestaurantRepositoryImpl(CrudRestaurantRepository crudRestaurantRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return crudRestaurantRepository.save(restaurant);
    }

    @Override
    public List<Restaurant> getAll() {
        return crudRestaurantRepository.findAll(Sort.by("name").ascending());
    }

    @Override
    public Restaurant get(int id) {
        return crudRestaurantRepository.getById(id);
    }

    @Override
    public void delete(int id) {
        crudRestaurantRepository.deleteById(id);
    }
}
