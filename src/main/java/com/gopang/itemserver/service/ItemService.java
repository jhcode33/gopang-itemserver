package com.gopang.itemserver.service;

import com.gopang.itemserver.dto.request.BrandManufacturerSaveDto;
import com.gopang.itemserver.dto.request.ItemDetailSaveDto;
import com.gopang.itemserver.dto.request.ItemOptionSaveDto;
import com.gopang.itemserver.dto.request.ItemSaveDto;
import com.gopang.itemserver.dto.response.ResItemSaveDto;
import com.gopang.itemserver.entity.*;
import com.gopang.itemserver.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemDetailRepository itemDetailRepository;
    private final ItemOptionRepository itemOptionRepository;
    private final BrandManufacturerRepository brandManufacturerRepository;
    private final CategoryRepository categoryRepository;

    // 판매자 id, 판매자 배송 정보 id, 판매자 환불 정보 id, 카테고리 id
    // 판매자 배송, 환불 정보x
    public ResItemSaveDto save(Long sellerId, Long sellerDeliveryId, Long sellerREId, Long categoryId,
                               ItemSaveDto itemSaveDto,
                               ItemDetailSaveDto itemDetailDto,
                               ItemOptionSaveDto itemOptionDto,
                               BrandManufacturerSaveDto brandManuDto) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new IllegalArgumentException("Not Found")
        );

        itemSaveDto.setSellerInfo(sellerId, sellerDeliveryId, sellerREId);
        Item item = ItemSaveDto.ofEntity(itemSaveDto);
        item.setCategory(category);
        itemRepository.save(item);

        ItemDetail itemDetail = ItemDetailSaveDto.ofEntity(itemDetailDto);
        itemDetail.setItem(item);
        itemDetailRepository.save(itemDetail);

        ItemOption itemOption = ItemOptionSaveDto.ofEntity(itemOptionDto);
        itemOption.setItem(item);
        itemOptionRepository.save(itemOption);

        BrandManufacturer brandManufacturer = BrandManufacturerSaveDto.ofEntity(brandManuDto);
        brandManufacturer.setItem(item);
        brandManufacturerRepository.save(brandManufacturer);


        return ResItemSaveDto.fromEntity(item);
    }
}
