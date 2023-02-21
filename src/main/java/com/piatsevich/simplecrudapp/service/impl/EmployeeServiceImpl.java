package com.piatsevich.simplecrudapp.service.impl;

import com.piatsevich.simplecrudapp.models.Employee;
import com.piatsevich.simplecrudapp.repository.EmployeeRepository;
import com.piatsevich.simplecrudapp.repository.jdbc.EmployeeRepositoryImpl;
import com.piatsevich.simplecrudapp.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();

    @Override
    public Employee getById(Integer id) {
        return employeeRepository.getById(id);
    }

    @Override
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.getAll();
    }

    @Override
    public Employee update(Employee employee) {
        return employeeRepository.update(employee);
    }

    @Override
    public void delete(Integer id) {
        employeeRepository.deleteById(id);
    }
}
