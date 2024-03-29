package com.example.Bikroy.controller;

import com.example.Bikroy.dto.request.DistrictAddressRequest;
import com.example.Bikroy.dto.response.DistrictAddressResponse;
import com.example.Bikroy.service.DistrictAddressService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/districts")
public class DistrictAddressController {

    private DistrictAddressService districtAddressService;

    @PostMapping
    public DistrictAddressResponse createDistrict(@RequestBody DistrictAddressRequest districtAddressRequest) {
        return districtAddressService.createDistrict(districtAddressRequest);
    }

    @GetMapping
    public List<DistrictAddressResponse> getDistrictList(){
        return districtAddressService.getDistrictList();
    }

    @PutMapping("/{district-uuid}")
    public DistrictAddressResponse editDistrict(@PathVariable("district-uuid") String uuid,
                                                @RequestBody DistrictAddressRequest districtAddressRequest){
        return districtAddressService.editDistrict(uuid, districtAddressRequest);
    }

    @DeleteMapping("/{district-uuid}")
    public boolean deleteDistrict(@PathVariable("district-uuid") String uuid) {
        return districtAddressService.deleteDistrict(uuid);
    }
}
