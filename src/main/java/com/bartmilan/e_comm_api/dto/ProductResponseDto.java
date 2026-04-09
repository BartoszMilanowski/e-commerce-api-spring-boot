package com.bartmilan.e_comm_api.dto;

import com.bartmilan.e_comm_api.model.AvailabilityStatus;

import java.math.BigDecimal;
import java.util.List;

public class ProductResponseDto {

    private Long id;
    private String name;
    private String description;
    private AvailabilityStatus available;
    private BigDecimal price;
    private Long stock;
    private String mainPhoto;
    private List<String> gallery;
    private Long categoryId;
    private String categoryName;

    public ProductResponseDto(Long id, String name, String description, AvailabilityStatus available,
                              BigDecimal price, Long stock, String mainPhoto, List<String> gallery,
                              Long categoryId, String categoryName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.available = available;
        this.price = price;
        this.stock = stock;
        this.mainPhoto = mainPhoto;
        this.gallery = gallery;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public AvailabilityStatus getAvailable() {
        return available;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getStock() {
        return stock;
    }

    public String getMainPhoto() {
        return mainPhoto;
    }

    public List<String> getGallery() {
        return gallery;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
