package com.example.Bikroy.controller;

import com.example.Bikroy.dto.request.DivisionAddressRequest;
import com.example.Bikroy.dto.response.DivisionAddressResponse;
import com.example.Bikroy.service.DivisionAddressService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/divisions")
public class DivisionAddressController {
    private DivisionAddressService divisionAddressService;

    @PostMapping
    public DivisionAddressResponse createDivision(@RequestBody DivisionAddressRequest divisionAddressRequest) {
        return divisionAddressService.createDivision(divisionAddressRequest);
    }

    @GetMapping
    public List<DivisionAddressResponse> getDivisionList(){
        return divisionAddressService.getDivisionList();
    }

    @PutMapping("/{division-uuid}")
    public DivisionAddressResponse editDivision(@PathVariable("division-uuid") String uuid,
                                                @RequestBody DivisionAddressRequest divisionAddressRequest){
        return divisionAddressService.editDivision(uuid, divisionAddressRequest);
    }

    @DeleteMapping("/{division-uuid}")
    public boolean deleteDivision(@PathVariable("division-uuid") String uuid) {
        return divisionAddressService.deleteDivision(uuid);
    }
}
