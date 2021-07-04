package com.company.chat.main.model;

public class Product {

    private int Id;
    private String Name;
    private String Description;
    private String Image;

    public Product(int id, String name, String description, String image) {
        Id = id;
        Name = name;
        Description = description;
        Image = image;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
