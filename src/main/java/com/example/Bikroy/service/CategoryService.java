package com.example.Bikroy.service;

import com.example.Bikroy.dto.request.CategoryRequest;
import com.example.Bikroy.dto.response.CategoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest categoryRequest);

    List<CategoryResponse> getCategoryList();

    CategoryResponse editCategory(String uuid, CategoryRequest categoryRequest);

    boolean deleteCategory(String uuid);

    CategoryResponse getCategoryByUuid(String uuid);

    List<CategoryResponse> filterCategories(Integer pageNo, Integer pageSize, String uuid, String categoryName);
}
