package com.example.Bikroy.service;

import com.example.Bikroy.dto.request.AreaAddressRequest;
import com.example.Bikroy.dto.response.AreaAddressResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AreaAddressService {
    AreaAddressResponse createArea(AreaAddressRequest areaAddressRequest);

    List<AreaAddressResponse> getAreaList();

    AreaAddressResponse editArea(String uuid, AreaAddressRequest areaAddressRequest);

    boolean deleteArea(String uuid);
}
