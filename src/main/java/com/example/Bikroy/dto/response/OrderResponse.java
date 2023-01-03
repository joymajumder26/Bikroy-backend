package com.example.Bikroy.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private String Uuid;
    private String orderName;
    private SubCategoryResponse subCategoryResponse;
    private DistrictAddressResponse districtAddressResponse;
}
