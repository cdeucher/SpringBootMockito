package com.app.product.main.service;

import com.app.product.main.dao.ImageDao;
import com.app.product.main.model.Image;

import java.text.AttributedString;
import java.util.List;

public class ImageService {

    private ImageDao dao;

    public ImageService(ImageDao dao) {
        this.dao = dao;
    }

    public Image insertNewImage(Image image){
        return dao.save(image);
    }
    public List list(int productId){
        return dao.getImagesByProductId(productId);
    }

    public Image getImage(int imageId){
        return dao.getImagesById(imageId);
    }

}
