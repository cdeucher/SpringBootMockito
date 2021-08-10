package com.app.product.repository;


import com.app.product.entity.Product;

import java.util.List;

public interface ProductDao {

    public Product save(Product prod);

    public Product getProductById(int id);

    public List<Product> list();

    public void removeProduct(int productId);
}
