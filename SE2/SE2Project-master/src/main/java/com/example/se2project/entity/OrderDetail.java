package com.example.se2project.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderDetail {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false,
    foreignKey = @ForeignKey(name = "orderDetail_Order"))
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false,
    foreignKey = @ForeignKey(name = "orderDetail_Product"))
    private Product product;

    private int quantity;
    private double price;
}
