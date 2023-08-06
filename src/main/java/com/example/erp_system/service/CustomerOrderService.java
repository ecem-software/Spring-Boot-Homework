package com.example.erp_system.service;

import com.example.erp_system.entity.CustomerEntity;
import com.example.erp_system.entity.CustomerOrderEntity;
import com.example.erp_system.entity.ProductEntity;
import com.example.erp_system.repository.CustomerOrderRepository;
import com.example.erp_system.repository.ProductRepository;
import com.example.erp_system.util.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerOrderService {
    @Autowired
    CustomerOrderRepository customerOrderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;

    public boolean createCustomerOrder(CustomerEntity customer, List<ProductEntity> productEntityList) {
        if (customer == null || productEntityList == null) {
            return false;
        } else {
            CustomerOrderEntity customerOrderEntity = new CustomerOrderEntity();
            customerOrderEntity.setCustomer(customer);
            customerOrderEntity.setProductList(productEntityList);
            customerOrderRepository.save(customerOrderEntity);
            return true;
        }
    }

    public List<CustomerOrderEntity> getAll() {
        return customerOrderRepository.findAll();
    }


    public CustomerOrderEntity getByUuid(UUID uuid) {
        return customerOrderRepository.findByUuid(uuid);
    }

    public List<CustomerOrderEntity> getByStatus(StatusEnum statusEnum) {

        return customerOrderRepository.getCustomerOrderEntitiesByStatus(statusEnum);
    }

    public boolean deleteCustomerOrderEntity(UUID uuid) {
        if (uuid == null)
            return false;
        else {
            customerOrderRepository.deleteByUuid(uuid);
            return true;
        }
    }

    public boolean addProductToCustomerOrder(UUID orderUuid, UUID productUuid) {
        if (orderUuid == null || productUuid == null || productRepository.findByUuid(productUuid).getOrderCount() == 0)
            return false;
        else {
            CustomerOrderEntity existingCustomerOrder = customerOrderRepository.findByUuid(orderUuid);
            productRepository.findByUuid(productUuid).setOrderCount(1);
            if (existingCustomerOrder == null)
                return false;
            ProductEntity product = productRepository.findByUuid(productUuid);
            if (existingCustomerOrder.getProductList().contains(product)) {
                for (ProductEntity productEntity : existingCustomerOrder.getProductList()) {
                    if (productEntity.getUuid().equals(productUuid))
                        productEntity.setOrderCount(productEntity.getOrderCount() + 1);
                }
            } else
                existingCustomerOrder.getProductList().add(product);
            customerOrderRepository.save(existingCustomerOrder);
            return true;
        }
    }

    public boolean updateCustomerOrderEntity(UUID uuid, CustomerOrderEntity customerOrderEntity) {
        if (uuid == null || customerOrderEntity == null)
            return false;
        else {
            CustomerOrderEntity existingCustomerOrder = customerOrderRepository.findByUuid(uuid);
            if (existingCustomerOrder == null)
                return false;
            existingCustomerOrder.setCustomer(customerOrderEntity.getCustomer());
            existingCustomerOrder.setProductList(customerOrderEntity.getProductList());
            existingCustomerOrder.setStatus(customerOrderEntity.getStatus());
            customerOrderRepository.save(existingCustomerOrder);
            return true;
        }
    }

    public void calculateTotalPrice(UUID uuid) {
        CustomerOrderEntity customerOrderEntity = customerOrderRepository.findByUuid(uuid);
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (ProductEntity productEntity : customerOrderEntity.getProductList()) {
            BigDecimal productPrice = productEntity.getProductPrice().multiply(BigDecimal.valueOf(productEntity.getOrderCount()));
            totalPrice = totalPrice.add(productPrice);
        }
        customerOrderEntity.setTotalPrice(totalPrice);
        customerOrderRepository.save(customerOrderEntity);
    }

    public CustomerOrderEntity controlOrderStatus(UUID customerOrderUuid) {
        CustomerOrderEntity customerOrder = customerOrderRepository.findByUuid(customerOrderUuid);
        if (customerOrder == null)
            return null;
        if (customerOrder.getStatus() == StatusEnum.WAITING) {
            for (ProductEntity productEntity : customerOrder.getProductList()) {
                if (productEntity.getStockAmount() < productEntity.getOrderCount()) {
                    customerOrder.setStatus(StatusEnum.REJECTED);
                    customerOrderRepository.save(customerOrder);
                    return customerOrder;
                } else {
                    customerOrder.setStatus(StatusEnum.APPROVED);
                    productEntity.setStockAmount(productEntity.getStockAmount() - productEntity.getOrderCount());
                    customerOrderRepository.save(customerOrder);
                }
            }
        }
        calculateTotalPrice(customerOrderRepository.findByUuid(customerOrderUuid).getUuid());
        return customerOrder;
    }
}
