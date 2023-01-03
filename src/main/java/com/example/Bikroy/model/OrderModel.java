package com.example.Bikroy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"ORDER\"")
public class OrderModel extends BaseModel {

    private String orderName;


    @ManyToOne
    private SubCategoryModel subCategory;
    @ManyToOne
    private DistrictAddressModel districtAddressModel;
}
