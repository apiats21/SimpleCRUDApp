package com.piatsevich.simplecrudapp.repository.jdbc;

import com.piatsevich.simplecrudapp.config.JdbcUtils;
import com.piatsevich.simplecrudapp.models.Client;
import com.piatsevich.simplecrudapp.models.Employee;
import com.piatsevich.simplecrudapp.repository.ProjectRepository;
import com.piatsevich.simplecrudapp.models.Project;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepositoryImpl implements ProjectRepository {

    private static final String GET_ALL_PROJECTS = "SELECT * FROM projects";

    private static final String SAVE_PROJECT =
            "INSERT INTO projects (project_name, client_id) VALUES(?,?)";

    private static final String GET_PROJECT_BY_ID = "SELECT projects.*, employees.username, clients.client_name, clients.client_email " +
            "FROM projects " +
            "LEFT JOIN employee_projects ON projects.project_id = employee_projects.project_id " +
            "LEFT JOIN employees ON employees.employee_id = employee_projects.employee_id " +
            "INNER JOIN clients ON projects.client_id = clients.client_id WHERE projects.project_id=?";

    private static final String GET_EMPLOYEES_ON_PROJECT = "SELECT employees.employee_id, employees.username FROM employees " +
            "JOIN employee_projects ON employees.employee_id = employee_projects.employee_id " +
            "JOIN projects ON projects.project_id = employee_projects.project_id WHERE projects.project_id =?";

    private static final String UPDATE_PROJECT =
            "UPDATE projects SET project_name=?, client_id=? WHERE project_id=?";
    private static final String DELETE_PROJECT_BY_ID =
            "DELETE FROM projects WHERE project_id=?";

    @Override
    public Project getById(Integer id) {
        Project project = null;
        try {
            PreparedStatement preparedStatement =
                    JdbcUtils.getConnection().prepareStatement(GET_PROJECT_BY_ID);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            project = new Project();

            project.setId(resultSet.getInt("project_id"));
            project.setName(resultSet.getString("project_name"));

            Client client = new Client();
            client.setId(resultSet.getInt("client_id"));
            client.setName(resultSet.getString("client_name"));
            client.setEmail(resultSet.getString("client_email"));

            project.setClient(client);

            List<Employee> employees = new ArrayList<>();

            PreparedStatement stmt =
                    JdbcUtils.getConnection().prepareStatement(GET_EMPLOYEES_ON_PROJECT);

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Employee employee  = new Employee();
                employee.setId(rs.getInt("employee_id"));
                employee.setUsername(rs.getString("username"));
                employees.add(employee);
            }

            project.setEmployee(employees);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return project;
    }


    @Override
    public Project save(Project project) {
        PreparedStatement stmt = null;
        try {
            stmt = JdbcUtils.getConnection().prepareStatement(SAVE_PROJECT);
            stmt.setString(1, project.getName());
            stmt.setInt(2, project.getClient().getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return project;
    }


    @Override
    public List<Project> getAll() {
        List<Project> projects = new ArrayList<>();

        try {
            PreparedStatement stmt =JdbcUtils.getConnection().prepareStatement(GET_ALL_PROJECTS);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Project project = new Project();
                project.setId(rs.getInt("project_id"));
                project.setName(rs.getString("project_name"));

                projects.add(project);
            }
            return projects;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Project update(Project updatedProject) {
        try {
            PreparedStatement preparedStatement =
                    JdbcUtils.getConnection().prepareStatement(UPDATE_PROJECT);

            preparedStatement.setString(1, updatedProject.getName());
            preparedStatement.setInt(2,updatedProject.getClient().getId());
            preparedStatement.setInt(3, updatedProject.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updatedProject;
    }

    @Override
    public void deleteById(Integer id) {
        try {
            PreparedStatement preparedStatement =
                    JdbcUtils.getConnection().prepareStatement(DELETE_PROJECT_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
