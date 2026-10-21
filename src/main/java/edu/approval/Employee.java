package edu.approval;

import java.util.ArrayList;
import java.util.List;

public class Employee {

    private int id;
    private String name;
    private List<Role> roles = new ArrayList<>();
    private Department department;

    Employee(int id, String name, List<Role> roles) {
        if (id <= 0) {
            throw new IllegalArgumentException("Номер должен быть больше нуля");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        this.id = id;
        this.name = name;
        this.roles.add(Role.EMPLOYEE);
        for (Role role : roles) {
            if (!this.roles.contains(role)) {
                this.roles.add(role);
            }
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Role> getRoles() {
        return new ArrayList<>(roles);
    }

    public boolean hasRole(Role role) {
        return roles.contains(role);
    }

    public Department getDepartment() {
        return department;
    }

    void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Employee)) {
            return false;
        }
        Employee that = (Employee) other;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return name + " #" + id;
    }
}
