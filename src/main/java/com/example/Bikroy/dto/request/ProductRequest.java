package com.example.Bikroy.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String productName;
    private Double price;
    private String condition;
    private String brand;
    private String modelName;
    private String features;
    private String description;

    private String createBy;
    private LocalDateTime createdOn;
    private String lastUpdatedBy;
}
