package com.moringaschool.Interfaces;

import com.moringaschool.Models.Department;
import com.moringaschool.Models.News;
import com.moringaschool.Models.User;
import org.sql2o.Connection;

import java.util.List;

public interface NewsInterface {
    void add(News news);

    List<News> findAll(Connection conn);

    News findById(int id);

    void update(int id, News news);

    void deleteById(int id);

    List<News> findDepartmentNews(int department_id);

    void clearAll();
}
