package com.app.rccl.shop.controller;

import com.app.rccl.shop.model.Product;
import com.app.rccl.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * get all products
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity(productService.getAllProducts(), HttpStatus.OK);
    }

    /**
     * get specific product
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
        return new ResponseEntity(productService.getProduct(id), HttpStatus.OK);
    }

    /**
     * search product
     * @param name
     * @return
     */
    @GetMapping("/search")
    public ResponseEntity<Product> searchProduct(@RequestParam("name") String name) {
        return new ResponseEntity(productService.searchProduct(name), HttpStatus.OK);
    }

}
