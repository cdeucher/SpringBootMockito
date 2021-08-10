package com.app.product.service;

import com.app.product.repository.ProductDao;
import com.app.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    @Autowired
    private ProductDao dao;

    public void insertNewProduct(Product prod){
        if ( Objects.isNull(dao.getProductById(prod.getId())) ) {
            dao.save(prod);
        }
    }

    public Product getProductById(int productId) {
        return dao.getProductById(productId);
    }

    public List<Product> listProducts(){
        return dao.list();
    }

    public void removeProductById(int productId) {
        dao.removeProduct(productId);
    }
}
