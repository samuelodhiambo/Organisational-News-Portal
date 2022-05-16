package com.moringaschool.Dao;

import com.moringaschool.Interfaces.DepartmentInterface;
import com.moringaschool.Models.Department;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class DepartmentDao implements DepartmentInterface {
    private final Sql2o sql2o;

    public DepartmentDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Department department) {

    }

    @Override
    public List<Department> findAll(Connection conn) {
        return null;
    }

    @Override
    public Department findById(int id) {
        return null;
    }

    @Override
    public void update(int id, Department department) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void clearAll() {

    }
}
