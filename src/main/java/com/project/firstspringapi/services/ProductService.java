package com.project.firstspringapi.services;

import com.project.firstspringapi.exceptions.ProductNotFoundException;
import com.project.firstspringapi.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id) throws ProductNotFoundException;

    List<Product> getAllProducts();

    Product replaceProduct(Long id, Product product);
}
