package com.moringaschool.Models;

import com.moringaschool.Dao.DepartmentDao;
import com.moringaschool.Database.DB;

import java.util.Objects;

public class News {
    private int id;
    private String headline;
    private String content;
    private int departmentId;
    private Department department;

    private DepartmentDao departmentDao = new DepartmentDao(DB.sql2o);

    public News(String headline, String content, int departmentId) {
        this.headline = headline;
        this.content = content;
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News)) return false;
        News news = (News) o;
        return getId() == news.getId() && getDepartmentId() == news.getDepartmentId() && getHeadline().equals(news.getHeadline()) && getContent().equals(news.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getHeadline(), getContent(), getDepartmentId());
    }

    public String getHeadline() {
        return headline;
    }

    public String getContent() {
        return content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartment(Department department) {
        this.department = departmentDao.findById(this.id);
    }

    public Department getDepartment() {
        return department;
    }
}
