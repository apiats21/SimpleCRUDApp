package com.piatsevich.simplecrudapp.models;

import java.util.List;

public class Project {
    private Integer id;
    private String name;
    List<Employee> employee;
    Client client;

    public Project() {

    }

    public Project(Integer id, String name, List<Employee> employee, Client client) {
        this.id = id;
        this.name = name;
        this.employee = employee;
        this.client = client;
    }

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
