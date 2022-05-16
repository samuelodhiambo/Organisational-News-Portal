package com.moringaschool;

import com.moringaschool.Dao.UserDao;
import com.moringaschool.Database.DB;
import com.moringaschool.Models.Department;
import com.moringaschool.Models.User;

import static spark.Spark.*;

import com.google.gson.Gson;


public class Main {
    private static UserDao userDao = new UserDao(DB.sql2o);
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


    }
}