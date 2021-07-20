package com.app.product.main.service;

import com.app.product.main.dao.ImageDao;
import com.app.product.main.model.Image;

import java.util.List;

public class ImageService {

    private static ImageDao dao;

    public ImageService(ImageDao dao) {
        ImageService.dao = dao;
    }

    public void insertNewImage(Image image) {
        dao.save(image);
    }

    public List list(int productId) {
        return ImageDao.getImagesByProductId(productId);
    }

    public Image getImage(int imageId) {
        return dao.getImagesById(imageId);
    }

    public void removeImageById(int imageId) {
        dao.removeImage(imageId);
    }
}
