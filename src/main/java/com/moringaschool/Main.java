package com.moringaschool;

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

    private static Connection conn = DB.sql2o.open();
    public static void main(String[] args) {
        DB.createTables(DB.sql2o.open());
        Gson gson = new Gson();

        post("/add/user", "application/json", (request, response) -> {
            User user = gson.fromJson(request.body(), User.class);
            userDao.add(user);
            response.status(201);
            response.type("application/json");
            return gson.toJson(user);
        });

        get("/view/user", "application/json", (request, response) -> {
            List<User> users = userDao.findAll(conn);
            if (users.size() < 1) {
                throw new ApiException(404, String.format("No users found"));
            }
            return gson.toJson(userDao.findAll(conn));
        });

        get("/view/:id/user", "application/json", (request, response) -> {
            User user = userDao.findById(Integer.parseInt(request.params("id")));
            if (user != null) {
                return gson.toJson(user);
            } else {
                throw new ApiException(404, String.format("User not found"));
            }
        });

        post("/user/:id/update/username", "application/json", (request, response) -> {
            User user = gson.fromJson(request.body(), User.class);
//            user.setId(Integer.parseInt(request.params("id")));
            userDao.update(Integer.parseInt(request.params("id")), user);
            response.status(201);
            response.type("application/json");
            return gson.toJson(user);
        });


    }
}