package com.example.Bikroy.controller;

import com.example.Bikroy.dto.request.AreaAddressRequest;
import com.example.Bikroy.dto.response.AreaAddressResponse;
import com.example.Bikroy.service.AreaAddressService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/areas")
public class AreaAddressController {
    private AreaAddressService areaAddressService;

    @PostMapping
    public AreaAddressResponse createArea(@RequestBody AreaAddressRequest areaAddressRequest) {
        return areaAddressService.createArea(areaAddressRequest);
    }

    @GetMapping
    public List<AreaAddressResponse> getAreaList(){
        return areaAddressService.getAreaList();
    }

    @PutMapping("/{area-uuid}")
    public AreaAddressResponse editArea(@PathVariable("area-uuid") String uuid,
                                         @RequestBody AreaAddressRequest areaAddressRequest){
        return areaAddressService.editArea(uuid, areaAddressRequest);
    }

    @DeleteMapping("/{area-uuid}")
    public boolean deleteArea(@PathVariable("area-uuid") String uuid) {
        return areaAddressService.deleteArea(uuid);
    }



}
