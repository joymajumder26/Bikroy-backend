package com.example.Bikroy.service.impl;



import com.example.Bikroy.dto.request.DistrictAddressRequest;
import com.example.Bikroy.dto.response.AreaAddressResponse;
import com.example.Bikroy.dto.response.DistrictAddressResponse;
import com.example.Bikroy.dto.response.DivisionAddressResponse;
import com.example.Bikroy.model.AreaAddressModel;
import com.example.Bikroy.model.DistrictAddressModel;
import com.example.Bikroy.model.DivisionAddressModel;
import com.example.Bikroy.repository.AreaAddressRepository;
import com.example.Bikroy.repository.DistrictAddressModelRepository;
import com.example.Bikroy.repository.DivisionAddressModelRepository;
import com.example.Bikroy.service.DistrictAddressService;
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
public class DistrictAddressImpl implements DistrictAddressService {
    private final DistrictAddressModelRepository districtAddressModelRepository;
    private final DivisionAddressModelRepository divisionAddressModelRepository;
    private final AreaAddressRepository areaAddressRepository;

    @Override
    public DistrictAddressResponse createDistrict(DistrictAddressRequest districtAddressRequest){
        Optional<DivisionAddressModel> divisionAddressModelOptional=
                divisionAddressModelRepository.findByUuid(districtAddressRequest.getDivisionUuid());
        Optional<AreaAddressModel>areaAddressModelOptional =
                areaAddressRepository.findByUuid(districtAddressRequest.getAreaUuid());

        DivisionAddressModel divisionAddressModel= null;
       AreaAddressModel areaAddressModel = null;

        if (divisionAddressModelOptional.isPresent()&&areaAddressModelOptional.isPresent()) {
            divisionAddressModel = divisionAddressModelOptional.get();
            areaAddressModel = areaAddressModelOptional.get();

            DistrictAddressModel districtAddressModel = new DistrictAddressModel(districtAddressRequest.getDistrictName(),
                    divisionAddressModel,areaAddressModel);
            districtAddressModel.setCreatedBy("Admin");
            districtAddressModel.setCreatedOn(LocalDateTime.now());
            districtAddressModel.setUuid(UUID.randomUUID().toString());

            districtAddressModel = districtAddressModelRepository.save(districtAddressModel);

            divisionAddressModel = districtAddressModel.getDivisionAddressModel();
            areaAddressModel = districtAddressModel.getAreaAddressModel();

            DivisionAddressResponse divisionAddressResponse =
                    new DivisionAddressResponse(divisionAddressModel.getUuid(), divisionAddressModel.getDivisionName());
            AreaAddressResponse areaAddressResponse =
                    new AreaAddressResponse(areaAddressModel.getUuid(), areaAddressModel.getAreaName());





            DistrictAddressResponse districtAddressResponse=
                    new DistrictAddressResponse(districtAddressModel.getUuid(), districtAddressModel.getDistrictName(),
                            divisionAddressResponse,areaAddressResponse);

            return districtAddressResponse;
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"District not found");
    }

    @Override
    public List<DistrictAddressResponse> getDistrictList(){
        List<DistrictAddressModel> districtAddressModels = districtAddressModelRepository.findAll();

        List<DistrictAddressResponse> districtAddressResponses = new ArrayList<>();

        for (DistrictAddressModel districtAddressModel : districtAddressModels) {
            DivisionAddressModel divisionAddressModel = districtAddressModel.getDivisionAddressModel();
            AreaAddressModel areaAddressModel = districtAddressModel.getAreaAddressModel();

            DivisionAddressResponse divisionAddressResponse =
                    new DivisionAddressResponse(divisionAddressModel.getUuid(), divisionAddressModel.getDivisionName());
            AreaAddressResponse areaAddressResponse =
                    new AreaAddressResponse(areaAddressModel.getUuid(), areaAddressModel.getAreaName());





            DistrictAddressResponse districtAddressResponse=
                    new DistrictAddressResponse(districtAddressModel.getUuid(), districtAddressModel.getDistrictName(),
                            divisionAddressResponse,areaAddressResponse);

            districtAddressResponses.add(districtAddressResponse);

        }
        return districtAddressResponses;

    }

    @Override
    public DistrictAddressResponse editDistrict(String uuid, DistrictAddressRequest districtAddressRequest){
        Optional<DistrictAddressModel> districtAddressModelOptional = districtAddressModelRepository.findByUuid(uuid);

        if (districtAddressModelOptional.isPresent()) {

            DistrictAddressModel districtAddressModel = districtAddressModelOptional.get();
            districtAddressModel.setDistrictName(districtAddressRequest.getDistrictName());


            districtAddressModel = districtAddressModelRepository.save(districtAddressModel);

            DivisionAddressModel divisionAddressModel = districtAddressModel.getDivisionAddressModel();
            AreaAddressModel areaAddressModel = districtAddressModel.getAreaAddressModel();

            DivisionAddressResponse divisionAddressResponse =
                    new DivisionAddressResponse(divisionAddressModel.getUuid(), divisionAddressModel.getDivisionName());
            AreaAddressResponse areaAddressResponse =
                    new AreaAddressResponse(areaAddressModel.getUuid(), areaAddressModel.getAreaName());




            return  new DistrictAddressResponse(districtAddressModel.getUuid(), districtAddressModel.getDistrictName(),
                    divisionAddressResponse,areaAddressResponse);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "District with uuid: " + uuid + " is not found.");
        }
    }

    @Override
    public boolean deleteDistrict(String uuid){
        Optional<DistrictAddressModel> districtAddressModelOptional = districtAddressModelRepository.findByUuid(uuid);

        if (districtAddressModelOptional.isPresent()) {
            DistrictAddressModel districtAddressModel = districtAddressModelOptional.get();
            districtAddressModelRepository.delete(districtAddressModel);

            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "District with uuid: " + uuid + " is not found.");
        }

    }

    }


