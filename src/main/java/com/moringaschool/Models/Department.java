package com.moringaschool.Models;

import java.util.Objects;

public class Department {
    private int id;
    private String departmentname;
    private String description;
    private int numberofemployees;

    public Department(String departmentname, String description, int numberofemployees) {
        this.departmentname = departmentname;
        this.description = description;
        this.numberofemployees = numberofemployees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return getId() == that.getId() && getNumberofemployees() == that.getNumberofemployees() && getDepartmentname().equals(that.getDepartmentname()) && getDescription().equals(that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDepartmentname(), getDescription(), getNumberofemployees());
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public String getDescription() {
        return description;
    }

    public int getNumberofemployees() {
        return numberofemployees;
    }
}
