package com.example.Bikroy.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT")
public class ProductModel extends BaseModel {
    private String productName;
    private Double price;
    private String condition;
    private String brand;
    private String modelName;
    private String features;
    private String description;
}
