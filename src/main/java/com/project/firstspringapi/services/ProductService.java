package com.project.firstspringapi.services;

import com.project.firstspringapi.exceptions.ProductNotFoundException;
import com.project.firstspringapi.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id) throws ProductNotFoundException;

    List<Product> getAllProducts();

    Product updateProduct(Long id, Product product);

    Product replaceProduct(Long id, Product product);

    Product createProduct(Product product);

    void deleteProduct();

}
