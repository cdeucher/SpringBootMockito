package com.app.product.main.model;

public class Image {
    private String name;
    private String url;
    private int productId;
    private int id;

    public Image(String name, String url, int productId, int id) {
        this.name = name;
        this.url = url;
        this.productId = productId;
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
