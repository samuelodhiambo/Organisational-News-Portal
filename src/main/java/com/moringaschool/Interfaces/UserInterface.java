package com.moringaschool.Interfaces;

import com.moringaschool.Models.User;
import org.sql2o.Connection;

import java.util.List;

public interface UserInterface {
    void add(User user);

    List<User> findAll(Connection conn);

    User findById(int id);

    void update(int id, User user);

    void deleteById(int id);

    void clearAll();
}
