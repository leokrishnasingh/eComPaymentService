package com.krishna.ecomproductservice.services;

import com.krishna.ecomproductservice.models.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();

    Product getSingleProduct(Long productId);

    Product createProduct(Product product, String categoryName);

    Product replaceProduct(Long productId, Product product);

    Product deleteProduct(Long productId);

}
