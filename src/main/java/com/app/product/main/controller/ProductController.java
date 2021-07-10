package com.app.product.main.controller;

import com.app.product.main.dao.ProductDao;
import com.app.product.main.model.Product;
import com.app.product.main.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.app.product.main.common.Common.generateRandomProductId;

@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductDao dao;
    private ProductService prodService;

    public ProductController() {
        this.dao = new ProductDao();
        this.prodService = new ProductService(dao);
    }

    @RequestMapping("/")
    public String cadNewProduct(){
        return "home";
    }

    @RequestMapping("/form")
    public String formProduct(){
        return "prod/form";
    }

    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public String addProduct(@RequestParam String name, @RequestParam String description, @RequestParam String image){
         int id = generateRandomProductId();
         Product newProd = new Product(id, name, description, image);

         prodService.insertNewProduct(newProd);
         return "prod/form";
    }

    @ResponseBody
    @RequestMapping("/{id}")
    public Product productById(@PathVariable int id){
        return prodService.getProductById(id);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Product> Product(){
        return prodService.listProducts();
    }


}
