package com.example.Bikroy.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictAddressResponse {
   private String Uuid;
   private String districtName;

   private DivisionAddressResponse divisionAddressResponse;
   private AreaAddressResponse areaAddressResponse;
}
