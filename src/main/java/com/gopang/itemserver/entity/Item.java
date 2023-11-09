package com.gopang.itemserver.entity;

import com.gopang.itemserver.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Entity
@Table(name = "Item")
@NoArgsConstructor
public class Item extends BaseTimeEntity {

    public enum SellState { 판매중, 판매중지 }

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "title_name", length = 100)
    private String titleName;

    @Column(name = "seller_name", length = 100)
    private String sellerName;

    @Enumerated(EnumType.STRING)
    private SellState state;

    private LocalDate sellStartDate;
    private LocalDate sellEndDate;

    @Column(name = "seller_id")
    private Long sellerId;

    @Column(name = "seller_delivery_id")
    private Long sellerDeliveryId;

    @Column(name = "seller_Return_Exchange_id")
    private Long sellerREId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    public List<ImageFile> images = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<ItemOption> options = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    public List<BrandManufacturer> brandManufacturer = new ArrayList<>();

//    @OneToOne
//    @JoinColumn(name = "item_detail_id", unique = true)
//    public ItemDetail itemDetail;

//    @OneToOne
//    @JoinColumn(name = "description_file_id", unique = true)
//    public DescriptionFile descriptionFile;
    @Builder
    public Item(Long itemId, String titleName, String sellerName, SellState state, LocalDate sellStartDate, LocalDate sellEndDate, Long sellerId, Long sellerDeliveryId, Long sellerREId, Category category, List<ImageFile> images, List<ItemOption> options, List<BrandManufacturer> brandManufacturer) {
        this.itemId = itemId;
        this.titleName = titleName;
        this.sellerName = sellerName;
        this.state = state;
        this.sellStartDate = sellStartDate;
        this.sellEndDate = sellEndDate;
        this.sellerId = sellerId;
        this.sellerDeliveryId = sellerDeliveryId;
        this.sellerREId = sellerREId;
        this.category = category;
        this.images = images;
        this.options = options;
        this.brandManufacturer = brandManufacturer;
    }

    //== 양방향 연관관계 편의 메서드 ==//
    public void setCategory(Category category) {
        this.category = category;
        category.getItems().add(this); // 양방향 연관관계 설정
    }
}
