package com.project.productservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "product")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int availableQuantity;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String merchantName;

    @Column(nullable = false)
    private LocalDateTime dateTimeCreated;

    @Column(nullable = false)
    private LocalDateTime dateTimeModified;

}
