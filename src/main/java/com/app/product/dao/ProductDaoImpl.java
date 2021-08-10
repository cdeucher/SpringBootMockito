package com.app.product.dao;

import com.app.product.repository.ImageDao;
import com.app.product.repository.ProductDao;
import com.app.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {
    private List<Product> Products = new ArrayList<>();

    private ImageDao imageDao;

    @Override
    public Product save(Product prod){
        Products.add(prod);
        return prod;
    }

    @Override
    public Product getProductById(int id){
        return Products.stream().filter(product -> product.getId() == id ).map(p ->{
            p.setImagesList(imageDao.getImagesByProductId(p.getId()));
            return p;
        }).findFirst().orElse(null);
    }

    @Override
    public List<Product> list(){
        return Products;
    }

    @Override
    public void removeProduct(int productId) {
        Products.removeIf(product -> product.getId() == productId);
    }
}
