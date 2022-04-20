package com.graduation.restvoting.repository.datajpa;

import com.graduation.restvoting.model.User;
import com.graduation.restvoting.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of {@link com.graduation.restvoting.repository.UserRepository} interface.
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final CrudUserRepository crudUserRepository;

    public UserRepositoryImpl(CrudUserRepository crudUserRepository) {
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    public User save(User user) {
        return crudUserRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return crudUserRepository.findAll(Sort.by("name", "email").ascending());
    }

    @Override
    public User get(int id) {
        return crudUserRepository.getById(id);
    }

    @Override
    public User getByEmail(String email) {
        return crudUserRepository.findByEmail(email);
    }

    @Override
    public void delete(int id) {
        crudUserRepository.deleteById(id);
    }
}
