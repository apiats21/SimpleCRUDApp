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

    private static final String GET_ALL_DEPARTMENTS = "SELECT * FROM departments";
    private static final String SAVE_DEPARTMENT =  "INSERT INTO departments (dep_name, dep_location) VALUES(?,?)";

    @Override
    public Department getById(Integer integer) {
        return null;
    }

    @Override
    public Department save(Department department) {
        PreparedStatement stmt = null;
        try {
            stmt = JdbcUtils.getConnection().prepareStatement(SAVE_DEPARTMENT);
            stmt.setString(1, department.getName());
            stmt.setString(2, department.getLocation());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return department;
    }

    @Override
    public List<Department> getAll() {
        List<Department> departments = new ArrayList<>();

        try {
            PreparedStatement stmt =JdbcUtils.getConnection().prepareStatement(GET_ALL_DEPARTMENTS);
            ResultSet rs = stmt.executeQuery();

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
