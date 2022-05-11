package com.graduation.restvoting.service;

import com.graduation.restvoting.model.Menu;
import com.graduation.restvoting.repository.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.graduation.restvoting.util.ValidationUtil.*;

/**
 * {@link MenuService} for {@link com.graduation.restvoting.repository.MenuRepository}.
 */
@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Transactional
    public Menu create(Menu menu) {
        Objects.requireNonNull(menu, "Menu must not be null.");
        return menuRepository.save(menu);
    }

    public List<Menu> getAll() {
        return menuRepository.getAll();
    }

    public Menu get(int id) {
        return checkNotFoundWithId(menuRepository.get(id), id);
    }

    @Transactional
    public void delete(int id) {
        checkNotFoundWithId(menuRepository.get(id), id);
        menuRepository.delete(id);
    }

    @Transactional
    public void update(Menu menu) {
        Objects.requireNonNull(menu, "Menu must not be null.");
        checkNotFoundWithId(menuRepository.save(menu), menu.getId());
    }

    @Transactional
    public void updateRestaurant(int id) {
        menuRepository.updateRestaurant(id);
    }
}
