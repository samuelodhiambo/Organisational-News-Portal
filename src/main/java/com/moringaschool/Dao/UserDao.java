package com.moringaschool.Dao;

import com.moringaschool.Interfaces.UserInterface;
import com.moringaschool.Models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class UserDao implements UserInterface {
    private final Sql2o sql2o;

    public UserDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(User user) {

    }

    @Override
    public List<User> findAll(Connection conn) {
        return null;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public void update(int id) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void clearAll() {

    }
}
