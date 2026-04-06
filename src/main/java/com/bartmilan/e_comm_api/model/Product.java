package com.bartmilan.e_comm_api.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AvailabilityStatus available;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Long stock;

    @Column
    private String mainPhoto;

    @Column
    private List<String> gallery;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product() {
    }

    public Product(String name, String description, AvailabilityStatus available, BigDecimal price, Long stock, String mainPhoto, List<String> gallery, Category category) {
        this.name = name;
        this.description = description;
        this.available = available;
        this.price = price;
        this.stock = stock;
        this.mainPhoto = mainPhoto;
        this.gallery = gallery;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AvailabilityStatus getAvailable() {
        return available;
    }

    public void setAvailable(AvailabilityStatus available) {
        this.available = available;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public String getMainPhoto() {
        return mainPhoto;
    }

    public void setMainPhoto(String mainPhoto) {
        this.mainPhoto = mainPhoto;
    }

    public List<String> getGallery() {
        return gallery;
    }

    public void setGallery(List<String> gallery) {
        this.gallery = gallery;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
