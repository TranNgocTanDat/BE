package com.example.beprojectweb.service;

import com.example.beprojectweb.dto.request.category.CategoryRequest;
import com.example.beprojectweb.dto.response.APIResponse;
import com.example.beprojectweb.dto.response.UserResponse;
import com.example.beprojectweb.dto.response.category.CategoryResponse;
import com.example.beprojectweb.entity.Category;
import com.example.beprojectweb.exception.AppException;
import com.example.beprojectweb.exception.ErrorCode;
import com.example.beprojectweb.mapper.CategoryMapper;
import com.example.beprojectweb.mapper.UserMapper;
import com.example.beprojectweb.repository.CategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService {
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;
    private final UserMapper userMapper;

    public Category createCategory(CategoryRequest request){
        if(categoryRepository.existsCategoriesByName(request.getName()))
            throw new AppException(ErrorCode.CATEGORY_EXISTED);

        Category category = categoryMapper.category(request);
        return categoryRepository.save(category);
    }

    public List<CategoryResponse> getCategories(){
        return categoryRepository.findAll()
                .stream()
                .map(category -> categoryMapper.toCategoryResponse(category))
                .toList();
    }

    public CategoryResponse getCategoryById(String id){
        return categoryMapper.toCategoryResponse(categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found")));
    }

    public CategoryResponse updateCategory(String id, CategoryRequest request){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("category not found"));

        categoryMapper.updateCategory(category, request);
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }



}
