package com.moringaschool.Interfaces;

import com.moringaschool.Models.Department;
import org.sql2o.Connection;

import java.util.List;

public interface DepartmentInterface {
    void add(Department department);

    List<Department> findAll(Connection conn);

    Department findById(int id);

    void update(int id);

    void deleteById(int id);

    void clearAll();
}