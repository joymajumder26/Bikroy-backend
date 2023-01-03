package com.example.Bikroy.dto.response;

import com.example.Bikroy.model.AreaAddressModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubCategoryResponse {
    private String Uuid;
    private String subCategoryName;
    private CategoryResponse categoryResponse;

}
