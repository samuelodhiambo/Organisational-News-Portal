SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS users (
 id SERIAL PRIMARY KEY,
 username VARCHAR,
 position VARCHAR,
 role VARCHAR,
 department int
);

CREATE TABLE IF NOT EXISTS departments (
 id SERIAL PRIMARY KEY,
 departmentName VARCHAR,
 description VARCHAR,
 numberOfEmployees int
);