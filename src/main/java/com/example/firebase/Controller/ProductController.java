package com.example.firebase.Controller;

import com.example.firebase.Entity.Product;
import com.example.firebase.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;


    @PostMapping("/products")
    public String saveProduct(@RequestBody Product product) throws ExecutionException, InterruptedException {
        return productService.saveProduct(product);
    }

    @GetMapping("/products/{name}")
    public Product getProduct(@PathVariable String  name) throws ExecutionException, InterruptedException {
        return productService.getProductDetails(name);
    }
    @GetMapping("/products")
    public List<Product> getProducts() throws ExecutionException, InterruptedException {
        return productService.getAll();
    }

    @PutMapping("/products")
    public String update(@RequestBody Product product) throws ExecutionException, InterruptedException {
        return productService.update(product);
    }

    @DeleteMapping("/products/{name}")
    public String delete(@PathVariable String  name) throws ExecutionException, InterruptedException {
        return productService.delete(name);
    }



}
