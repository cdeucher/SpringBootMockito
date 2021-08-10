package com.app.product.repository;

import com.app.product.entity.Image;

import java.util.List;

public interface ImageDao {

    public Image save(Image img);

    public List<Image> getImagesByProductId(int productId);

    public Image getImagesById(int imageId);

    public void removeImage(int imageId);
}
