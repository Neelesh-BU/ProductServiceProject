package com.project.firstspringapi.services;

import com.project.firstspringapi.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id);

    List<Product> getAllProducts();

    Product replaceProduct(Long id, Product product);
}
