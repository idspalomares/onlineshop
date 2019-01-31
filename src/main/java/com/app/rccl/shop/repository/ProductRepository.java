package com.app.rccl.shop.repository;

import com.app.rccl.shop.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends ShopRepository<Product> {

    @Query("select p from Product p where lower(p.name) like lower(concat('%', :name, '%'))")
    List<Product> searchProduct(@Param("name") String name);

}
