package com.app.product.dao;

import com.app.product.repository.ImageDao;
import com.app.product.entity.Image;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ImageDaoImpl implements ImageDao {

    private static final ArrayList<Image> imageList = new ArrayList<>();

    public Image save(Image img){
        imageList.add(img);
        return img;
    }

    public List<Image> getImagesByProductId(int productId){
        return imageList.stream().filter(image -> image.getProductId() == productId).collect(Collectors.toList());
    }

    public Image getImagesById(int imageId) {
        return imageList.stream().filter(image -> image.getId() == imageId).findFirst().orElse(null);
    }

    public void removeImage(int imageId){
        imageList.removeIf(image -> image.getId() == imageId);
    }
}
