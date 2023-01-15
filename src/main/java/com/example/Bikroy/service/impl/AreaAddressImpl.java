package com.example.Bikroy.service.impl;

import com.example.Bikroy.dto.request.AreaAddressRequest;
import com.example.Bikroy.dto.response.AreaAddressResponse;
import com.example.Bikroy.model.AreaAddressModel;
import com.example.Bikroy.repository.AreaAddressRepository;
import com.example.Bikroy.service.AreaAddressService;
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
public class AreaAddressImpl implements AreaAddressService {

 private final AreaAddressRepository areaAddressRepository;

 @Override
 public AreaAddressResponse createArea(AreaAddressRequest areaAddressRequest){
  AreaAddressModel areaAddressModel = new AreaAddressModel(areaAddressRequest.getAreaName());
  areaAddressModel.setCreatedBy("Admin");
  areaAddressModel.setCreatedOn(LocalDateTime.now());
  areaAddressModel.setUuid(UUID.randomUUID().toString());
//  areaAddressModel.setLastUpdatedBy(categoryRequest.getLastUpdatedBy());

  areaAddressModel = areaAddressRepository.save(areaAddressModel);

  AreaAddressResponse areaAddressResponse =
          new AreaAddressResponse(areaAddressModel.getUuid(), areaAddressModel.getAreaName());

  return areaAddressResponse;
 }

 @Override
 public List<AreaAddressResponse> getAreaList(){
  List<AreaAddressModel> areaAddressModels = areaAddressRepository.findAll();

  List<AreaAddressResponse> areaAddressResponses = new ArrayList<>();

  for (AreaAddressModel areaAddressModel: areaAddressModels) {
   AreaAddressResponse areaAddressResponse =
           new AreaAddressResponse(areaAddressModel.getUuid(), areaAddressModel.getAreaName());
   areaAddressResponses.add(areaAddressResponse);
  }

  return areaAddressResponses;

 }

 @Override
 public AreaAddressResponse editArea(String uuid, AreaAddressRequest areaAddressRequest){
  Optional<AreaAddressModel> areaAddressModelOptional = areaAddressRepository.findByUuid(uuid);

  if(areaAddressModelOptional.isPresent()){
   AreaAddressModel areaAddressModel= areaAddressModelOptional.get();

   areaAddressModel.setAreaName(areaAddressRequest.getAreaName());

   areaAddressModel = areaAddressRepository.save(areaAddressModel);

   return new AreaAddressResponse(areaAddressModel.getUuid(), areaAddressModel.getAreaName());

  } else {
   throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Area with uuid: " + uuid + " is not found.");
  }

 }
 @Override
  public boolean deleteArea(String uuid){
  Optional<AreaAddressModel> areaAddressModelOptional = areaAddressRepository.findByUuid(uuid);

  if(areaAddressModelOptional.isPresent()){
   AreaAddressModel areaAddressModel = areaAddressModelOptional.get();

   areaAddressRepository.delete(areaAddressModel);

   return true;
  } else {
   throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Area with uuid: " + uuid + " is not found.");
  }

 }


}
