package com.example.Bikroy.service.impl;

import com.example.Bikroy.dto.request.ProductRequest;
import com.example.Bikroy.dto.response.CategoryResponse;
import com.example.Bikroy.dto.response.ProductResponse;
import com.example.Bikroy.model.CategoryModel;
import com.example.Bikroy.model.ProductModel;
import com.example.Bikroy.repository.ProductRepository;
import com.example.Bikroy.service.ProductService;
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
public class ProductImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest){
        ProductModel productModel = new ProductModel(productRequest.getProductName(),
                productRequest.getPrice(),productRequest.getCondition(),productRequest.getBrand(),
                productRequest.getModelName(),productRequest.getFeatures(),productRequest.getDescription());

        productModel.setCreatedBy("Admin");
        productModel.setCreatedOn(LocalDateTime.now());
        productModel.setUuid(UUID.randomUUID().toString());
        productModel.setLastUpdatedBy(productModel.getLastUpdatedBy());

        productModel = productRepository.save(productModel);

        ProductResponse productResponse =
                new ProductResponse(productModel.getUuid(), productModel.getProductName(),productModel.getPrice(),productModel.getCondition(),
                        productModel.getBrand(),productModel.getModelName(),productModel.getFeatures(),productModel.getDescription(),
                        productModel.getCreatedBy(),productModel.getCreatedOn(),productModel.getLastUpdatedBy());

        return productResponse;

    }

    @Override
    public List<ProductResponse> getProductList(){
        List<ProductModel> productModels = productRepository.findAll();

        List<ProductResponse> productResponses = new ArrayList<>();

        for (ProductModel productModel: productModels) {
            ProductResponse productResponse =
                    new ProductResponse(productModel.getUuid(), productModel.getProductName(),productModel.getPrice(),productModel.getCondition(),
                            productModel.getBrand(),productModel.getModelName(),productModel.getFeatures(),productModel.getDescription(),
                            productModel.getCreatedBy(),productModel.getCreatedOn(),productModel.getLastUpdatedBy());
            productResponses.add(productResponse);
        }

        return productResponses;
    }

    @Override
    public ProductResponse editProduct(String uuid, ProductRequest productRequest){
        Optional<ProductModel> productModelOptional = productRepository.findByUuid(uuid);

        if(productModelOptional.isPresent()){
           ProductModel productModel = productModelOptional.get();

            productModel.setProductName(productRequest.getProductName());

            productModel = productRepository.save(productModel);

            return  new ProductResponse(productModel.getUuid(), productModel.getProductName(),productModel.getPrice(),productModel.getCondition(),
                    productModel.getBrand(),productModel.getModelName(),productModel.getFeatures(),productModel.getDescription(),
                    productModel.getCreatedBy(),productModel.getCreatedOn(),productModel.getLastUpdatedBy());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with uuid: " + uuid + " is not found.");
        }
    }

    @Override
    public boolean deleteProduct(String uuid){
        Optional<ProductModel> productModelOptional = productRepository.findByUuid(uuid);

        if(productModelOptional.isPresent()){
            ProductModel productModel = productModelOptional.get();

            productRepository.delete(productModel);

            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with uuid: " + uuid + " is not found.");
        }
    }

    @Override
    public ProductResponse getProductByUuid(String uuid){
        Optional<ProductModel> productModelOptional = productRepository.findByUuid(uuid);

        if(productModelOptional.isPresent()){
            ProductModel productModel = productModelOptional.get();

            return  new ProductResponse(productModel.getUuid(), productModel.getProductName(),productModel.getPrice(),productModel.getCondition(),
                    productModel.getBrand(),productModel.getModelName(),productModel.getFeatures(),productModel.getDescription(),
                    productModel.getCreatedBy(),productModel.getCreatedOn(),productModel.getLastUpdatedBy());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with uuid: " + uuid + " is not found.");
        }
    }
    @Override
    public  List<ProductResponse> filterProducts(Integer pageNo, Integer pageSize, String uuid, String productName){

        return null;
    }
}
