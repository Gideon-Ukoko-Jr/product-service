package com.project.productservice.repositories;

import com.project.productservice.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("select p from ProductEntity p order by p.dateTimeModified asc")
    List<ProductEntity> getAllProductsSortedByDate();
}
