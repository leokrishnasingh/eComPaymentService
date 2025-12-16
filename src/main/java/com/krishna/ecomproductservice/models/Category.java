package com.krishna.ecomproductservice.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Category extends BaseModel {
    private String categoryName;
}
