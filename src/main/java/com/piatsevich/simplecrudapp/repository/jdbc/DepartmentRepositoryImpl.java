package com.piatsevich.simplecrudapp.repository.jdbc;

import com.piatsevich.simplecrudapp.config.JdbcUtils;
import com.piatsevich.simplecrudapp.models.Person;
import com.piatsevich.simplecrudapp.repository.DepartmentRepository;
import com.piatsevich.simplecrudapp.models.Department;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentRepositoryImpl implements DepartmentRepository {

    private Connection connection;

    public DepartmentRepositoryImpl() {
        this.connection = JdbcUtils.getConnection();
    }

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
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()) {
                Department department = new Department();

                department.setName(resultSet.getString("dep_name"));
                department.setLocation(resultSet.getString("dep_location"));


                departments.add(department);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return departments;
    }

    @Override
    public Department update(Department department) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }
}
