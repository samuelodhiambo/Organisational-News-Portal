package com.moringaschool;

import com.moringaschool.Dao.UserDao;
import com.moringaschool.Database.DB;
import com.moringaschool.Models.Department;
import com.moringaschool.Models.User;

public class Main {
    private static UserDao userDao = new UserDao(DB.sql2o);
    public static void main(String[] args) {
        DB.createTables(DB.sql2o.open());
        Department department = new Department("ICT", "ICT support services", 20);
//        System.out.println(department);
        User user = new User("Samuel", "Manager", "operations", 1);
//        System.out.println(user.getDepartment());
        userDao.add(user);
        System.out.println(user.getId());
    }
}