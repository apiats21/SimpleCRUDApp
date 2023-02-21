package com.piatsevich.simplecrudapp.models;

public class Employee {
    private Integer id;
    private String username;
    private String email;
    private Department department;

    public Employee() {
    }

    public Employee(Integer id, String username, String email, Department department) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
