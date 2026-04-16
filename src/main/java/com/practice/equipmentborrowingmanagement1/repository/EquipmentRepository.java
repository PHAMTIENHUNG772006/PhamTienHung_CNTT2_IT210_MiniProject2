package com.practice.equipmentborrowingmanagement1.repository;

import com.practice.equipmentborrowingmanagement1.model.entity.Equipment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EquipmentRepository {

    private static final List<Equipment> equipments = new ArrayList<>();
    private static int currentId = 1;

    static {
        Equipment e1 = new Equipment();
        e1.setId(currentId++);
        e1.setName("Máy 1");
        e1.setType("Device");
        e1.setStock(10);
        e1.setStatus(true);

        Equipment e2 = new Equipment();
        e2.setId(currentId++);
        e2.setName("Phòng 1");
        e2.setType("Room");
        e2.setStock(1);
        e2.setStatus(true);

        equipments.add(e1);
        equipments.add(e2);
    }


    // Lấy tất cả thiết bị
    public List<Equipment> findAll() {
        return equipments;
    }

    // Tìm theo id
    public Equipment findById(int id) {
        return equipments.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Thêm mới
    public void save(Equipment equipment) {
        equipment.setId(currentId++);
        equipments.add(equipment);
    }

    // Cập nhật
    public void update(Equipment updated) {
        for (int i = 0; i < equipments.size(); i++) {
            if (equipments.get(i).getId() == updated.getId()) {
                equipments.set(i, updated);
                break;
            }
        }
    }

    // Xóa
    public void delete(int id) {
        equipments.removeIf(e -> e.getId() == id);
    }

    // Kiểm tra tồn tại
    public boolean existsById(int id) {
        return equipments.stream().anyMatch(e -> e.getId() == id);
    }

    // Lấy số lượng tồn kho
    public int getAvailableQuantity(int id) {
        Equipment e = findById(id);
        return e != null ? e.getStock() : 0;
    }

    // Giảm số lượng
    public void decreaseQuantity(int id, int amount) {
        Equipment e = findById(id);
        if (e != null) {
            e.setStock(e.getStock() - amount);
        }
    }

    // Tăng số lượng
    public void increaseQuantity(int id, int amount) {
        Equipment e = findById(id);
        if (e != null) {
            e.setStock(e.getStock() + amount);
        }
    }
}

