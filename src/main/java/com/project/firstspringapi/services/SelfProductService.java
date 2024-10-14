package com.project.firstspringapi.services;

import com.project.firstspringapi.exceptions.ProductNotFoundException;
import com.project.firstspringapi.models.Category;
import com.project.firstspringapi.models.Product;
import com.project.firstspringapi.reopsitories.CategoryRepository;
import com.project.firstspringapi.reopsitories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
//@Primary
public class SelfProductService implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    SelfProductService(ProductRepository productRepository,
                       CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        //Fetch the product with the give id from DB.
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException(id, "Product NOT Found");
        }

        return optionalProduct.get();
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

        Category category = product.getCategory();

        if (category.getId() == null) {
            //we need to save the category
            Category savedCategory = categoryRepository.save(category);
            product.setCategory(savedCategory);
        } else {
            //we should check if the category id is valid or not.
        }

        Product savedProduct = productRepository.save(product);
        Optional<Category> optionalCategory = categoryRepository.findById(savedProduct.getCategory().getId());
        Category category1 = optionalCategory.get();
        savedProduct.setCategory(category1);
        return savedProduct;
    }

    @Override
    public void deleteProduct() {

    }
}
