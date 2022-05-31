package com.project.productservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "cart_item")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    private String email;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    @Column(nullable = false)
    private LocalDateTime dateTimeCreated;

    @Column(nullable = false)
    private LocalDateTime dateTimeModified;

    @Transient
    public BigDecimal getSubTotal(){
        return this.product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

//    @ManyToOne
//    @JoinColumn(nullable = false, name = "cart_id")
//    private CartEntity cart;
}
