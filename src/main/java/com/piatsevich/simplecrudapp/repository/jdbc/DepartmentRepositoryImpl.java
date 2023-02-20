package com.piatsevich.simplecrudapp.repository.jdbc;

import com.piatsevich.simplecrudapp.config.JdbcUtils;
import com.piatsevich.simplecrudapp.models.Person;
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
    private static final String GET_DEPARTMENT_BY_ID = "SELECT * FROM departments WHERE department_id=?";
    private static final String UPDATE_DEPARTMENT = "UPDATE departments SET dep_name=?, dep_location=? WHERE department_id=?";
    private static final String DELETE_DEPARTMENT_BY_ID = "DELETE FROM departments WHERE department_id=?";

    @Override
    public Department getById(Integer id) {
        Department department = null;
        try {
            PreparedStatement preparedStatement =
                    JdbcUtils.getConnection().prepareStatement(GET_DEPARTMENT_BY_ID);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            department = new Department();

            department.setId(resultSet.getInt("department_id"));
            department.setName(resultSet.getString("dep_name"));
            department.setLocation(resultSet.getString("dep_location"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return department;
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
                department.setId(rs.getInt("department_id"));
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
    public Department update(Department updatedDepartment) {
        try {
            PreparedStatement preparedStatement =
                    JdbcUtils.getConnection().prepareStatement(UPDATE_DEPARTMENT);

            preparedStatement.setString(1, updatedDepartment.getName());
            preparedStatement.setString(2, updatedDepartment.getLocation());
            preparedStatement.setInt(3,updatedDepartment.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updatedDepartment;
    }

    @Override
    public void deleteById(Integer id) {
        try {
            PreparedStatement preparedStatement =
                    JdbcUtils.getConnection().prepareStatement(DELETE_DEPARTMENT_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
