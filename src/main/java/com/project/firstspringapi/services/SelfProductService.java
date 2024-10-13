package com.project.firstspringapi.services;

import com.project.firstspringapi.exceptions.ProductNotFoundException;
import com.project.firstspringapi.models.Product;
import com.project.firstspringapi.reopsitories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductService")
//@Primary
public class SelfProductService implements ProductService {
    private ProductRepository productRepository;

    SelfProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        //Fetch the product with the give id from DB.
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public void deleteProduct() {

    }
}
