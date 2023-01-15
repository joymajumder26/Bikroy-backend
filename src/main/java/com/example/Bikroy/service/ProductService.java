package com.example.Bikroy.service;

import com.example.Bikroy.dto.request.ProductRequest;
import com.example.Bikroy.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);

    List<ProductResponse> getProductList();

    ProductResponse editProduct(String uuid, ProductRequest productRequest);



    boolean deleteProduct(String uuid);

    ProductResponse getProductByUuid(String uuid);

    List<ProductResponse> filterProducts(Integer pageNo, Integer pageSize, String uuid, String productName);
}
