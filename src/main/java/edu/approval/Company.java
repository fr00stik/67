package edu.approval;

import java.util.ArrayList;
import java.util.List;

public class Company {

    private Department root;
    private List<Employee> employees = new ArrayList<>();

    public Company(String name) {
        root = new Department(name, null);
    }

    public Department getRoot() {
        return root;
    }

    public Department addDepartment(Department parent, String name) {
        Department child = new Department(name, parent);
        parent.addChild(child);
        return child;
    }

    public Employee hire(Department department, int id, String name, Role... roles) {
        for (Employee e : employees) {
            if (e.getId() == id) {
                throw new CompanyException("Номер " + id + " уже занят");
            }
        }
        List<Role> roleList = new ArrayList<>();
        for (Role role : roles) {
            roleList.add(role);
        }
        Employee employee = new Employee(id, name, roleList);
        employees.add(employee);
        department.addEmployee(employee);
        return employee;
    }

    public void setHead(Department department, Employee employee) {
        department.setHead(employee);
    }

    public List<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }

    public List<Department> getDepartments() {
        return getDepartments(root);
    }

    public List<Department> getDepartments(Department from) {
        List<Department> result = new ArrayList<>();
        addDepartmentsToList(from, result);
        return result;
    }

    private void addDepartmentsToList(Department department, List<Department> result) {
        result.add(department);
        for (Department child : department.getChildren()) {
            addDepartmentsToList(child, result);
        }
    }

    public int getDepth(Department department) {
        int depth = 0;
        Department current = department;
        while (current.getParent() != null) {
            depth++;
            current = current.getParent();
        }
        return depth;
    }

    public Employee getManager(Employee employee) {
        Department department = employee.getDepartment();
        while (department != null) {
            Employee head = department.getHead();
            if (head != null && !head.equals(employee)) {
                return head;
            }
            department = department.getParent();
        }
        throw new CompanyException("У сотрудника " + employee + " нет руководителя");
    }

    public Employee getManagerUp(Employee employee, int levels) {
        if (levels < 1) {
            throw new IllegalArgumentException("Уровней должно быть хотя бы 1");
        }
        Employee current = employee;
        for (int i = 0; i < levels; i++) {
            try {
                current = getManager(current);
            } catch (CompanyException e) {
                throw new CompanyException("Выше сотрудника " + employee + " нет " + levels + " уровней");
            }
        }
        return current;
    }

    public List<Employee> getSubordinates(Employee employee) {
        List<Employee> result = new ArrayList<>();
        Department department = employee.getDepartment();
        if (department == null || !employee.equals(department.getHead())) {
            return result;
        }
        for (Department d : getDepartments(department)) {
            for (Employee e : d.getEmployees()) {
                if (!e.equals(employee)) {
                    result.add(e);
                }
            }
        }
        return result;
    }
}
