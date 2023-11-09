package com.gopang.itemserver.controller;

import com.gopang.itemserver.dto.request.category.CategorySaveDto;
import com.gopang.itemserver.dto.response.ResCategorySaveDto;
import com.gopang.itemserver.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/category")
    private ResponseEntity<ResCategorySaveDto> save(
            @RequestBody @Valid CategorySaveDto categorySaveDto) {

        ResCategorySaveDto category = categoryService.save(categorySaveDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(category);
    }
}
