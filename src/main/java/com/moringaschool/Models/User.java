package com.moringaschool.Models;

import java.util.Objects;

public class User {
    private int id;
    private String username;
    private String position;
    private String role;
    private int department;

    public User(String username, String position, String role, int department) {
        this.username = username;
        this.position = position;
        this.role = role;
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() && getDepartment() == user.getDepartment() && getUsername().equals(user.getUsername()) && getPosition().equals(user.getPosition()) && getRole().equals(user.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPosition(), getRole(), getDepartment());
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

    public int getDepartment() {
        return department;
    }
}
