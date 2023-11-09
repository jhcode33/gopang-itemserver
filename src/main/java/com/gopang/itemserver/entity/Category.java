package com.gopang.itemserver.entity;

import com.gopang.itemserver.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Entity
@Table(name = "category")
@NoArgsConstructor
public class Category extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent")
    private Category parent;

    @Column(name = "depth")
    private Long depth;

    @Builder.Default
    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Item> items = new ArrayList<>();

    @Builder
    public Category(Long categoryId, String name, Category parent, Long depth, List<Category> child, List<Item> items) {
        this.categoryId = categoryId;
        this.name = name;
        this.parent = parent;
        this.depth = depth;
        this.child = child;
        this.items = items;
    }

    //== 양방향 연관관계 편의 메서드 ==//
    public void addItem(Item item) {
        items.add(item);
        item.setCategory(this); // 양방향 연관관계 설정
    }

    public void setParent(Category parent) {
        this.parent = parent;
        parent.getChild().add(this);
    }

}
