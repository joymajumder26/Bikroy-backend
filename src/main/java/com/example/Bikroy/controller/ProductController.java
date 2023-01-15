package com.example.Bikroy.controller;

import com.example.Bikroy.dto.request.CategoryRequest;
import com.example.Bikroy.dto.request.ProductRequest;
import com.example.Bikroy.dto.response.CategoryResponse;
import com.example.Bikroy.dto.response.ProductResponse;
import com.example.Bikroy.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {
    private ProductService productService;


    @PostMapping
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    @GetMapping
    public List<ProductResponse> getProductList(){
        return productService.getProductList();
    }

    @PutMapping("/{product-uuid}")
    public ProductResponse editProduct(@PathVariable("product-uuid") String uuid,
                                         @RequestBody ProductRequest productRequest){
        return productService.editProduct(uuid, productRequest);
    }

    @DeleteMapping("/{product-uuid}")
    public boolean deleteProduct(@PathVariable("product-uuid") String uuid) {
        return productService.deleteProduct(uuid);
    }

    @GetMapping("/{category-uuid}")
    public ProductResponse getProductByUuid(@PathVariable("product-uuid") String uuid) {
        return productService.getProductByUuid(uuid);
    }

    @GetMapping("/filter")
    public List<ProductResponse> filterProducts(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String uuid,
            @RequestParam(required = false) String productName
    ){
        return productService.filterProducts(pageNo, pageSize, uuid, productName);
    }
}
