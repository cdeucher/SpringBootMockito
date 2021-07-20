package com.app.product.main.model;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private int Id;
    private String Name = "";
    private String Description = "";
    private List<Image> ImagesList = new ArrayList<>();

    public Product add(int id, String name, String description) {
        Id = id;
        Name = name;
        Description = description;

        return this;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public List<Image> getImagesList() {
        return ImagesList;
    }

    public void setImagesList(List<Image> imagesList) {
        ImagesList = imagesList;
    }
}
