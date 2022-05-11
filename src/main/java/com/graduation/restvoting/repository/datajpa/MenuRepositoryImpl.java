package com.graduation.restvoting.repository.datajpa;

import com.graduation.restvoting.model.Menu;
import com.graduation.restvoting.model.Restaurant;
import com.graduation.restvoting.repository.MenuRepository;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of {@link com.graduation.restvoting.repository.MenuRepository} interface.
 */
@Repository
public class MenuRepositoryImpl implements MenuRepository {

    private final CrudMenuRepository crudMenuRepository;

    private final CrudRestaurantRepository crudRestaurantRepository;

    public MenuRepositoryImpl(CrudMenuRepository crudMenuRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudMenuRepository = crudMenuRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Override
    public Menu save(Menu menu) {
        return crudMenuRepository.save(menu);
    }

    @Override
    public List<Menu> getAll() {
        return crudMenuRepository.findAll();
    }

    @Override
    public Menu get(int id) {
        return crudMenuRepository.getById(id);
    }

    @Override
    public void delete(int id) {
        crudMenuRepository.deleteById(id);
    }

    @Override
    public void updateRestaurant(int id) {
        Restaurant restaurant = crudRestaurantRepository.getById(id);

        crudMenuRepository.findAll()
                .stream()
                .collect(Collectors.collectingAndThen(Collectors.toList(), collected -> {
                    Collections.shuffle(collected);
                    return collected.stream();
                }))
                .limit(3)
                .forEach(menu -> menu.setRestaurant(restaurant));
    }
}
