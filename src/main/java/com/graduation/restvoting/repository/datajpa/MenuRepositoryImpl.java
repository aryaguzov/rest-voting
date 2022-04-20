package com.graduation.restvoting.repository.datajpa;

import com.graduation.restvoting.model.Menu;
import com.graduation.restvoting.repository.MenuRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of {@link com.graduation.restvoting.repository.MenuRepository} interface.
 */
@Repository
public class MenuRepositoryImpl implements MenuRepository {

    private final CrudMenuRepository crudMenuRepository;

    public MenuRepositoryImpl(CrudMenuRepository crudMenuRepository) {
        this.crudMenuRepository = crudMenuRepository;
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
}
