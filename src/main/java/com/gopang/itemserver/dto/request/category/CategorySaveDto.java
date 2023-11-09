package com.gopang.itemserver.dto.request.category;

import com.gopang.itemserver.entity.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategorySaveDto {

    private String name;
    private Long parentId;
    private Long depth;

    @Builder
    public CategorySaveDto(String name, Long parentId, Long depth) {
        this.name = name;
        this.parentId = parentId;
        this.depth = depth;
    }

    public static Category ofEntity(CategorySaveDto dto) {
        return Category.builder()
                .name(dto.name)
                .build();
    }
}
