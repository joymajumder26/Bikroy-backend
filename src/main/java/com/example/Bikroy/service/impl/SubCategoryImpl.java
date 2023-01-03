package com.example.Bikroy.service.impl;

import com.example.Bikroy.dto.request.SubCategoryRequest;
import com.example.Bikroy.dto.response.CategoryResponse;
import com.example.Bikroy.dto.response.SubCategoryResponse;
import com.example.Bikroy.model.CategoryModel;
import com.example.Bikroy.model.SubCategoryModel;
import com.example.Bikroy.repository.CategoryModelRepository;
import com.example.Bikroy.repository.SubCategoryModelRepository;
import com.example.Bikroy.service.SubCategoryService;
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
public class SubCategoryImpl implements SubCategoryService {
    private final SubCategoryModelRepository subCategoryRepository;
    private final CategoryModelRepository categoryRepository;

    @Override
    public SubCategoryResponse createSubCategory(SubCategoryRequest subCategoryRequest){
        Optional<CategoryModel> categoryModelOptional= categoryRepository.findByUuid(subCategoryRequest.getCategoryUuid());
        CategoryModel categoryModel= null;
        if (categoryModelOptional.isPresent()) {
            categoryModel = categoryModelOptional.get();

        }
        SubCategoryModel subCategoryModel = new SubCategoryModel(subCategoryRequest.getSubCategoryName(),
                categoryModel);
        subCategoryModel.setCreatedBy("Admin");
        subCategoryModel.setCreatedOn(LocalDateTime.now());
        subCategoryModel.setUuid(UUID.randomUUID().toString());

        subCategoryModel = subCategoryRepository.save(subCategoryModel);

        categoryModel = subCategoryModel.getCategoryModel();
        CategoryResponse categoryResponse =
                new CategoryResponse(categoryModel.getUuid(), categoryModel.getCategoryName(),categoryModel.getPrice(),categoryModel.getCondition(),
                        categoryModel.getBrand(),categoryModel.getModelName(),categoryModel.getFeatures(),categoryModel.getDescription(),
                        categoryModel.getCreatedBy(),categoryModel.getCreatedOn(),categoryModel.getLastUpdatedBy());

        SubCategoryResponse subCategoryResponse =
                new SubCategoryResponse(subCategoryModel.getUuid(),subCategoryModel.getSubCategoryName(),categoryResponse);

        return subCategoryResponse;
    }
    @Override
    public List<SubCategoryResponse> getSubCategoryList(){
        List<SubCategoryModel> subCategoryModels = subCategoryRepository.findAll();

        List<SubCategoryResponse> subCategoryResponses = new ArrayList<>();

        for (SubCategoryModel subCategoryModel : subCategoryModels) {
            CategoryModel categoryModel = subCategoryModel.getCategoryModel();
            CategoryResponse categoryResponse =
                    new CategoryResponse(categoryModel.getUuid(), categoryModel.getCategoryName(),categoryModel.getPrice(),categoryModel.getCondition(),
                            categoryModel.getBrand(),categoryModel.getModelName(),categoryModel.getFeatures(),categoryModel.getDescription(),
                            categoryModel.getCreatedBy(),categoryModel.getCreatedOn(),categoryModel.getLastUpdatedBy());
            SubCategoryResponse subCategoryResponse =
                    new SubCategoryResponse(subCategoryModel.getUuid(),subCategoryModel.getSubCategoryName(),categoryResponse);
            subCategoryResponses.add(subCategoryResponse);

        }
        return subCategoryResponses;
    }
    @Override
    public SubCategoryResponse editCategory(String uuid, SubCategoryRequest subCategoryRequest){
        Optional<SubCategoryModel> subCategoryModelOptional = subCategoryRepository.findByUuid(uuid);

        if(subCategoryModelOptional.isPresent()){
            CategoryResponse categoryResponse = null;
            SubCategoryModel subCategoryModel = subCategoryModelOptional.get();

            subCategoryModel.setSubCategoryName(subCategoryRequest.getSubCategoryName());

            subCategoryModel = subCategoryRepository.save(subCategoryModel);

            return new SubCategoryResponse(subCategoryModel.getUuid(), subCategoryModel.getSubCategoryName(),categoryResponse);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "SubCategory with uuid: " + uuid + " is not found.");
        }
    }
    @Override
    public  boolean deleteCategory(String uuid){
        Optional<SubCategoryModel> subCategoryModelOptional = subCategoryRepository.findByUuid(uuid);

        if(subCategoryModelOptional.isPresent()){
            SubCategoryModel subCategoryModel = subCategoryModelOptional.get();

            subCategoryRepository.delete(subCategoryModel);

            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "SubCategory with uuid: " + uuid + " is not found.");
        }

    }
    @Override
    public SubCategoryResponse getSubCategoryByUuid(String uuid){
        Optional<SubCategoryModel> subCategoryModelOptional = subCategoryRepository.findByUuid(uuid);

        if(subCategoryModelOptional.isPresent()){
            CategoryResponse categoryResponse = null;
            SubCategoryModel subCategoryModel = subCategoryModelOptional.get();

            return new SubCategoryResponse(subCategoryModel.getUuid(), subCategoryModel.getSubCategoryName(),categoryResponse);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category with uuid: " + uuid + " is not found.");
        }

    }
}
