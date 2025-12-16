package com.krishna.ecomproductservice.dtos;

import com.krishna.ecomproductservice.models.Category;
import com.krishna.ecomproductservice.models.Product;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class ProductDto {
    private String productName;
    private String description;
    private Double price;
    private String imageUrl;
    private String category;

    public static Product from (ProductDto productDto) {
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImage(productDto.getImageUrl());
        return product;
    }
}

