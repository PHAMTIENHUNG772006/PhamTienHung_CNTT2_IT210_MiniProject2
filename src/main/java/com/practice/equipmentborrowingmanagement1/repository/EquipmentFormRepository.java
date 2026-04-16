package com.practice.equipmentborrowingmanagement1.repository;

import com.practice.equipmentborrowingmanagement1.model.entity.EquipmentForm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EquipmentFormRepository {

    private static final List<EquipmentForm> forms = new ArrayList<>();
    private static int currentId = 1;

    // Lấy tất cả
    public List<EquipmentForm> findAll() {
        return forms;
    }

    // Tìm theo id
    public EquipmentForm findById(int id) {
        return forms.stream()
                .filter(f -> f.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Lưu form
    public void save(EquipmentForm form) {
        form.setId(currentId++);
        forms.add(form);
    }

    // Cập nhật
    public void update(EquipmentForm updated) {
        for (int i = 0; i < forms.size(); i++) {
            if (forms.get(i).getId() == updated.getId()) {
                forms.set(i, updated);
                break;
            }
        }
    }

    // Xóa
    public void delete(int id) {
        forms.removeIf(f -> f.getId() == id);
    }

    // Tìm theo studentId
    public List<EquipmentForm> findByStudentId(String studentId) {
        return forms.stream()
                .filter(f -> f.getStudentId().equalsIgnoreCase(studentId))
                .toList();
    }

    // Tìm theo equipmentId
    public List<EquipmentForm> findByEquipmentId(int equipmentId) {
        return forms.stream()
                .filter(f -> f.getEquipment().getId() == equipmentId)
                .toList();
    }

    // Tìm theo status
    public List<EquipmentForm> findByStatus(boolean status) {
        return forms.stream()
                .filter(f -> f.isStatus() == status)
                .toList();
    }
}

