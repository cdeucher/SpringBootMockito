package com.app.product.main.controller;

import com.app.product.main.dao.ProductDao;
import com.app.product.main.model.Product;
import com.app.product.main.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.app.product.main.common.Common.generateRandomId;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/product")
public class ProductController {

    private ProductDao dao = new ProductDao();
    private ProductService prodService;

    public ProductController() {
        prodService = new ProductService(dao);
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
         int id = generateRandomId();
         Product newProd = new Product().add(id, name, description);
         prodService.insertNewProduct(newProd);

        model.addAttribute("productList", prodService.listProducts());
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

    @RequestMapping("/del/{productId}")
    public String removeProduct(Model model, @PathVariable int productId){
        prodService.removeProductById(productId);
        model.addAttribute("productList", prodService.listProducts());
        return "prod/form";
    }
}
