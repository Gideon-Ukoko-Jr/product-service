package com.project.productservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "customer")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, unique = true)
    private String uniqueUserId;

    private String address;

    @Column(nullable = false)
    private LocalDateTime createdTime;

//    @JsonIgnore
//    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
//    private List<OrderEntity> orders;
}
