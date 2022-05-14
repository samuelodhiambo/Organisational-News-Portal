package com.moringaschool.Database;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

public class DB {
    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/samian", "samian" ,"root");

    public static void createTables(Connection conn) {
        try{
            conn.createQuery("CREATE TABLE IF NOT EXISTS users (\n" +
                    " id SERIAL PRIMARY KEY,\n" +
                    " username VARCHAR,\n" +
                    " position VARCHAR,\n" +
                    " role VARCHAR,\n" +
                    " department int\n" +
                    ");").executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
            throw new RuntimeException();
        }

        try {
            conn.createQuery("CREATE TABLE IF NOT EXISTS departments (\n" +
                    " id SERIAL PRIMARY KEY,\n" +
                    " departmentName VARCHAR,\n" +
                    " description VARCHAR,\n" +
                    " numberOfEmployees int\n" +
                    ");").executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
            throw new RuntimeException();
        }
    }
}
