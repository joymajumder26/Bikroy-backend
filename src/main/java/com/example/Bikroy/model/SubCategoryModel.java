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
@Table(name = "SUB_CATEGORY")
public class SubCategoryModel extends BaseModel {

    private String subCategoryName;

    @ManyToOne
    private ProductModel productModel;

    @ManyToOne
    private CategoryModel categoryModel;

}
