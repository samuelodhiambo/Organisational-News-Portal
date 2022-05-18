package com.moringaschool.Dao;

import com.moringaschool.Database.DB;
import com.moringaschool.Models.Department;
import com.moringaschool.Models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;

import static org.junit.Assert.*;

public class UserDaoTest {
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
        User testUser = setupUser();
        assertEquals(testUser.getId(), testUser.getId());
    }

    @Test
    public void findAll() {
        userDao.clearAll();
        User user1 = setupUser();
        User user2 = setupUser();
        assertEquals(2, userDao.findAll(conn).size());
    }

    @Test
    public void findById() {
        User user = setupUser();
        User foundUser = userDao.findById(user.getId());
        assertEquals(user, foundUser);
    }

    @Test
    public void update() {
        User user = setupUser();
        User updatedUser = new User("Samuel Ian", "Manager", "operations", 1);
        updatedUser.setId(user.getId());
        userDao.update(user.getId(), updatedUser);
        assertEquals(user.getId(), updatedUser.getId());
    }

    @Test
    public void deleteById() {
        User user = setupUser();
        userDao.deleteById(user.getId());
        assertEquals(null, userDao.findById(user.getId()));
    }

    @Test
    public void clearAll() {
        userDao.clearAll();
        assertEquals(0, userDao.findAll(conn).size());
    }

    // helpers
    public User setupUser() {
        Department department = new Department("ICT", "ICT support services", 20);
        departmentDao.add(department);
        System.out.println(department);
        User user = new User("Samuel", "Manager", "operations", 1);
        userDao.add(user);
        return user;
    }
}