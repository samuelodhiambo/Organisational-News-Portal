package com.moringaschool.Models;

import java.util.Objects;

public class Department {
    private int id;
    private String departmentName;
    private String description;
    private int numberOfEmployees;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return getId() == that.getId() && getNumberOfEmployees() == that.getNumberOfEmployees() && getDepartmentName().equals(that.getDepartmentName()) && getDescription().equals(that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDepartmentName(), getDescription(), getNumberOfEmployees());
    }

    public Department(String departmentName, String description, int numberOfEmployees) {
        this.departmentName = departmentName;
        this.description = description;
        this.numberOfEmployees = numberOfEmployees;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getDescription() {
        return description;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }
}
