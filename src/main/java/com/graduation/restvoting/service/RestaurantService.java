package com.graduation.restvoting.service;

import com.graduation.restvoting.model.Restaurant;
import com.graduation.restvoting.repository.MenuRepository;
import com.graduation.restvoting.repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.graduation.restvoting.util.ValidationUtil.*;

/**
 * {@link RestaurantService} for {@link com.graduation.restvoting.repository.RestaurantRepository}.
 */
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    private final MenuRepository menuRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, MenuRepository menuRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuRepository = menuRepository;
    }

    @Transactional
    public Restaurant create(Restaurant restaurant) {
        Objects.requireNonNull(restaurant, "Restaurant must not be null.");
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getAll() {
        return restaurantRepository.getAll();
    }

    public Restaurant get(int id) {
        return checkNotFoundWithId(restaurantRepository.get(id), id);
    }

    @Transactional
    public void delete(int id) {
        checkNotFoundWithId(restaurantRepository.get(id), id);
        restaurantRepository.delete(id);
    }

    @Transactional
    public void update(Restaurant restaurant) {
        Objects.requireNonNull(restaurant, "Restaurant must not be null.");
        checkNotFoundWithId(restaurantRepository.save(restaurant), restaurant.getId());
    }

    @Transactional
    public void updateMenu() {
        getAll().forEach(restaurant -> menuRepository.updateRestaurant(restaurant.getId()));
    }
}
