package com.project.productservice.repositories;

import com.project.productservice.entities.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {

    @Query("select c from CartItemEntity c where c.email =?1")
    List<CartItemEntity> getAllCartItemsByEmail(String email);

    @Query("select c from CartItemEntity c where c.customer.id =?1")
    List<CartItemEntity> getAllCartItemsByCustomerId(long customerId);
}
