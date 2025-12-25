package com.krishna.ecomproductservice.services;

import com.krishna.ecomproductservice.dtos.ProductDto;
import com.krishna.ecomproductservice.exceptions.ProductNotFound;
import com.krishna.ecomproductservice.models.Category;
import com.krishna.ecomproductservice.models.Product;
import com.krishna.ecomproductservice.repositories.ICategoryRepository;
import com.krishna.ecomproductservice.repositories.IProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("mySQLProductDB")
public class ProductService implements IProductService {

    private IProductRepository productRepository;
    private ICategoryRepository categoryRepository;

    public ProductService(IProductRepository productRepository, ICategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getSingleProduct(Long productId) {
        Optional<Product> optionalProduct =  productRepository.findById(productId);
        Product product = productRepository.findById(productId).orElseThrow( () -> new ProductNotFound("Product not found") );
        return product;
    }

    @Override
    public Product createProduct(Product product ,  String categoryName) {
        Optional<Category> optionalCategory = categoryRepository.findByCategoryName(categoryName);
        // save category first then product
        Category category ;
        if(optionalCategory.isPresent()) {
            // no need to create a new category
            category = optionalCategory.get();
        }
        else{
            category = new Category();
            category.setCategoryName(categoryName);
            category = categoryRepository.save(category);
            product.setCategory(category);
        }
        return productRepository.save(product);
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        Optional<Product> optionalProduct =  productRepository.findById(productId);
        Product oldProduct = productRepository.findById(productId).orElseThrow( () -> new ProductNotFound("Product not found") );

        oldProduct.setProductName(product.getProductName());
        oldProduct.setDescription(product.getDescription());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setImage(product.getImage());
        oldProduct.setCategory(product.getCategory());

        return productRepository.save(oldProduct);
    }

    @Override
    public Product deleteProduct(Long productId) {

        Optional<Product> optionalProduct =  productRepository.findById(productId);
        Product product = productRepository.findById(productId).orElseThrow( () -> new ProductNotFound("Product not found") );
        if(optionalProduct.isPresent()) {
            productRepository.delete(optionalProduct.get());
            return product;
        }
        else throw new ProductNotFound("Product not found");
    }
}
