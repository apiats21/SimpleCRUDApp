package com.piatsevich.simplecrudapp.repository.jdbc;

import com.piatsevich.simplecrudapp.config.JdbcUtils;
import com.piatsevich.simplecrudapp.repository.DepartmentRepository;
import com.piatsevich.simplecrudapp.models.Department;
import org.springframework.stereotype.Component;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentRepositoryImpl implements DepartmentRepository {

    @Override
    public Department getById(Integer integer) {
        return null;
    }

    @Override
    public Department save(Department department) {
        return null;
    }

    @Override
    public List<Department> getAll() {
        List<Department> departments = new ArrayList<>();

        String SQL = "SELECT * FROM departments";

        try {
            PreparedStatement preparedStatement =JdbcUtils.getConnection().prepareStatement(SQL); // old way
            ResultSet rs = preparedStatement.executeQuery(); // old way

            while(rs.next()) {
                Department department = new Department();
                department.setName(rs.getString("dep_name"));
                department.setLocation(rs.getString("dep_location"));
                departments.add(department);
            }
            return departments;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Department update(Department department) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }
}
