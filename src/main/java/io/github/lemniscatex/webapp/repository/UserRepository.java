package io.github.lemniscatex.webapp.repository;

import io.github.lemniscatex.webapp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


public interface UserRepository extends CrudRepository<User, Integer> {
    @Transactional
    void deleteByName(String name);

    @Transactional
    void deleteByIdEquals(Integer id);

    @Transactional
    void deleteByEmail(String email);

    @Transactional
    User[] getUserByName(String name);

    @Transactional
    User[] getUserByEmail(String email);
}