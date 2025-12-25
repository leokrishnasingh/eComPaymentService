package com.krishna.ecomproductservice.services;

import com.krishna.ecomproductservice.dtos.ProductDto;
import com.krishna.ecomproductservice.models.Category;
import com.krishna.ecomproductservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreDB")
public class FakeStoreProductService implements IProductService {

    private static final String CACHE_NAME = "products";
    private final RestTemplate restTemplate;
    //private final RedisTemplate<String, Object> redisTemplate;
    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate ) {
        this.restTemplate = restTemplate;
        //this.redisTemplate = redisTemplate;
    }

    @Override
    public List<Product> getAllProducts() {
        ResponseEntity<List<ProductDto>> response = restTemplate.exchange(
                "https://fakestoreapi.com/products/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductDto>>() {}
        );
        List<Product> products = new ArrayList<>();
        for(ProductDto pdto : response.getBody()){
            Product product = Product.from(pdto);
            products.add(product);
        }
        return products;
    }


    @Override
    @Cacheable(value = CACHE_NAME , key = "#productId")
    public Product getSingleProduct(Long productId) {

        // if the productId is present in cache then return else
        // fetch it from the system and save it in redis cache before returning

//        if(redisTemplate.opsForHash().get("PRODUCTS", "productId_"+productId) != null){
//            return (Product) redisTemplate.opsForHash().get("PRODUCTS", "productId_"+productId);
//        }
        ResponseEntity<ProductDto> responseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products/" + productId, ProductDto.class);
        Product product = Product.from(responseEntity.getBody());

        //redisTemplate.opsForHash().put("PRODUCTS", "productId_"+productId, product);
        return product;
    }

    @Override
    @CachePut(value = CACHE_NAME, key = "#product.id")
    public Product createProduct(Product product, String categoryName) {
        return null;
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        return null;
    }

    @Override
    @CacheEvict(value = CACHE_NAME, key = "#productId")
    public Product deleteProduct(Long productId) {
        return null;
    }

}
