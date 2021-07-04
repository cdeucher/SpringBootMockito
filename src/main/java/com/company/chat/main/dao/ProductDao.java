package com.company.chat.main.dao;


import com.company.chat.main.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDao {

    List<Product> Products = new ArrayList<Product>();

    public Product save(Product prod){
        this.Products.add(prod);
        return prod;
    }

    public Product getProductById(int id){
        return findProductById(id);
    }

    public List<Product> list(){
        return Products;
    }

    public Product findProductById(int id){
        for (Product prod : this.Products){
            if( prod.getId() == id){
                return prod;
            }
        }
        return null;
    }
}
