package com.gopang.itemserver.controller;

import com.gopang.itemserver.dto.request.category.CategorySaveDto;
import com.gopang.itemserver.dto.request.category.CategoryUpdateDto;
import com.gopang.itemserver.dto.response.ResCategoryDto;
import com.gopang.itemserver.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<ResCategoryDto> save(
            @RequestBody @Valid CategorySaveDto categorySaveDto) {

        ResCategoryDto category = categoryService.save(categorySaveDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(category);
    }

    @PutMapping("/category/{categoryId}")
    public ResponseEntity<ResCategoryDto> update(
            @PathVariable("categoryId") Long categoryId,
            @RequestBody @Valid CategoryUpdateDto categoryUpdateDto) {

        categoryUpdateDto.setCategoryId(categoryId);
        ResCategoryDto category = categoryService.update(categoryUpdateDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(category);
    }
}
