package com.example.Bikroy.controller;

import com.example.Bikroy.dto.request.SubCategoryRequest;
import com.example.Bikroy.dto.response.SubCategoryResponse;
import com.example.Bikroy.service.SubCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/subCategories")
public class SubCategoryController {
    private SubCategoryService subCategoryService;

    @PostMapping
    public SubCategoryResponse createSubCategory(@RequestBody SubCategoryRequest subCategoryRequest){
        return subCategoryService.createSubCategory(subCategoryRequest);
    }
    @GetMapping
    public List<SubCategoryResponse> getSubCategoryList(){
        return subCategoryService.getSubCategoryList();
    }


    @PutMapping("/{subCategory-uuid}")
    public SubCategoryResponse editSubCategory(@PathVariable("subCategory-uuid") String uuid,
                                               @RequestBody SubCategoryRequest subCategoryRequest){
        return subCategoryService.editCategory(uuid, subCategoryRequest);
    }

    @DeleteMapping("/{subCategory-uuid}")
    public boolean deleteSubCategory(@PathVariable("subCategory-uuid") String uuid) {
        return subCategoryService.deleteCategory(uuid);
    }

    @GetMapping("/{subCategory-uuid}")
    public SubCategoryResponse getSubCategoryByUuid(@PathVariable("subCategory-uuid") String uuid) {
        return subCategoryService.getSubCategoryByUuid(uuid);
    }
}
