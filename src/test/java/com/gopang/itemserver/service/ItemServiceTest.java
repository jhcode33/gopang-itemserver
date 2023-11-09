package com.gopang.itemserver.service;

import com.gopang.itemserver.dto.request.BrandManufacturerSaveDto;
import com.gopang.itemserver.dto.request.ItemDetailSaveDto;
import com.gopang.itemserver.dto.request.ItemOptionSaveDto;
import com.gopang.itemserver.dto.request.ItemSaveDto;
import com.gopang.itemserver.dto.response.ResItemSaveDto;
import com.gopang.itemserver.entity.Category;
import com.gopang.itemserver.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
//@Transactional
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryRepository categoryRepository;

    ItemSaveDto itemSaveDto;
    ItemDetailSaveDto itemDetailSaveDto;
    ItemOptionSaveDto itemOptionSaveDto;
    BrandManufacturerSaveDto brandManufacturerSaveDto;

    Category rootCategory;
    Category childCategory1;
    Category childCategory2;
    Category grandchildCategory1;
    Category grandchildCategory2;
    Category grandchildCategory3;

    @BeforeEach
    public void setUp() {
        itemDataSet();
        categoryDataSet();
    }

    @Test
    public void saveItemTest() {
        // when
        Long sellerId = 1L;
        Long sellerDeliveryId = 2L;
        Long sellerREId = 3L;
        Long categoryId = 4L;

        // given
        ResItemSaveDto resItemSaveDto = itemService.save(sellerId, sellerDeliveryId, sellerREId, categoryId,
                itemSaveDto, itemDetailSaveDto, itemOptionSaveDto, brandManufacturerSaveDto);

        // then
        assertEquals(itemSaveDto.getTitleName(), resItemSaveDto.getTitleName()); // 예시로 아이템 타이틀 검증

    }

    @Test
    public void saveCategoryTest() {
        // when
        // given
        // then
        categoryRepository.save(rootCategory);
        categoryRepository.save(childCategory1);
        categoryRepository.save(childCategory2);
        categoryRepository.save(grandchildCategory1);
        categoryRepository.save(grandchildCategory2);
        categoryRepository.save(grandchildCategory3);
    }

    private void itemDataSet() {
        itemSaveDto = ItemSaveDto.builder()
                .sellerId(1L)
                .sellerDeliveryId(2L)
                .sellerREId(3L)
                .titleName("Sample Item")
                .sellerName("Sample Seller")
                .sellState("판매중")
                .sellStartDate(LocalDate.now())
                .sellEndDate(LocalDate.now().plusDays(30))
                .build();

        itemDetailSaveDto = ItemDetailSaveDto.builder()
                .maxOrder("Sample Max Order")
                .parallelType("병행수입")
                .minorType("미성년자구입")
                .taxType("과세")
                .maker("Sample Maker")
                .manufacturerItemName("Sample Manufacturer Item Name")
                .warning("Sample Warning")
                .qualityAssurance("Sample Quality Assurance")
                .asInfo("Sample AS Info")
                .build();

        itemOptionSaveDto = ItemOptionSaveDto.builder()
                .itemModelName("Sample Item Model Name")
                .sellerItemNumber("Sample Seller Item Number")
                .optionName("Sample Option Name")
                .sellAmount(10L)
                .normalCost(10000L)
                .discountCost(8000L)
                .sellCost(9000L)
                .inventoryAmount(50L)
                .build();

        brandManufacturerSaveDto = BrandManufacturerSaveDto.builder()
                .brandName("Sample Brand")
                .manufacturerName("Sample Manufacturer")
                .build();
    }
    private void categoryDataSet() {
        // Category 엔티티를 생성하고 예시 데이터를 추가하는 코드
        rootCategory = new Category();
        rootCategory.setName("Root Category");
        rootCategory.setDepth(0L);

        childCategory1 = new Category();
        childCategory1.setName("Child Category 1");
        childCategory1.setDepth(1L);
        childCategory1.setParent(rootCategory);

        childCategory2 = new Category();
        childCategory2.setName("Child Category 2");
        childCategory2.setDepth(1L);
        childCategory2.setParent(rootCategory);

        grandchildCategory1 = new Category();
        grandchildCategory1.setName("Grandchild Category 1");
        grandchildCategory1.setDepth(2L);
        grandchildCategory1.setParent(childCategory1);

        grandchildCategory2 = new Category();
        grandchildCategory2.setName("Grandchild Category 2");
        grandchildCategory2.setDepth(2L);
        grandchildCategory2.setParent(childCategory1);

        grandchildCategory3 = new Category();
        grandchildCategory3.setName("Grandchild Category 3");
        grandchildCategory3.setDepth(2L);
        grandchildCategory3.setParent(childCategory2);

        // Category 엔티티 간의 연관관계 설정
        rootCategory.getChild().add(childCategory1);
        rootCategory.getChild().add(childCategory2);
        childCategory1.getChild().add(grandchildCategory1);
        childCategory1.getChild().add(grandchildCategory2);
        childCategory2.getChild().add(grandchildCategory3);
    }
}
