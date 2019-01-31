package com.app.rccl.shop.service;

import com.app.rccl.shop.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProduct(Long id);

    List<Product> searchProduct(String name);
}
