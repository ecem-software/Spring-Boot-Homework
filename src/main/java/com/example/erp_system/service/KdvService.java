package com.example.erp_system.service;

import com.example.erp_system.entity.KdvEntity;
import com.example.erp_system.repository.KdvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class KdvService {
    @Autowired
    KdvRepository kdvRepository;

    public boolean createKdv(String name, BigDecimal percent) {
        if (name == null || percent == null) {
            return false;
        } else {
            KdvEntity kdvEntity = new KdvEntity();
            kdvEntity.setName(name);
            kdvEntity.setPercent(percent);
            kdvRepository.save(kdvEntity);
            return true;
        }
    }

    public List<KdvEntity> getAll() {
        return kdvRepository.findAll();
    }

    public List<KdvEntity> getAllByNameIContains(String name) {
        return kdvRepository.findAllByNameContainsIgnoreCase(name);
    }

    public boolean KdvUpdate(UUID uuid, KdvEntity kdvEntity) {
        if (uuid == null || kdvEntity == null)
            return false;
        else {
            KdvEntity existingKdv = kdvRepository.findByUuid(uuid);
            if (existingKdv == null)
                return false;
            existingKdv.setName(kdvEntity.getName());
            existingKdv.setPercent(kdvEntity.getPercent());
            kdvRepository.save(existingKdv);
            return true;
        }
    }

    public boolean Kdvdeletion(UUID uuid) {
        if (uuid == null) {
            return false;
        } else {
            kdvRepository.deleteByUuid(uuid);
            return true;
        }
    }
}
