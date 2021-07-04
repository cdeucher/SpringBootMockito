package com.company.chat.main.controller;

import com.company.chat.main.dao.ProductDao;
import com.company.chat.main.model.Product;
import com.company.chat.main.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

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
         prodService.insertNewProduct(name, description, image);
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
