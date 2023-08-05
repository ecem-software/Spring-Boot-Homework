package com.example.erp_system.controller;

import com.example.erp_system.entity.ProductEntity;
import com.example.erp_system.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("Product")

public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("create")
    public ResponseEntity<Boolean> create(@RequestBody ProductEntity product) {
        return new ResponseEntity<>(productService.createProduct(product.getName(), product.getStockAmount(), product.getProductPrice(), product.getIsKdvApplied(),
                product.getKdv()), HttpStatus.CREATED);
    }

    @GetMapping("get/{uuid}")
    public ResponseEntity<ProductEntity> getByUuid(@PathVariable UUID uuid) {
        return new ResponseEntity<>(productService.getByUuid(uuid), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductEntity>> getAll() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("getByName/{name}")
    public ResponseEntity<List<ProductEntity>> getByName(@PathVariable String name) {
        return new ResponseEntity<>(productService.getAllByNameContainsIgnoreCase(name), HttpStatus.OK);
    }

    @Modifying
    @Transactional
    @DeleteMapping("delete/{uuid}")
    public ResponseEntity<Boolean> deleteByUuid(@PathVariable UUID uuid) {
        return new ResponseEntity<>(productService.productDeletion(uuid), HttpStatus.OK);
    }

    @Modifying
    @Transactional
    @PutMapping("update/{uuid}")
    public ResponseEntity<Boolean> updateByUuid(@PathVariable UUID uuid, @RequestBody ProductEntity product) {
        return new ResponseEntity<>(productService.updateProduct(uuid, product), HttpStatus.OK);
    }


}
