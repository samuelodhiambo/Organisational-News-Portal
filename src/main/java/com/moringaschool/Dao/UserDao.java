package com.moringaschool.Dao;

import com.moringaschool.Database.DB;
import com.moringaschool.Interfaces.UserInterface;
import com.moringaschool.Models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class UserDao implements UserInterface {
    private final Sql2o sql2o;

    public UserDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(User user) {
        String sql = "";
        sql = "insert into users (\n" +
                "    \"role\",\n" +
                "    department,\n" +
                "    \"position\",\n" +
                "    username)\n" +
                "values (\n" +
                "    :role,\n" +
                "    :department,\n" +
                "    :position,\n" +
                "    :username)\n" +
                "";
        try(Connection conn = DB.sql2o.open()) {
            int id = (int) conn.createQuery(sql, true).bind(user).executeUpdate().getKey();
            user.setId(id);
        } catch (Sql2oException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll(Connection conn) {
        try {
            return conn.createQuery("SELECT * FROM users").executeAndFetch(User.class);
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public User findById(int id) {
        try(Connection conn = DB.sql2o.open()) {
            return conn.createQuery("SELECT * FROM users WHERE id=:id").addParameter("id", id).executeAndFetchFirst(User.class);
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(int id, User user) {
        try(Connection conn = DB.sql2o.open()) {
            conn.createQuery("UPDATE users SET role=:role, department=:department, position=:position, username=:username WHERE id=:id").bind(user).executeUpdate();
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        try(Connection conn = DB.sql2o.open()) {
            conn.createQuery("DELETE FROM users WHERE id=:id").addParameter("id", id).executeUpdate();
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void clearAll() {
        try(Connection conn = DB.sql2o.open()) {
            conn.createQuery("DELETE FROM users").executeUpdate();
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }
    }
}
