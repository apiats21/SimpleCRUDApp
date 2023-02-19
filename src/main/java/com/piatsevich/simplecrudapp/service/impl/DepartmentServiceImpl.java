package com.piatsevich.simplecrudapp.service.impl;

import com.piatsevich.simplecrudapp.models.Department;
import com.piatsevich.simplecrudapp.repository.DepartmentRepository;
import com.piatsevich.simplecrudapp.repository.jdbc.DepartmentRepositoryImpl;
import com.piatsevich.simplecrudapp.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository = new DepartmentRepositoryImpl();

    @Override
    public Department getById(Integer id) {
        return departmentRepository.getById(id);
    }

    @Override
    public Department create(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAll() {
        return departmentRepository.getAll();
    }

    @Override
    public Department update(Department department) {
        return departmentRepository.update(department);
    }

    @Override
    public void delete(Integer id) {
        departmentRepository.deleteById(id);

    }
}
