package com.graduation.restvoting.repository.datajpa;

import com.graduation.restvoting.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {
}
