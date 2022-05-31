package com.project.productservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "address")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    private String zipcode;

    @Column(nullable = false)
    private String country;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
}
