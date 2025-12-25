package com.krishna.ecomproductservice.controllers;

import com.krishna.ecomproductservice.dtos.ProductDto;
import com.krishna.ecomproductservice.models.Category;
import com.krishna.ecomproductservice.models.Product;
import com.krishna.ecomproductservice.services.IProductService;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final IProductService productService;

    public ProductController(@Qualifier("fakeStoreDB") IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable long productId) {
        return productService.getSingleProduct(productId);
    }

    @GetMapping()
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    /*
    create update , create / save  and delete endpoints as well
     */

    @PostMapping()
    public Product addProduct(@RequestBody ProductDto productDto) {
        Product product = Product.from(productDto);
        return productService.createProduct(product , productDto.getCategory());
    }

    @PutMapping("/{productId}")
    public Product updateProduct(@PathVariable("productId") long productId , @RequestBody ProductDto productDto) {
        Product product = Product.from(productDto);
        return productService.replaceProduct(productId, product);
    }

    @DeleteMapping
    public Product deleteProduct(@PathVariable long productId) {
        return productService.deleteProduct(productId);
    }

}
