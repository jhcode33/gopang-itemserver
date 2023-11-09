package com.gopang.itemserver.dto.response;

import com.gopang.itemserver.entity.Category;
import com.gopang.itemserver.entity.Item;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
/**
 * -Response
 * 카테고리 등록 정보
 */
@Getter
@Setter
@NoArgsConstructor
public class ResCategorySaveDto {

    private Long categoryId;
    private String name;
    private Long parentId;
    private Long depth;

    @Builder
    public ResCategorySaveDto(Long categoryId, String name, Long parentId, Long depth) {
        this.categoryId = categoryId;
        this.name = name;
        this.parentId = parentId;
        this.depth = depth;
    }

    public static ResCategorySaveDto fromEntity(Category category) {
        return ResCategorySaveDto.builder()
                .categoryId(category.getCategoryId())
                .name(category.getName())
                //.parentId(category.getParent().getCategoryId())
                .depth(category.getDepth())
                .build();
    }
}
