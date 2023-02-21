package com.piatsevich.simplecrudapp.repository.jdbc;

import com.piatsevich.simplecrudapp.config.JdbcUtils;
import com.piatsevich.simplecrudapp.models.Department;
import com.piatsevich.simplecrudapp.repository.EmployeeRepository;
import com.piatsevich.simplecrudapp.models.Employee;
import org.springframework.stereotype.Component;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private static final String GET_ALL_EMPLOYEES = "SELECT employees.*, departments.* " +
            "FROM employees INNER JOIN departments ON employees.department_id = departments.department_id;";
    private static final String SAVE_EMPLOYEE =
            "INSERT INTO employees (username, email, department_id) VALUES(?,?,?)";
    private static final String GET_EMPLOYEE_BY_ID = "SELECT employees.*, departments.* " +
            "FROM employees INNER JOIN departments ON employees.department_id = departments.department_id WHERE employee_id=?;";
    private static final String UPDATE_EMPLOYEE =
            "UPDATE employees SET username=?, email=?, department_id=? WHERE employee_id=?";
    private static final String DELETE_EMPLOYEE_BY_ID =
            "DELETE FROM employees WHERE employee_id=?";

    @Override
    public Employee getById(Integer id) {
        Employee employee = null;
        try {
            PreparedStatement preparedStatement =
                    JdbcUtils.getConnection().prepareStatement(GET_EMPLOYEE_BY_ID);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            employee = new Employee();

            employee.setId(resultSet.getInt("employee_id"));
            employee.setUsername(resultSet.getString("username"));
            employee.setEmail(resultSet.getString("email"));

            Department department = new Department();
            department.setId(resultSet.getInt("department_id"));
            department.setName(resultSet.getString("dep_name"));
            department.setLocation(resultSet.getString("dep_location"));
            employee.setDepartment(department);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employee;
    }

    @Override
    public Employee save(Employee employee) {
        PreparedStatement stmt = null;
        try {
            stmt = JdbcUtils.getConnection().prepareStatement(SAVE_EMPLOYEE);
            stmt.setString(1, employee.getUsername());
            stmt.setString(2, employee.getEmail());
            stmt.setInt(3, employee.getDepartment().getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();

        try {
            PreparedStatement stmt =JdbcUtils.getConnection().prepareStatement(GET_ALL_EMPLOYEES);
            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("employee_id"));
                employee.setUsername(resultSet.getString("username"));
                employee.setEmail(resultSet.getString("email"));

                Department department = new Department();
                department.setId(resultSet.getInt("department_id"));
                department.setName(resultSet.getString("dep_name"));
                department.setLocation(resultSet.getString("dep_location"));
                employee.setDepartment(department);

                employees.add(employee);
            }
            return employees;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Employee update(Employee updatedEmployee) {
        try {
            PreparedStatement preparedStatement =
                    JdbcUtils.getConnection().prepareStatement(UPDATE_EMPLOYEE);

            preparedStatement.setString(1, updatedEmployee.getUsername());
            preparedStatement.setString(2, updatedEmployee.getEmail());
            preparedStatement.setInt(3,updatedEmployee.getDepartment().getId());
            preparedStatement.setInt(4,updatedEmployee.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updatedEmployee;
    }

    @Override
    public void deleteById(Integer id) {
        try {
            PreparedStatement preparedStatement =
                    JdbcUtils.getConnection().prepareStatement(DELETE_EMPLOYEE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
