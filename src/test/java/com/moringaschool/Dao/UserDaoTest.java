package com.moringaschool.Dao;

import com.moringaschool.Database.DB;
import com.moringaschool.Models.Department;
import com.moringaschool.Models.User;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;


public class UserDaoTest {
    private Connection conn;
    private UserDao userDao;
    private DepartmentDao departmentDao;

    @Before public void setUp() throws Exception {
        System.out.println("Done.....");
//        String ConnectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
//        Sql2o sql2o = new Sql2o(ConnectionString, "samian", "root");

        conn = DB.sql2o.open();
        DB.createTables(conn);
        userDao = new UserDao(DB.sql2o);
        departmentDao = new DepartmentDao(DB.sql2o);
    }

    @After public void tearDown() throws Exception {
        conn.close();
        System.out.println("Done.....");
    }

    @Test
    public void testAdd() {
        User testUser = setupUser();
        System.out.println(testUser.getUsername());
        assertEquals(testUser.getId(), testUser.getId());
    }

    public void testFindAll() {
    }

    public void testFindById() {
    }

    public void testUpdate() {
    }

    public void testDeleteById() {
    }

    public void testClearAll() {
    }

    // helpers

    public User setupUser() {
        Department department = new Department("ICT", "ICT support services", 20);
        System.out.println(department);
        User user = new User("Samuel", "Manager", "operations", 1);
        userDao.add(user);
        return user;
    }
}