package com.skd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String order_status;

    @Column(nullable = false)
    private String order_number = String.valueOf(UUID.randomUUID());

    @Column(nullable = false, unique = true)
    private Date created_date = new Date();

    @OneToMany(mappedBy = "order")
    List<OrderItem> orderItems;

}