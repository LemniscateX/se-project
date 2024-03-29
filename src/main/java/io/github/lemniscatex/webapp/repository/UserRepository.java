package io.github.lemniscatex.webapp.repository;

import io.github.lemniscatex.webapp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


public interface UserRepository extends CrudRepository<User, Integer> {
    @Transactional
    Boolean existsByEmail(String email);

    @Transactional
    User getUserByEmail(String email);
}