package com.moringaschool.Dao;

import com.moringaschool.Database.DB;
import com.moringaschool.Models.Department;
import com.moringaschool.Models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;

import static org.junit.Assert.*;

public class DepartmentDaoTest {
    private Connection conn;
    private UserDao userDao;
    private DepartmentDao departmentDao;

    @Before
    public void setUp() throws Exception {
        conn = DB.sql2o.open();
        DB.createTables(conn);
        userDao = new UserDao(DB.sql2o);
        departmentDao = new DepartmentDao(DB.sql2o);
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void add() {
        Department department = setupDepartment();
        Department department1 = departmentDao.findById(department.getId());
        assertEquals(department.getId(), department1.getId());
    }

    @Test
    public void findAll() {
        departmentDao.clearAll();
        Department department1 = setupDepartment();
        Department department2 = setupDepartment();
        assertEquals(2, departmentDao.findAll(conn).size());
    }

    @Test
    public void findById() {
        Department department = setupDepartment();
        Department foundDepartment = departmentDao.findById(department.getId());
        assertEquals(department, foundDepartment);
    }

    @Test
    public void findUsersByDepartment() {
        Department department1 = setupDepartment();
        User user = new User("Samuel", "Manager", "operations", department1.getId());
        userDao.add(user);
        departmentDao.findUsersByDepartment(user.getDepartment());
        assertEquals(department1.getId(), user.getDepartment());
    }

    @Test
    public void update() {
        Department department = setupDepartment();
        Department updatedDepartment = new Department("ICT Dept", "ICT support services", 20);
        updatedDepartment.setId(department.getId());
        departmentDao.update(department.getId(), updatedDepartment);
        assertEquals(department.getId(), updatedDepartment.getId());
    }

    @Test
    public void deleteById() {
        Department department = setupDepartment();
        departmentDao.deleteById(department.getId());
        assertEquals(null, departmentDao.findById(department.getId()));
    }

    @Test
    public void clearAll() {
        departmentDao.clearAll();
        assertEquals(0, departmentDao.findAll(conn).size());
    }

    // helpers
    public Department setupDepartment() {
        Department department = new Department("ICT", "ICT support services", 20);
        departmentDao.add(department);
        return department;
    }
}