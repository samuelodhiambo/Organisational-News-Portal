package com.moringaschool.Models;

import java.util.Objects;

public class User {
    private int id;
    private String username;
    private String position;
    private String role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() && getUsername().equals(user.getUsername()) && getPosition().equals(user.getPosition()) && getRole().equals(user.getRole()) && getDepartment().equals(user.getDepartment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPosition(), getRole(), getDepartment());
    }

    private Department department;

    public User(String username, String position, String role, Department department) {
        this.username = username;
        this.position = position;
        this.role = role;
        this.department = department;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPosition() {
        return position;
    }

    public String getRole() {
        return role;
    }

    public Department getDepartment() {
        return department;
    }
}
