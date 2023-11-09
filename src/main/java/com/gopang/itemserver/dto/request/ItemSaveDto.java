package com.gopang.itemserver.dto.request;

import com.gopang.itemserver.entity.Item;
import com.gopang.itemserver.entity.Item.SellState;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * -Request
 * 판매자 상품 등록 정보
 */
@Getter
@Setter
@NoArgsConstructor
public class ItemSaveDto {

    // 판매자 관련 정보
    private Long sellerId;
    private Long sellerDeliveryId;
    private Long sellerREId;

    private String titleName;
    private String sellerName;
    private String sellState;
    private LocalDate sellStartDate;
    private LocalDate sellEndDate;

    @Builder
    public ItemSaveDto(Long sellerId, Long sellerDeliveryId, Long sellerREId, String titleName, String sellerName, String sellState, LocalDate sellStartDate, LocalDate sellEndDate) {
        this.sellerId = sellerId;
        this.sellerDeliveryId = sellerDeliveryId;
        this.sellerREId = sellerREId;
        this.titleName = titleName;
        this.sellerName = sellerName;
        this.sellState = sellState;
        this.sellStartDate = sellStartDate;
        this.sellEndDate = sellEndDate;
    }

    public static Item ofEntity(ItemSaveDto dto) {
        return Item.builder()
                .sellerId(dto.sellerId)
                .sellerDeliveryId(dto.sellerDeliveryId)
                .sellerREId(dto.sellerREId)
                .titleName(dto.titleName)
                .sellerName(dto.sellerName)
                .state(SellState.valueOf(dto.sellState))
                .sellStartDate(dto.sellStartDate)
                .sellEndDate(dto.sellEndDate)
                .build();
    }

    public void setSellerInfo(Long sellerId, Long sellerDeliveryId, Long sellerREId) {
        this.sellerId = sellerId;
        this.sellerDeliveryId = sellerDeliveryId;
        this.sellerREId = sellerREId;
    }
}
