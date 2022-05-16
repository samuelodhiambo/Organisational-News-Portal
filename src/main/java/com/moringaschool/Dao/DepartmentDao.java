package com.moringaschool.Dao;

import com.moringaschool.Database.DB;
import com.moringaschool.Interfaces.DepartmentInterface;
import com.moringaschool.Models.Department;
import com.moringaschool.Models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class DepartmentDao implements DepartmentInterface {
    private final Sql2o sql2o;

    public DepartmentDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Department department) {
        String sql = "";
        sql = "insert into departments (\n" +
                "    departmentname,\n" +
                "    description,\n" +
                "    numberofemployees)\n" +
                "values (\n" +
                "    :departmentname,\n" +
                "    :description,\n" +
                "    :numberofemployees)\n" +
                ";";
        try(Connection conn = DB.sql2o.open()) {
            int id = (int) conn.createQuery(sql, true).bind(department).executeUpdate().getKey();
            department.setId(id);
        } catch (Sql2oException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Department> findAll(Connection conn) {
        try {
            return conn.createQuery("SELECT * FROM departments").executeAndFetch(Department.class);
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Department findById(int id) {
        try(Connection conn = DB.sql2o.open()) {
            return conn.createQuery("SELECT * FROM departments WHERE id=:id").addParameter("id", id).executeAndFetchFirst(Department.class);
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(int id, Department department) {
        try(Connection conn = DB.sql2o.open()) {
            conn.createQuery("UPDATE departments SET departmentname=:departmentname, description=:description, numberofemployees=:numberofemployees WHERE id=:id").bind(department).executeUpdate();
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void clearAll() {

    }
}
