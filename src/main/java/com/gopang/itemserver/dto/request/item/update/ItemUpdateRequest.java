package com.gopang.itemserver.dto.request.item.update;

import com.gopang.itemserver.dto.request.item.save.BrandManufacturerSaveDto;
import com.gopang.itemserver.dto.request.item.save.ItemDetailSaveDto;
import com.gopang.itemserver.dto.request.item.save.ItemOptionSaveDto;
import com.gopang.itemserver.dto.request.item.save.ItemSaveDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * -Request
 * 판매자 상품 수정 정보들
 */
@Getter
@Setter
@NoArgsConstructor
public class ItemUpdateRequest {
    private Long categoryId;
    private ItemUpdateDto itemUpdateDto;
    private ItemDetailUpdateDto itemDetailUpdateDto;
    private ItemOptionUpdateDto itemOptionUpdateDto;
    private BrandManufacturerUpdateDto brandManufacturerUpdateDto;
}
