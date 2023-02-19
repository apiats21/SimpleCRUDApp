package com.piatsevich.simplecrudapp.service.impl;

import com.piatsevich.simplecrudapp.models.Department;
import com.piatsevich.simplecrudapp.service.DepartmentService;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentService departmentService = new DepartmentServiceImpl();

    @Override
    public Department getById(Integer id) {
        return departmentService.getById(id);
    }

    @Override
    public Department create(Department department) {
        return departmentService.create(department);
    }

    @Override
    public List<Department> getAll() {
        return departmentService.getAll();
    }

    @Override
    public Department update(Department department) {
        return departmentService.update(department);
    }

    @Override
    public void delete(Integer id) {
        departmentService.delete(id);

    }
}
