package com.example.climbing.models;

public enum GradeEnum {
    FIVE_A("5a"),
    FIVE_B("5b"),
    FIVE_C("5c"),
    SIX_A("6a"),
    SIX_B("6b"),
    SIX_C("6c");

    private final String grade;

    GradeEnum(String grade) {
        this.grade = grade;
    }

    public String getValue() {
        return grade;
    }

    // Convert a String to GradeEnum
    public static GradeEnum fromString(String gradeString) {
        for (GradeEnum grade : GradeEnum.values()) {
            if (grade.getValue().equalsIgnoreCase(gradeString)) {
                return grade;
            }
        }
        throw new IllegalArgumentException("No matching GradeEnum for String: " + gradeString);
    }
}

