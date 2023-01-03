package com.example.Bikroy.service;

import com.example.Bikroy.dto.request.DivisionAddressRequest;
import com.example.Bikroy.dto.response.DivisionAddressResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DivisionAddressService {
    DivisionAddressResponse createDivision(DivisionAddressRequest divisionAddressRequest);

    List<DivisionAddressResponse> getDivisionList();

    DivisionAddressResponse editDivision(String uuid, DivisionAddressRequest divisionAddressRequest);

    boolean deleteDivision(String uuid);
}
