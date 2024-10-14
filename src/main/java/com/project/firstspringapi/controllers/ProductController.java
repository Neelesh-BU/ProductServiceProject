package com.project.firstspringapi.controllers;

import com.project.firstspringapi.exceptions.ProductNotFoundException;
import com.project.firstspringapi.models.Product;
import com.project.firstspringapi.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//localhost:8080/products
@RestController //This controller is going to REST API's
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(@Qualifier("selfProductService") ProductService productService) {
        this.productService = productService;
    }


    //localhost:8080/products/1
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        Product product = productService.getProductById(id);
        ResponseEntity<Product> responseEntity;
//        if (product == null) {
//            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            return responseEntity;
//        }

        responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        return responseEntity;


        //throw new RuntimeException("Something went wrong");
    }

    //localhost:8080/products
    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    //createProduct
    //deleteProduct
    //updateProduct -> Partial Update (PATCH)
    //replaceProduct -> Replace (PUT)

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id,@RequestBody Product product) {
        return productService.replaceProduct(id, product);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }
}
