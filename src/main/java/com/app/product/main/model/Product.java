package com.app.product.main.model;

public class Product {

    private int Id;
    private String Name = "";
    private String Description = "";

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
}
