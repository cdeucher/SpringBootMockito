package com.app.product.service;

import com.app.product.dao.ImageDaoImpl;
import com.app.product.entity.Image;
import com.app.product.repository.ImageDao;

import java.util.List;

public class ImageService {

    private static ImageDao dao;

    public ImageService(ImageDaoImpl dao) {
        ImageService.dao = dao;
    }

    public void insertNewImage(Image image) {
        dao.save(image);
    }

    public List list(int productId) {
        return dao.getImagesByProductId(productId);
    }

    public Image getImage(int imageId) {
        return dao.getImagesById(imageId);
    }

    public void removeImageById(int imageId) {
        dao.removeImage(imageId);
    }
}
