package com.graduation.restvoting.service;

import com.graduation.restvoting.model.Menu;
import com.graduation.restvoting.model.Restaurant;
import com.graduation.restvoting.repository.MenuRepository;
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

@DisplayName("Unit-level testing for UserService")
class MenuServiceTest extends AbstractServiceTest {

    @Mock
    public MenuRepository menuRepository;

    @InjectMocks
    public MenuService menuService;

    private Restaurant restaurant;

    private Menu actual;

    @BeforeEach
    void setup() {
        menuRepository = Mockito.mock(MenuRepository.class);
        menuService = new MenuService(menuRepository);
        restaurant = new Restaurant(1, "Lucky Pub");
        actual = new Menu(10, "Soup", 2.55, restaurant);
    }

    @Test
    void create() {
        final Menu menu = new Menu(null, "Guest meal", 0.01, restaurant);

        given(menuRepository.save(menu)).willAnswer(invocation -> invocation.getArgument(0));

        final Menu savedMenu = menuService.create(menu);

        assertEquals(menu, savedMenu);
        verify(menuRepository).save(any(Menu.class));
    }

    @Test
    void getAll() {
        final List<Menu> menus = new ArrayList<>();
        menus.add(actual);

        when(menuRepository.getAll()).thenReturn(menus);
        final List<Menu> allMenus = menuService.getAll();

        assertEquals(menus, allMenus);
        verify(menuRepository, times(1)).getAll();
    }

    @Test
    void get() {
        final int id = actual.getId();

        when(menuRepository.get(id)).thenReturn(actual);
        final Menu expected = menuService.get(id);

        assertEquals(expected, actual);
    }

    @Test
    void delete() {
        final int id = actual.getId();
        menuRepository.delete(id);

        assertThrows(NotFoundException.class, () -> menuService.get(id));
    }

    @Test
    void update() {
        actual.setName("Updated Name");
        actual.setPrice(14.8);
        actual.setRestaurant(new Restaurant(100, "Updated Restaurant"));

        given(menuRepository.save(actual)).willAnswer(invocation -> invocation.getArgument(0));
        menuService.update(actual);

        assertEquals("Updated Name", actual.getName());
        assertEquals(14.8, actual.getPrice());
    }
}