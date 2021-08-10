package com.app.product.controller;

import com.app.product.common.Common;
import com.app.product.repository.ProductDao;
import com.app.product.controller.dto.ProductDto;
import com.app.product.entity.Product;
import com.app.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService prodService;

    public ProductController() {
        prodService = new ProductService();
    }

    @RequestMapping("/")
    public String cadNewProduct(){
        return "home";
    }

    @RequestMapping("/form")
    public String formProduct(Model model){
        model.addAttribute("productList", prodService.listProducts());
        return "prod/form";
    }

    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public String addProduct(Model model, @RequestParam String name, @RequestParam String description){
         int id = Common.generateRandomId();
         Product newProd = new Product().add(id, name, description);
         prodService.insertNewProduct(newProd);

        model.addAttribute("productList", ProductDto.toProduct(prodService.listProducts()));
         return "prod/form";
    }

    @ResponseBody
    @RequestMapping("/{id}")
    public ProductDto productById(@PathVariable int id){
        return ProductDto.toProduct(Arrays.asList(prodService.getProductById(id))).get(0);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Product> Product(){
        return prodService.listProducts();
    }

    @RequestMapping("/del/{productId}")
    public String removeProduct(Model model, @PathVariable int productId){
        prodService.removeProductById(productId);
        model.addAttribute("productList", prodService.listProducts());
        return "prod/form";
    }
}
