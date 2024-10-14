package com.project.firstspringapi;

import com.project.firstspringapi.models.Category;
import com.project.firstspringapi.models.Product;
import com.project.firstspringapi.projections.ProductWithTitleAndDescription;
import com.project.firstspringapi.reopsitories.CategoryRepository;
import com.project.firstspringapi.reopsitories.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class FirstSpringApiApplicationTests {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

	@Test
	@Transactional
	public void testTC(){
		Optional<Category> optionalCategory = categoryRepository.findById(1L);

		Category category = optionalCategory.get();

		System.out.println("Fetching products related to the above category");
		List<Product> products = category.getProducts();
		// System.out.println(products.get(0).getTitle());

		ProductWithTitleAndDescription productWithTitleAndDescription = productRepository.somerandomQuery(1L);

		System.out.println(productWithTitleAndDescription.getTitle());
		System.out.println(productWithTitleAndDescription.getDescription());

		ProductWithTitleAndDescription product = productRepository.somerandomQuery(2L);
		System.out.println(product.getTitle());
		System.out.println(product.getDescription());
		System.out.println("DEBUG");
	}

}
