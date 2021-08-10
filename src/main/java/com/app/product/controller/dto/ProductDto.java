package com.app.product.controller.dto;

import com.app.product.entity.Image;
import com.app.product.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDto {
    private int id;
    private String name = "";
    private String description = "";
    private List<Image> imagesList = new ArrayList<>();

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.imagesList = product.getImagesList();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Image> getImagesList() {
        return imagesList;
    }

    public static List<ProductDto> toProduct(List<Product> products){
        return products.stream().map(ProductDto::new).collect(Collectors.toList());
    }
}
