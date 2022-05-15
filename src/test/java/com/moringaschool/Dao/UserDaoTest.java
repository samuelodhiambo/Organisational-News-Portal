package com.moringaschool.Dao;

import com.moringaschool.Database.DB;
import com.moringaschool.Models.Department;
import com.moringaschool.Models.User;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class UserDaoTest extends TestCase {
    private Connection conn;
    private UserDao userDao;
    private DepartmentDao departmentDao;

    @Before
    public void setUp() throws Exception {
//        String ConnectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
//        Sql2o sql2o = new Sql2o(ConnectionString, "samian", "root");

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
    public void testAdd() {
        User testUser = setupUser();
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
        System.out.println(user.getDepartment());
        userDao.add(user);
        return user;
    }
}