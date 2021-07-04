package com.company.chat.main.service;

import com.company.chat.main.dao.ProductDao;
import com.company.chat.main.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private ProductDao dao;
    
    public ProductService(ProductDao dao) {
        this.dao = dao;
    }

    public Product insertNewProduct(String name, String description, String image){
        int id = getIdRandom();
        Product prod = new Product(id, name, description, image);
        dao.save(prod);
        return prod;
    }

    public Product getProductById(int productId) {
        return dao.getProductById(productId);
    }

    public List<Product> listProducts(){
        return dao.list();
    }

    public int getIdRandom() {
        return (int) ((Math.random() * (99999 - 1)) + 1);
    }
}
