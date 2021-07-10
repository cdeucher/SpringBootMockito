package com.app.product.main.service;

import com.app.product.main.dao.ProductDao;
import com.app.product.main.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductDao dao;
    
    public ProductService(ProductDao dao) {
        this.dao = dao;
    }

    public Product insertNewProduct(Product prod){
        return dao.save(prod);
    }

    public Product getProductById(int productId) {
        return dao.getProductById(productId);
    }

    public List<Product> listProducts(){
        return dao.list();
    }

}
