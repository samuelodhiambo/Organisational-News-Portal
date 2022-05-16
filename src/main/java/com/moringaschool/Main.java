package com.moringaschool;

import com.moringaschool.Dao.DepartmentDao;
import com.moringaschool.Dao.UserDao;
import com.moringaschool.Database.DB;
import com.moringaschool.Exceptions.ApiException;
import com.moringaschool.Models.Department;
import com.moringaschool.Models.User;

import static spark.Spark.*;

import com.google.gson.Gson;
import org.sql2o.Connection;

import java.util.List;


public class Main {
    private static UserDao userDao = new UserDao(DB.sql2o);
    private static DepartmentDao departmentDao = new DepartmentDao(DB.sql2o);

    private static Connection conn = DB.sql2o.open();
    public static void main(String[] args) {
        DB.createTables(DB.sql2o.open());
        Gson gson = new Gson();

        // User
        post("/user/add", "application/json", (request, response) -> {
            User user = gson.fromJson(request.body(), User.class);
            if (departmentDao.findById(user.getDepartment()) != null) {
                userDao.add(user);
                response.status(201);
                response.type("application/json");
                return gson.toJson(user);
            } else {
                return new ApiException(404, String.format("Department entered does not exist")).getMessage();
            }
        });

        get("/user/:id/view", "application/json", (request, response) -> {
            User user = userDao.findById(Integer.parseInt(request.params("id")));
            if (user != null) {
                return gson.toJson(user);
            } else {
                return new ApiException(404, String.format("User not found")).getMessage();
            }
        });

        get("/view/user", "application/json", (request, response) -> {
            List<User> users = userDao.findAll(conn);
            if (users.size() < 1) {
                return new ApiException(404, String.format("No users found")).getMessage();
            }
            return gson.toJson(userDao.findAll(conn));
        });

        post("/user/:id/update", "application/json", (request, response) -> {
            User user = gson.fromJson(request.body(), User.class);
            user.setId(Integer.parseInt(request.params("id")));
            userDao.update(Integer.parseInt(request.params("id")), user);
            response.status(201);
            response.type("application/json");
            return gson.toJson(user);
        });

        get("/delete/:id/user", "application/json", (request, response) -> {
            userDao.deleteById(Integer.parseInt(request.params("id")));
            response.status(201);
            response.type("application/json");
            return gson.toJson(userDao.findAll(conn));
        });

        // Department
        post("/department/add", "application/json", (request, response) -> {
            Department department = gson.fromJson(request.body(), Department.class);
            departmentDao.add(department);
            response.status(201);
            response.type("application/json");
            return gson.toJson(department);
        });

        get("/department/:id/view", "application/json", (request, response) -> {
            Department department = departmentDao.findById(Integer.parseInt(request.params("id")));
            if (department != null) {
                return gson.toJson(department);
            } else {
                return new ApiException(404, String.format("Department not found")).getMessage();
            }
        });

        get("/view/department", "application/json", (request, response) -> {
            List<Department> departments = departmentDao.findAll(conn);
            if (departments.size() < 1) {
                return new ApiException(404, String.format("No departments found")).getMessage();
            }
            return gson.toJson(departmentDao.findAll(conn));
        });

        post("/department/:id/update", "application/json", (request, response) -> {
            Department department = gson.fromJson(request.body(), Department.class);
            department.setId(Integer.parseInt(request.params("id")));
            departmentDao.update(Integer.parseInt(request.params("id")), department);
            response.status(201);
            response.type("application/json");
            return gson.toJson(department);
        });

        get("/delete/:id/department", "application/json", (request, response) -> {
            departmentDao.deleteById(Integer.parseInt(request.params("id")));
            response.status(201);
            response.type("application/json");
            return gson.toJson(departmentDao.findAll(conn));
        });

    }
}