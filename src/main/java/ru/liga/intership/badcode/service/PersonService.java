package ru.liga.intership.badcode.service;


import ru.liga.intership.badcode.domain.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonService {
    Statement statement;
    List<Person> sqlResultList;

    public void connectToDataBase(String url, String user, String password) {
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            statement = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void doQuery(String sql) {
        try {
            ResultSet rs = statement.executeQuery(sql);
            sqlResultList = new ArrayList<>();
            while (rs.next()) {
                Person p = new Person(rs.getLong("id"),
                        rs.getString("sex"),
                        rs.getString("name"),
                        rs.getLong("age"),
                        rs.getLong("weight"),
                        rs.getLong("height"));
                sqlResultList.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public double getSelectedPersonsAverageBMI() {
        double totalImt = 0.0;
        long countOfPerson = 1;
        for (Person p : sqlResultList) {
            double heightInMeters = p.getHeight() / 100d;
            double imt = p.getWeight() / (Double) (heightInMeters * heightInMeters);
            totalImt += imt;
        }
        if (sqlResultList.size() != 0)
            countOfPerson = sqlResultList.size();
        return totalImt/countOfPerson;
    }
}
