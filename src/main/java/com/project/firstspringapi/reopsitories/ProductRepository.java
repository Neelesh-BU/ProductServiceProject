package com.project.firstspringapi.reopsitories;

import com.project.firstspringapi.models.Product;
import com.project.firstspringapi.projections.ProductWithTitleAndDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    Optional<Product> findById(Long id);

    List<Product> findByTitle(String word);

    List<Product> findByTitleContains(String str);

    List<Product> findByTitleAndDescription(String title, String Description);

    Optional<Product> findByImage(String url);

    @Override
    void delete(Product entity);

    Product save(Product product);

    //HQL
    @Query("select p.title as title, p.description as description from Product p where p.id = :id")
    ProductWithTitleAndDescription somerandomQuery(@Param("id") Long id);

    //SQL --> nativ query
    @Query(value = "select title, description from product where p.id = :id", nativeQuery = true)
    ProductWithTitleAndDescription someOtherrandomQuery(@Param("id") Long id);


}
