package com.example.erp_system.service;

import com.example.erp_system.dto.CustomerOrderDTO;
import com.example.erp_system.dto.KdvDTO;
import com.example.erp_system.entity.KdvEntity;
import com.example.erp_system.entity.ProductEntity;
import com.example.erp_system.repository.KdvRepository;
import com.example.erp_system.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    KdvRepository kdvRepository;

    public boolean createProduct(String name, Integer stockAmount, BigDecimal productPrice, boolean isKdvApplied, KdvEntity kdv) {
        if (name == null || stockAmount == null || productPrice == null || kdv == null) {
            return false;

        } else {
            ProductEntity productEntity = new ProductEntity();
            productEntity.setName(name);
            productEntity.setStockAmount(stockAmount);
            productEntity.setProductPrice(productPrice);
            productEntity.setIsKdvApplied(isKdvApplied);
            productEntity.setOrderCount(1);
            productEntity.setKdv(kdvRepository.findByUuid(kdv.getUuid()));
            kdvCorrectPrice(productEntity);
            productRepository.save(productEntity);
            return true;
        }
    }

    public List<ProductEntity> getAll() {
        return productRepository.findAll();
    }

    public boolean productDeletion(UUID uuid) {
        if (uuid == null)
            return false;
        else {
            productRepository.deleteByUuid(uuid);
            return true;
        }
    }

    public ProductEntity getByUuid(UUID uuid) {
        return productRepository.findByUuid(uuid);
    }

    public List<ProductEntity> getAllByNameContainsIgnoreCase(String name) {
        return productRepository.findAllByNameContainsIgnoreCase(name);
    }

    public boolean updateProduct(UUID uuid, ProductEntity productEntity) {
        if (uuid == null || productEntity == null) {
            return false;
        } else {
            ProductEntity existingProduct = productRepository.findByUuid(uuid);
            if (existingProduct == null) {
                return false;
            } else {
                existingProduct.setName(productEntity.getName());
                existingProduct.setStockAmount(productEntity.getStockAmount());
                existingProduct.setProductPrice(productEntity.getProductPrice());
                existingProduct.setKdv(productEntity.getKdv());
                existingProduct.setNonKdvAppliedPrice(productEntity.getNonKdvAppliedPrice());
                existingProduct.setIsKdvApplied(productEntity.getIsKdvApplied());
                kdvCorrectPrice(existingProduct);
                productRepository.save(existingProduct);
                return true;
            }
        }

        }

      public void kdvCorrectPrice(ProductEntity product){
        BigDecimal kdv= product.getKdv().getPercent();
        BigDecimal price= product.getProductPrice();
        if(!product.getIsKdvApplied()){
            product.setNonKdvAppliedPrice(price);
            BigDecimal kdvPrice= (price.multiply(kdv).divide(new BigDecimal(100)));
            BigDecimal totalPrice=price.add(kdvPrice);
            product.setProductPrice(totalPrice);
        }else{
            BigDecimal kdvPrice= price.multiply((kdv.divide(BigDecimal.valueOf(100))));
            BigDecimal nonKdvPrice= price.subtract(kdvPrice);
            product.setNonKdvAppliedPrice(nonKdvPrice);
        }
      }


}
