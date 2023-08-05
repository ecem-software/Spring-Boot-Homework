package com.example.erp_system.service;

import com.example.erp_system.dto.CustomerOrderDTO;
import com.example.erp_system.entity.CustomerOrderEntity;
import com.example.erp_system.entity.OrderBillEntity;
import com.example.erp_system.repository.OrderBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class OrderBillService {
    @Autowired
    OrderBillRepository orderBillRepository;

    public boolean createOrderBill(CustomerOrderEntity order, BigDecimal totalPrice) {
        if (order == null || totalPrice == null)
            return false;
        else {
            OrderBillEntity orderBillEntity = new OrderBillEntity();
            orderBillEntity.setOrder(order);
            orderBillEntity.setTotalPrice(totalPrice);
            orderBillRepository.save(orderBillEntity);
            return true;
        }
    }

    public List<OrderBillEntity> getAll() {
        return orderBillRepository.findAll();
    }

    public OrderBillEntity getByUuid(UUID uuid) {
        return orderBillRepository.findByUuid(uuid);
    }

    public boolean deleteOrderBill(UUID uuid) {
        if (uuid == null)
            return false;
        else {
            orderBillRepository.deleteByUuid(uuid);
            return true;
        }
    }

    public boolean updateOrderBill(UUID uuid, OrderBillEntity orderBillEntity) {
        if (uuid == null || orderBillEntity == null)
            return false;
        else {
            OrderBillEntity existingOrderBill = orderBillRepository.findByUuid(uuid);
            if (existingOrderBill == null)
                return false;
            existingOrderBill.setOrder(orderBillEntity.getOrder());
            existingOrderBill.setTotalPrice(orderBillEntity.getTotalPrice());
            orderBillRepository.save(existingOrderBill);
            return true;
        }
    }

}
