package com.example.Bikroy.service;

import com.example.Bikroy.dto.request.DistrictAddressRequest;
import com.example.Bikroy.dto.response.DistrictAddressResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DistrictAddressService {
    DistrictAddressResponse createDistrict(DistrictAddressRequest districtAddressRequest);

    List<DistrictAddressResponse> getDistrictList();

    DistrictAddressResponse editDistrict(String uuid, DistrictAddressRequest districtAddressRequest);

    boolean deleteDistrict(String uuid);
}
