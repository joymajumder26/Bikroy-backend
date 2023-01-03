package com.example.Bikroy.service;

import com.example.Bikroy.dto.request.SubCategoryRequest;
import com.example.Bikroy.dto.response.SubCategoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubCategoryService {
    SubCategoryResponse createSubCategory(SubCategoryRequest subCategoryRequest);

    List<SubCategoryResponse> getSubCategoryList();

    SubCategoryResponse editCategory(String uuid, SubCategoryRequest subCategoryRequest);

    boolean deleteCategory(String uuid);

    SubCategoryResponse getSubCategoryByUuid(String uuid);
}
