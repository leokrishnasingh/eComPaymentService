package com.krishna.ecomproductservice.models;

import com.krishna.ecomproductservice.dtos.ProductDto;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Product extends BaseModel {
    private String productName;
    private String description;
    private Double price;
    private String image;

    @ManyToOne
    private Category category;

    public static Product from(ProductDto dto){
        Product product = new Product();
        product.setPrice(dto.getPrice());
        Category category = new Category();
        category.setCategoryName(dto.getCategory());
        product.setCategory(category);
        return product;
    }
}
