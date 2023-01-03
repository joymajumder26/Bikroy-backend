package com.example.Bikroy.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private String orderName;
    private String subCategoryUuid;
    private String districtUuid;
}
