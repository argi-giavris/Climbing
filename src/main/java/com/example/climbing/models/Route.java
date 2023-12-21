package com.example.climbing.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Route {
    Integer id;
    GradeEnum grade;
    String setterEmail;


    public Route(Integer id, GradeEnum grade, String setterEmail) {
        this.id = id;
        this.grade = grade;
        this.setterEmail = setterEmail;
    }


    public Route(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.grade = GradeEnum.fromString(resultSet.getString("grade"));
        this.setterEmail = resultSet.getString("setter_email");
    }

    public Route(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GradeEnum getGrade() {
        return grade;
    }

    public void setGrade(GradeEnum grade) {
        this.grade = grade;
    }

    public String getSetterEmail() {
        return setterEmail;
    }

    public void setSetterName(String setterEmail) {
        this.setterEmail = setterEmail;
    }
}
