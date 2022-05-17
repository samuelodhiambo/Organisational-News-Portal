package com.moringaschool.Models;

import com.moringaschool.Dao.DepartmentDao;
import com.moringaschool.Database.DB;

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
