package com.moringaschool.Dao;

import com.moringaschool.Database.DB;
import com.moringaschool.Interfaces.NewsInterface;
import com.moringaschool.Models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class NewsDao implements NewsInterface {
    private final Sql2o sql2o;

    public NewsDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }


    @Override
    public void add(News news) {
        try(Connection conn = DB.sql2o.open()) {
            int id = (int) conn.createQuery("INSERT INTO news (headline, content, departmentId) VALUES (:headline, :content, :departmentId)", true).bind(news).executeUpdate().getKey();
            news.setId(id);
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<News> findAll(Connection conn) {
        try {
            return conn.createQuery("SELECT * FROM news").executeAndFetch(News.class);
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public News findById(int id) {
        try(Connection conn = DB.sql2o.open()) {
            return conn.createQuery("SELECT * FROM news WHERE id=:id").addParameter("id", id).executeAndFetchFirst(News.class);
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(int id, News news) {
        try(Connection conn = DB.sql2o.open()) {
            conn.createQuery("UPDATE news SET headline=:headline, content=:content, departmentId=:departmentId WHERE id=:id").addParameter("id", id).bind(news).executeUpdate();
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        try(Connection conn = DB.sql2o.open()) {
            conn.createQuery("DELETE FROM news WHERE id=:id").addParameter("id", id).executeUpdate();
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<News> findDepartmentNews(int department_id) {
        try(Connection conn = DB.sql2o.open()) {
            return conn.createQuery("SELECT * FROM news WHERE departmentid=:departmentid").addParameter("departmentid", department_id).executeAndFetch(News.class);
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void clearAll() {
        try(Connection conn = DB.sql2o.open()) {
            conn.createQuery("DELETE FROM news").executeUpdate();
        } catch (Sql2oException ex) {
            throw new RuntimeException(ex);
        }
    }
}
