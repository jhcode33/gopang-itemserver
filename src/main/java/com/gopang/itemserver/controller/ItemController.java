package com.gopang.itemserver.controller;

import com.gopang.itemserver.dto.request.item.ItemSaveRequest;
import com.gopang.itemserver.dto.request.item.SellerInfo;
import com.gopang.itemserver.dto.response.ResItemSaveDto;
import com.gopang.itemserver.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    //private final RestTemplate restTemplate;

    @PostMapping("/seller/item")
    public ResponseEntity<ResItemSaveDto> save(
            @RequestBody ItemSaveRequest itemRequest) {
        // 판매자 id, 판매자 배송지 id, 판매자 환불지 id
        // 원래는 RestTemplate or Kafka으로 요청해야 함
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(1L);
        sellerInfo.setSellerDeliveryId(2L);
        sellerInfo.setSellerREId(3L);

        // Front에서 카테고리 분류를 선택하고 해당 ID 값을 같이 넘겨줌
        // categoryId = 4L;

        ResItemSaveDto itemSave = itemService.save(
                itemRequest.getCategoryId(),
                sellerInfo,
                itemRequest.getItemSaveDto(),
                itemRequest.getItemDetailSaveDto(),
                itemRequest.getItemOptionSaveDto(),
                itemRequest.getBrandManufacturerSaveDto());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(itemSave);
    }
}
