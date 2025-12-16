package com.krishna.ecomproductservice.repositories;

import com.krishna.ecomproductservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
    public Optional<Category> findByCategoryName(String categoryName);
}
