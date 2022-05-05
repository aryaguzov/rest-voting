package com.graduation.restvoting.controller.user;

import com.graduation.restvoting.model.Restaurant;
import com.graduation.restvoting.model.User;
import com.graduation.restvoting.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import static com.graduation.restvoting.util.ValidationUtil.assureIdConsistent;
import static com.graduation.restvoting.util.ValidationUtil.checkNew;

public abstract class AbstractUserController {

    protected Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    public List<User> getAll() {
        log.info("get all users");
        return userService.getAll();
    }

    public User get(int id) {
        log.info("get a user with id={}", id);
        return userService.get(id);
    }

    public void delete(int id) {
        log.info("delete a user with id={}", id);
        userService.delete(id);
    }

    public User getByEmail(String email) {
        log.info("get a user with email={}", email);
        return userService.getByEmail(email);
    }

    public User create(User user) {
        checkNew(user);
        log.info("create a user={}", user);
        return userService.create(user);
    }

    public void update(User user, int id) {
        assureIdConsistent(user, id);
        log.info("update a user={} with id={}", user, id);
        userService.update(user);
    }
}
