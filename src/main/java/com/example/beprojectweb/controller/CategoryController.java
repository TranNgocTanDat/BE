package com.example.beprojectweb.controller;

import com.example.beprojectweb.dto.request.category.CategoryRequest;
import com.example.beprojectweb.dto.response.APIResponse;
import com.example.beprojectweb.dto.response.category.CategoryResponse;
import com.example.beprojectweb.dto.response.product.ProductResponse;
import com.example.beprojectweb.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {
    CategoryService categoryService;

    @PostMapping
    public APIResponse<CategoryResponse> createCategory(@RequestBody CategoryRequest request){
        APIResponse apiResponse = new APIResponse();
        apiResponse.setResult(categoryService.createCategory(request));
        return apiResponse;
    }

    @GetMapping
    public APIResponse<List<CategoryResponse>> getCategories() {
        return APIResponse.<List<CategoryResponse>>builder()
                .result(categoryService.getCategories())
                .build();
    }

    @GetMapping("/{cate_ID}")
    public APIResponse<CategoryResponse> getCategoryById(@PathVariable int cate_ID){
        return APIResponse.<CategoryResponse>builder()
                .result(categoryService.getCategoryById(String.valueOf(cate_ID)))
                .build();
    }


}
