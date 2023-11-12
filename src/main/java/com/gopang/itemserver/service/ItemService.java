package com.gopang.itemserver.service;

import com.gopang.itemserver.dto.request.item.*;
import com.gopang.itemserver.dto.request.item.save.*;
import com.gopang.itemserver.dto.request.item.update.BrandManufacturerUpdateDto;
import com.gopang.itemserver.dto.request.item.update.ItemDetailUpdateDto;
import com.gopang.itemserver.dto.request.item.update.ItemOptionUpdateDto;
import com.gopang.itemserver.dto.request.item.update.ItemUpdateRequest;
import com.gopang.itemserver.dto.response.ResItemSaveDto;
import com.gopang.itemserver.dto.response.ResItemUpdateDto;
import com.gopang.itemserver.entity.*;
import com.gopang.itemserver.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public ResItemSaveDto save(SellerInfo sellerInfo, ItemSaveRequest itemSaveRequest) {
        Long categoryId = itemSaveRequest.getCategoryId();
        ItemSaveDto itemDto = itemSaveRequest.getItemSaveDto();
        ItemDetailSaveDto itemDetailDto = itemSaveRequest.getItemDetailSaveDto();
        ItemOptionSaveDto itemOptionDto = itemSaveRequest.getItemOptionSaveDto();
        BrandManufacturerSaveDto brandManuDto = itemSaveRequest.getBrandManufacturerSaveDto();

        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new IllegalArgumentException("Not Found")
        );

        itemDto.setSellerInfo(sellerInfo.getSellerId(), sellerInfo.getSellerDeliveryId(), sellerInfo.getSellerREId());
        Item item = ItemSaveDto.ofEntity(itemDto);
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

    public ResItemUpdateDto update(SellerInfo sellerInfo, ItemUpdateRequest itemRequest) {
        Long categoryId = itemRequest.getCategoryId();

        Category newCategory = categoryRepository.findById(categoryId).orElseThrow(
                () -> new IllegalArgumentException("Not Found")
        );

        Item item = itemRepository.findById(itemRequest.getItemUpdateDto().getItemId()).orElseThrow(
                () -> new IllegalArgumentException("Item not found")
        );

        ItemDetail itemDetail = updateItemDetail(itemRequest.getItemDetailUpdateDto());
        ItemOption itemOption = updateOption(item, itemRequest.getItemOptionUpdateDto());
        BrandManufacturer brandManu = updateBrandManu(item, itemRequest.getBrandManufacturerUpdateDto());



    }

    private ItemDetail updateItemDetail(ItemDetailUpdateDto itemDetailUpdateDto) {
        ItemDetail itemDetail = itemDetailRepository.findById(itemDetailUpdateDto.getItemDetailId()).orElseThrow(
                () -> new IllegalArgumentException(("ItemDetail not found"))
        );
        itemDetail.update(itemDetailUpdateDto);
        return itemDetail;
    }

    private ItemOption updateOption(Item item, ItemOptionUpdateDto dto) {
        for (ItemOption option : item.getOptions()) {
            Long updateOptionId = dto.getItemOptionId();
            if (option.getItemOptionId() == updateOptionId) {
                option.update(dto);
                return option;
            }
        }
        return null;
    }

    private BrandManufacturer updateBrandManu(Item item, BrandManufacturerUpdateDto dto) {
        for (BrandManufacturer brandManu : item.getBrandManufacturer()) {
            Long brandManuId = dto.getBrandManuId();
            if (brandManu.getBrandManufacturerId() == brandManuId) {
                brandManu.update(dto);
                return brandManu;
            }
        }
        return null;
    }
}
