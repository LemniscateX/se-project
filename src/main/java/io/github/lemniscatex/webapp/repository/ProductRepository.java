package io.github.lemniscatex.webapp.repository;

import io.github.lemniscatex.webapp.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    @Transactional
    void deleteByIdEquals(Integer id);

    @Transactional
    Product getProductById(Integer id);
}
