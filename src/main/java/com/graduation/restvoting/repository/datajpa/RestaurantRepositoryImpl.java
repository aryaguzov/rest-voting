package com.graduation.restvoting.repository.datajpa;

import com.graduation.restvoting.model.Menu;
import com.graduation.restvoting.model.Restaurant;
import com.graduation.restvoting.repository.RestaurantRepository;
import com.graduation.restvoting.util.exception.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        return crudRestaurantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("A restaurant with id=%d doesn't exist", id)));
    }

    @Override
    public void delete(int id) {
        crudRestaurantRepository.deleteById(id);
    }
}
