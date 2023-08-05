package com.example.erp_system.repository;

import com.example.erp_system.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    void deleteByUuid(UUID uuid);
    List<ProductEntity> findAllByNameContainsIgnoreCase(String name);

    ProductEntity findByUuid(UUID uuid);


}
