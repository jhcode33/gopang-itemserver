package com.gopang.itemserver.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemSaveRequest {
    private Long categoryId;
    private ItemSaveDto itemSaveDto;
    private ItemDetailSaveDto itemDetailSaveDto;
    private ItemOptionSaveDto itemOptionSaveDto;
    private BrandManufacturerSaveDto brandManufacturerSaveDto;
}
