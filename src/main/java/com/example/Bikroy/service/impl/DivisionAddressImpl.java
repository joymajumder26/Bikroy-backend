package com.example.Bikroy.service.impl;

import com.example.Bikroy.dto.request.DivisionAddressRequest;
import com.example.Bikroy.dto.response.DivisionAddressResponse;
import com.example.Bikroy.model.DivisionAddressModel;
import com.example.Bikroy.repository.DivisionAddressModelRepository;
import com.example.Bikroy.service.DivisionAddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DivisionAddressImpl implements DivisionAddressService {
    private final DivisionAddressModelRepository divisionAddressRepository;



    @Override
    public DivisionAddressResponse createDivision(DivisionAddressRequest divisionAddressRequest){
        DivisionAddressModel divisionAddressModel = new DivisionAddressModel(divisionAddressRequest.getDivisionName());
        divisionAddressModel.setCreatedBy("Admin");
        divisionAddressModel.setCreatedOn(LocalDateTime.now());
        divisionAddressModel.setUuid(UUID.randomUUID().toString());
//        districtAddressModel.setLastUpdatedBy(districtAddressRequest.getLastUpdatedBy());

        divisionAddressModel = divisionAddressRepository.save(divisionAddressModel);

        DivisionAddressResponse divisionAddressResponse =
                new DivisionAddressResponse(divisionAddressModel.getUuid(), divisionAddressModel.getDivisionName());

        return divisionAddressResponse;
    }

    @Override
    public List<DivisionAddressResponse> getDivisionList(){
        List<DivisionAddressModel> divisionAddressModels = divisionAddressRepository.findAll();

        List<DivisionAddressResponse> divisionAddressResponses = new ArrayList<>();

        for (DivisionAddressModel divisionAddressModel: divisionAddressModels) {
            DivisionAddressResponse divisionAddressResponse =
                    new DivisionAddressResponse(divisionAddressModel.getUuid(), divisionAddressModel.getDivisionName());
            divisionAddressResponses.add(divisionAddressResponse);
        }

        return divisionAddressResponses;


    }

    @Override
    public DivisionAddressResponse editDivision(String uuid, DivisionAddressRequest divisionAddressRequest){
        Optional<DivisionAddressModel> divisionAddressModelOptional = divisionAddressRepository.findByUuid(uuid);

        if(divisionAddressModelOptional.isPresent()){
            DivisionAddressModel divisionAddressModel = divisionAddressModelOptional.get();

            divisionAddressModel.setDivisionName(divisionAddressRequest.getDivisionName());

            divisionAddressModel = divisionAddressRepository.save(divisionAddressModel);

            return new DivisionAddressResponse(divisionAddressModel.getUuid(), divisionAddressModel.getDivisionName());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "division with uuid: " + uuid + " is not found.");
        }
    }

    @Override
    public  boolean deleteDivision(String uuid){
        Optional<DivisionAddressModel> divisionAddressModelOptional = divisionAddressRepository.findByUuid(uuid);

        if(divisionAddressModelOptional.isPresent()){
            DivisionAddressModel divisionAddressModel = divisionAddressModelOptional.get();
            divisionAddressRepository.delete(divisionAddressModel);

            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "division with uuid: " + uuid + " is not found.");
        }

    }

}
