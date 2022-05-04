package com.graduation.restvoting.service;

import com.graduation.restvoting.model.Restaurant;
import com.graduation.restvoting.repository.RestaurantRepository;
import com.graduation.restvoting.util.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@DisplayName("Unit-level testing for RestaurantService")
class RestaurantServiceTest extends AbstractServiceTest {

    @Mock
    public RestaurantRepository restaurantRepository;

    @InjectMocks
    public RestaurantService restaurantService;

    private Restaurant actual;

    @BeforeEach
    void setup() {
        restaurantRepository = Mockito.mock(RestaurantRepository.class);
        restaurantService = new RestaurantService(restaurantRepository);
        actual = new Restaurant(1, "Lucky Pub");
    }

    @Test
    void create() {
        final Restaurant restaurant = new Restaurant(null, "Guest Restaurant");

        given(restaurantRepository.save(restaurant)).willAnswer(invocation -> invocation.getArgument(0));

        final Restaurant savedRestaurant = restaurantService.create(restaurant);

        assertEquals(restaurant, savedRestaurant);
        verify(restaurantRepository).save(any(Restaurant.class));

    }

    @Test
    void getAll() {
        final List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(actual);

        when(restaurantRepository.getAll()).thenReturn(restaurants);
        final List<Restaurant> allRestaurants = restaurantService.getAll();

        assertEquals(restaurants, allRestaurants);
        verify(restaurantRepository, times(1)).getAll();
    }

    @Test
    void get() {
        final int id = actual.getId();

        when(restaurantRepository.get(id)).thenReturn(actual);
        final Restaurant expected = restaurantService.get(id);

        assertEquals(expected, actual);
    }

    @Test
    void delete() {
        final int id = actual.getId();
        restaurantRepository.delete(id);

        assertThrows(NotFoundException.class, () -> restaurantService.get(id));
    }

    @Test
    void update() {
        actual.setName("Updated Name");

        given(restaurantRepository.save(actual)).willAnswer(invocation -> invocation.getArgument(0));
        restaurantService.update(actual);

        assertEquals("Updated Name", actual.getName());
    }
}