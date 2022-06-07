package com.example.se2project.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false)
    @Length(min = 2, max = 30)
    private String name;

    @Column(nullable = false)
    @Length(min = 2, max = 50)
    private String detail;

    @NotEmpty(message = "Image can not be empty")
    @Column(length = Integer.MAX_VALUE, nullable = false)
    private String image;

    @Column(nullable = false)
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categories_id")
    private Category category;

    public Product(Product product) {
        this.productId = product.getProductId();
        this.name = product.getName();
        this.detail = product.getDetail();
        this.image = product.getImage();
        this.price = product.getPrice();
    }

    @Transient
    public String getImagePath() {
        if(image == null || productId == 0) {
            return null;
        }
        return "/product-image/" + productId + "/" + image;
    }
}
