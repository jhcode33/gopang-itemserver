package com.gopang.itemserver.dto.response;

import com.gopang.itemserver.entity.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * -Response
 * 판매자 상품 등록 정보
 */
@Getter
@Setter
@NoArgsConstructor
public class ResItemSaveDto {

    private Long itemId;
    private String titleName;
    private String sellerName;
    private String state;

    private LocalDate sellStartDate;
    private LocalDate sellEndDate;

    private Long sellerId;
    private Long sellerDeliveryId;
    private Long sellerREId;

    @Builder
    public ResItemSaveDto(Long itemId, String titleName, String sellerName, String state, LocalDate sellStartDate, LocalDate sellEndDate, Long sellerId, Long sellerDeliveryId, Long sellerREId) {
        this.itemId = itemId;
        this.titleName = titleName;
        this.sellerName = sellerName;
        this.state = state;
        this.sellStartDate = sellStartDate;
        this.sellEndDate = sellEndDate;
        this.sellerId = sellerId;
        this.sellerDeliveryId = sellerDeliveryId;
        this.sellerREId = sellerREId;
    }

    public static ResItemSaveDto fromEntity(Item item) {
        return ResItemSaveDto.builder()
                .itemId(item.getItemId())
                .titleName(item.getTitleName())
                .sellerName(item.getItemLabel())
                .state(item.getState().toString())
                .sellStartDate(item.getSellStartDate())
                .sellEndDate(item.getSellEndDate())
                .sellerId(item.getSellerId())
                .sellerDeliveryId(item.getSellerDeliveryId())
                .sellerREId(item.getSellerREId())
                .build();
    }
}
