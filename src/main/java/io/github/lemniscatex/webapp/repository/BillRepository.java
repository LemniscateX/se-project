package io.github.lemniscatex.webapp.repository;

import io.github.lemniscatex.webapp.model.Bill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface BillRepository extends CrudRepository<Bill, Integer> {
    @Transactional
    Bill getBillById(Integer id);

    @Transactional
    Bill[] findAllByUserId(Integer userId);
}