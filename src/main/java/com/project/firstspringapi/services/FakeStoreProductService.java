package com.project.firstspringapi.services;

import com.project.firstspringapi.dtos.FakeStoreProductDto;
import com.project.firstspringapi.exceptions.ProductNotFoundException;
import com.project.firstspringapi.models.Category;
import com.project.firstspringapi.models.Product;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {
    private final HttpServletResponse httpServletResponse;
    private RestTemplate restTemplate;

    FakeStoreProductService(RestTemplate restTemplate, HttpServletResponse httpServletResponse) {
        this.restTemplate =restTemplate;
        this.httpServletResponse = httpServletResponse;
    }

    private Product convertFakeStoreDTOToProduct(FakeStoreProductDto dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setImage(dto.getImage());


        Category category = new Category();
        category.setTitle(dto.getCategory());
        product.setCategory(category);

        return product;
    }


    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        //Call FakeStore API here to get the Product with given id.
        FakeStoreProductDto fakeStoreProductDto =
                restTemplate.getForObject("https://fakestoreapi.com/products/" + id,
                        FakeStoreProductDto.class);

        //1st param -> URL
        //2st param -> Response

        if (fakeStoreProductDto == null) {
            throw new ProductNotFoundException(id, "Product with id" + id + "not found");
            //return null;
        }


        //Convert FakeStoreDTO into Product object.
        return convertFakeStoreDTOToProduct(fakeStoreProductDto);


    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos =
                restTemplate.getForObject("https://fakestoreapi.com/products",
                        FakeStoreProductDto[].class);

        //convert List of FakeStoreProductsDtos to List of Products
        List<Product> response = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            response.add(convertFakeStoreDTOToProduct(fakeStoreProductDto));
        }

        return response;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setImage(product.getImage());
        fakeStoreProductDto.setDescription(product.getDescription());


        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDto.class,
                        restTemplate.getMessageConverters());
        FakeStoreProductDto response =
                restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);

        return convertFakeStoreDTOToProduct(response);
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public void deleteProduct() {

    }
}
