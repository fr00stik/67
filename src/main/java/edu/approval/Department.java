package edu.approval;

import java.util.ArrayList;
import java.util.List;

public class Department {

    private String name;
    private Department parent;
    private List<Department> children = new ArrayList<>();
    private List<Employee> employees = new ArrayList<>();
    private Employee head;

    Department(String name, Department parent) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название не может быть пустым");
        }
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public Department getParent() {
        return parent;
    }

    public List<Department> getChildren() {
        return new ArrayList<>(children);
    }

    public List<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }

    public Employee getHead() {
        return head;
    }

    void addChild(Department child) {
        children.add(child);
    }

    void addEmployee(Employee employee) {
        employees.add(employee);
        employee.setDepartment(this);
    }

    void setHead(Employee employee) {
        if (!employees.contains(employee)) {
            throw new IllegalArgumentException("Руководителем может быть только сотрудник этого отдела");
        }
        this.head = employee;
    }

    @Override
    public String toString() {
        return name;
    }
}
