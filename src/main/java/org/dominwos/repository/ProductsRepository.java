package org.dominwos.repository;

import java.util.List;

import org.dominwos.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p  WHERE p.id IN (:ids) AND p.available=TRUE")
    List<Product> getAvailableProductsForIds(@Param("ids") List<Long> ids);

    @Query("SELECT p FROM Product p  WHERE p.available=TRUE")
    List<Product> getAllAvailableProducts();
}
