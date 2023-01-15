package com.example.Bikroy.service.impl;

import com.example.Bikroy.dto.request.OrderRequest;
import com.example.Bikroy.dto.response.*;
import com.example.Bikroy.model.*;
import com.example.Bikroy.repository.DistrictAddressModelRepository;
import com.example.Bikroy.repository.OrderModelRepository;
import com.example.Bikroy.repository.SubCategoryModelRepository;
import com.example.Bikroy.service.OrderService;
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
public class OrderImpl implements OrderService {

    private final OrderModelRepository orderModelRepository;

    private final SubCategoryModelRepository subCategoryModelRepository;

    private final DistrictAddressModelRepository districtAddressModelRepository;

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest){

        Optional<SubCategoryModel> subCategoryModelOptional= subCategoryModelRepository.findByUuid(orderRequest.getSubCategoryUuid());
        Optional<DistrictAddressModel>districtAddressModelOptional = districtAddressModelRepository.findByUuid(orderRequest.getDistrictUuid());
        SubCategoryModel subCategoryModel= null;
        DistrictAddressModel districtAddressModel = null;
        if (subCategoryModelOptional.isPresent()&&districtAddressModelOptional.isPresent()) {
            subCategoryModel = subCategoryModelOptional.get();
            districtAddressModel = districtAddressModelOptional.get();

            OrderModel orderModel = new OrderModel(orderRequest.getOrderName(),subCategoryModel,districtAddressModel);
            orderModel.setCreatedBy("Admin");
            orderModel.setCreatedOn(LocalDateTime.now());
            orderModel.setUuid(UUID.randomUUID().toString());

            orderModel = orderModelRepository.save(orderModel);

            subCategoryModel = orderModel.getSubCategory();
            districtAddressModel = orderModel.getDistrictAddressModel();
            CategoryModel categoryModel = subCategoryModel.getCategoryModel();
            ProductModel productModel = subCategoryModel.getProductModel();
            DivisionAddressModel divisionAddressModel = districtAddressModel.getDivisionAddressModel();
            AreaAddressModel areaAddressModel = districtAddressModel.getAreaAddressModel();

            CategoryResponse categoryResponse =
                    new CategoryResponse(categoryModel.getUuid(), categoryModel.getCategoryName());
            ProductResponse productResponse =
                    new ProductResponse(productModel.getUuid(), productModel.getProductName(),productModel.getPrice(),productModel.getCondition(),
                            productModel.getBrand(),productModel.getModelName(),productModel.getFeatures(),productModel.getDescription(),
                            productModel.getCreatedBy(),productModel.getCreatedOn(),productModel.getLastUpdatedBy());

            SubCategoryResponse subCategoryResponse =
                    new SubCategoryResponse(subCategoryModel.getUuid(),subCategoryModel.getSubCategoryName(),productResponse,categoryResponse);

            DivisionAddressResponse divisionAddressResponse=
                    new DivisionAddressResponse(divisionAddressModel.getUuid(),divisionAddressModel.getDivisionName());
            AreaAddressResponse areaAddressResponse =
                    new AreaAddressResponse(areaAddressModel.getUuid(), areaAddressModel.getAreaName());
            DistrictAddressResponse districtAddressResponse =
                    new DistrictAddressResponse(districtAddressModel.getUuid(),
                            districtAddressModel.getDistrictName(),divisionAddressResponse,areaAddressResponse);


            OrderResponse orderResponse = new OrderResponse(orderModel.getUuid(),orderModel.getOrderName(),subCategoryResponse,
                    districtAddressResponse);

            return orderResponse;
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Order not found");



    }

    @Override
    public List<OrderResponse> getOrderList(){
        List<OrderModel> orderModels = orderModelRepository.findAll();

        List<OrderResponse> orderResponses = new ArrayList<>();

        for (OrderModel orderModel : orderModels) {
            SubCategoryModel subCategoryModel = orderModel.getSubCategory();
            CategoryModel categoryModel = new CategoryModel();
            ProductModel productModel = new ProductModel();
            DistrictAddressModel districtAddressModel = orderModel.getDistrictAddressModel();
            DivisionAddressModel divisionAddressModel = new DivisionAddressModel();
            AreaAddressModel areaAddressModel=new AreaAddressModel();

            if (subCategoryModel.getCategoryModel()!=null){
                categoryModel = subCategoryModel.getCategoryModel();
            }
            if (subCategoryModel.getProductModel()!=null){
                productModel = subCategoryModel.getProductModel();
            }


            if (districtAddressModel.getDivisionAddressModel()!=null){
                divisionAddressModel= districtAddressModel.getDivisionAddressModel();

            }
            if (districtAddressModel.getAreaAddressModel()!=null){
                areaAddressModel= districtAddressModel.getAreaAddressModel();

            }

            CategoryResponse categoryResponse =
                    new CategoryResponse(categoryModel.getUuid(), categoryModel.getCategoryName());
            ProductResponse productResponse =
                    new ProductResponse(productModel.getUuid(), productModel.getProductName(),productModel.getPrice(),productModel.getCondition(),
                            productModel.getBrand(),productModel.getModelName(),productModel.getFeatures(),productModel.getDescription(),
                            productModel.getCreatedBy(),productModel.getCreatedOn(),productModel.getLastUpdatedBy());
            SubCategoryResponse subCategoryResponse =
                    new SubCategoryResponse(subCategoryModel.getUuid(),subCategoryModel.getSubCategoryName(),productResponse,categoryResponse);

            DivisionAddressResponse divisionAddressResponse=
                    new DivisionAddressResponse(divisionAddressModel.getUuid(),divisionAddressModel.getDivisionName());
            AreaAddressResponse areaAddressResponse =
                    new AreaAddressResponse(areaAddressModel.getUuid(), areaAddressModel.getAreaName());
            DistrictAddressResponse districtAddressResponse =
                    new DistrictAddressResponse(districtAddressModel.getUuid(),
                            districtAddressModel.getDistrictName(),divisionAddressResponse,areaAddressResponse);


            OrderResponse orderResponse = new OrderResponse(orderModel.getUuid(),orderModel.getOrderName(),subCategoryResponse,
                    districtAddressResponse);
            orderResponses.add(orderResponse);

        }
        return orderResponses;
    }

    @Override
    public OrderResponse editOrder(String uuid, OrderRequest orderRequest){
        Optional<OrderModel> orderModelOptional = orderModelRepository.findByUuid(uuid);

        if (orderModelOptional.isPresent()) {

            OrderModel orderModel = orderModelOptional.get();

            orderModel.setOrderName(orderRequest.getOrderName());

            orderModel = orderModelRepository.save(orderModel);

            SubCategoryModel subCategoryModel = orderModel.getSubCategory();
            CategoryModel categoryModel = subCategoryModel.getCategoryModel();
            ProductModel productModel = subCategoryModel.getProductModel();

            DistrictAddressModel districtAddressModel = orderModel.getDistrictAddressModel();
            DivisionAddressModel divisionAddressModel = districtAddressModel.getDivisionAddressModel();
            AreaAddressModel areaAddressModel = districtAddressModel.getAreaAddressModel();

            CategoryResponse categoryResponse =
                    new CategoryResponse(categoryModel.getUuid(), categoryModel.getCategoryName());
            ProductResponse productResponse =
                    new ProductResponse(productModel.getUuid(), productModel.getProductName(),productModel.getPrice(),productModel.getCondition(),
                            productModel.getBrand(),productModel.getModelName(),productModel.getFeatures(),productModel.getDescription(),
                            productModel.getCreatedBy(),productModel.getCreatedOn(),productModel.getLastUpdatedBy());

            SubCategoryResponse subCategoryResponse =
                    new SubCategoryResponse(subCategoryModel.getUuid(),subCategoryModel.getSubCategoryName(),productResponse,categoryResponse);

            DivisionAddressResponse divisionAddressResponse=
                    new DivisionAddressResponse(divisionAddressModel.getUuid(),divisionAddressModel.getDivisionName());
            AreaAddressResponse areaAddressResponse =
                    new AreaAddressResponse(areaAddressModel.getUuid(), areaAddressModel.getAreaName());
            DistrictAddressResponse districtAddressResponse =
                    new DistrictAddressResponse(districtAddressModel.getUuid(),
                            districtAddressModel.getDistrictName(),divisionAddressResponse,areaAddressResponse);

            return new OrderResponse(orderModel.getUuid(),orderModel.getOrderName(),subCategoryResponse,
                    districtAddressResponse);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order with uuid: " + uuid + " is not found.");
        }
    }

    @Override
    public boolean deleteOrder(String uuid){

        Optional<OrderModel> orderModelOptional = orderModelRepository.findByUuid(uuid);

        if (orderModelOptional.isPresent()) {
            OrderModel orderModel = orderModelOptional.get();
            orderModelRepository.delete(orderModel);

            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order with uuid: " + uuid + " is not found.");
        }

    }
}
