package com.app.product.main.dao;


import com.app.product.main.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDao {

    private List<Product> Products = new ArrayList<>();

    public Product save(Product prod){
        Products.add(prod);
        return prod;
    }

    public Product getProductById(int id){
        return Products.stream().filter(product -> product.getId() == id).findFirst().get();
    }

    public List<Product> list(){
        return Products;
    }
}
