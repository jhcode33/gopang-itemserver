package com.gopang.itemserver.service;

import com.gopang.itemserver.dto.request.category.CategorySaveDto;
import com.gopang.itemserver.dto.response.ResCategorySaveDto;
import com.gopang.itemserver.entity.Category;
import com.gopang.itemserver.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public ResCategorySaveDto save(CategorySaveDto categorySaveDto) {
        Category parent = getParentCategory(categorySaveDto.getParentId());
        Long depth = (parent != null) ? parent.getDepth() + 1 : 0L;

        categorySaveDto.setDepth(depth);
        Category category = CategorySaveDto.ofEntity(categorySaveDto);
        Long parentId = (parent != null) ? category.setParent(parent) : 0;

        Category saveCategory = categoryRepository.save(category);
        return ResCategorySaveDto.fromEntity(saveCategory, parentId);
    }

    // 부모 카테고리 찾기
    private Category getParentCategory(Long parentId) {
        if (parentId != null && parentId != 0) {
            return categoryRepository.findById(parentId)
                    .orElseThrow(() -> new IllegalArgumentException("Parent category not found."));
        }
        return null;
    }
}
