package com.example.se2project.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Builder
@Table(name = "cart_product")
public class CartProduct {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Product product = productrepo.findById(product_id);
     * C cart_id = userRepo.findById(user_1).getCart();
     * Cart cart = userRepo.findById(user.getCart())
     * CartProduct cartProduct = new CartProduct(1, cart, product, 5)
     */
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
//
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    private int quantity;

    public CartProduct(Product product, User user, int quantity) {
        this.product = product;
        this.user = user;
        this.quantity = quantity;
    }

//    public CartProduct(Product product, User user, int i) {
//    }

//    @Column(nullable = true)
//    private double price;
//    private Long productId;
//    public double getTotalPrice() {
//        return this.product.getPrice() * this.quantity;
//    }
}
