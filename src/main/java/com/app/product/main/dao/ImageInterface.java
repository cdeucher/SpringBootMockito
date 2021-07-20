package com.app.product.main.dao;

import com.app.product.main.model.Image;

import java.util.ArrayList;
import java.util.List;


public abstract interface ImageInterface {

    public static ArrayList<Image> imageList = new ArrayList<>();

    public abstract Image save(Image img);

    public static List<Image> getImagesByProductId(int productId) {
        return null;
    }

    public abstract Image getImagesById(int imageId);

    public abstract void removeImage(int imageId);
}
