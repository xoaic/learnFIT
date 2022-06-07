package com.example.se2project.entity.dto;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    public String name;
    public String detail;
    public MultipartFile image;
    public double price;
    public Long categoryId;
}
