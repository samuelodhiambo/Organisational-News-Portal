package com.moringaschool.Interfaces;

import com.moringaschool.Models.Department;
import com.moringaschool.Models.User;
import org.sql2o.Connection;

import java.util.List;

public interface DepartmentInterface {
    void add(Department department);

    List<Department> findAll(Connection conn);

    Department findById(int id);

    void update(int id, Department department);

    void deleteById(int id);

    List<User> findUsersByDepartment(int department_id);

    void clearAll();
}
