package com.moringaschool.Dao;

import com.moringaschool.Database.DB;
import com.moringaschool.Models.Department;
import com.moringaschool.Models.News;
import com.moringaschool.Models.User;
import org.h2.util.New;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;

import java.util.List;

import static org.junit.Assert.*;

public class NewsDaoTest {
    private Connection conn;
    private UserDao userDao;
    private DepartmentDao departmentDao;
    private NewsDao newsDao;

    @Before
    public void setUp() throws Exception {
        conn = DB.sql2o.open();
        DB.createTables(conn);
        userDao = new UserDao(DB.sql2o);
        departmentDao = new DepartmentDao(DB.sql2o);
        newsDao = new NewsDao(DB.sql2o);
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void add() {
        News news = setupNews();
        News news1 = newsDao.findById(news.getId());
        assertEquals(news.getId(), news1.getId());
    }

    @Test
    public void findAll() {
        newsDao.clearAll();
        News news1 = setupNews();
        News news2 = setupNews();
        assertEquals(2, newsDao.findAll(conn).size());
    }

    @Test
    public void findById() {
        News news = setupNews();
        News foundNews = newsDao.findById(news.getId());
        assertEquals(news, foundNews);
    }

    @Test
    public void update() {
        News news = setupNews();
        News updatedNews = new News("Am the updated headline", "I am a very interesting content", news.getId());
        updatedNews.setId(news.getId());
        newsDao.update(news.getId(), updatedNews);
        assertEquals(updatedNews.getHeadline(), "Am the updated headline");
    }

    @Test
    public void deleteById() {
        News news = setupNews();
        newsDao.deleteById(news.getId());
        assertEquals(null, newsDao.findById(news.getId()));
    }

    @Test
    public void findDepartmentNews() {
        newsDao.clearAll();
        Department department1 = new Department("ICT", "ICT support services", 20);
        departmentDao.add(department1);
        News news = new News("Am the headline", "I am a very interesting content", department1.getId());
        newsDao.add(news);
        List<News> departmentNews = newsDao.findDepartmentNews(department1.getId());
        for (int i=1; i<departmentNews.size(); i++ ){
            System.out.println(i);
            assertEquals(departmentNews.get(i).getId(), news.getDepartment());
        }
    }

    @Test
    public void clearAll() {
        newsDao.clearAll();
        assertEquals(0, newsDao.findAll(conn).size());
    }

    // helpers
    public News setupNews() {
        Department department = new Department("ICT", "ICT support services", 20);
        departmentDao.add(department);
        News news = new News("Am the headline", "I am a very interesting content", department.getId());
        newsDao.add(news);
        return news;
    }
}