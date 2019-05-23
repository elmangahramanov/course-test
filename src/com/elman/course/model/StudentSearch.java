package com.elman.course.model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class StudentSearch {
    private String studentName;
    private String studentSurname;
    private LocalDate dobMin;
    private LocalDate dobMax;
    private Integer selectCourse;
    private String description;
    private Integer selectTeacher;
    private String keyword;

    public StudentSearch() {
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        if (studentName == null || studentName.trim().isEmpty()) {
            this.studentName = null;
        } else {
            this.studentName = studentName;
        }
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        if (studentSurname == null || studentSurname.trim().isEmpty()) {
            this.studentSurname = null;
        } else {
            this.studentSurname = studentSurname;
        }
    }

    public LocalDate getDobMin() {
        return dobMin;
    }

    public void setDobMin(String dobMin) {
        if (dobMin == null || dobMin.trim().isEmpty()) {
            this.dobMin = null;
        } else {
            this.dobMin = LocalDate.parse(dobMin);
        }
    }

    public LocalDate getDobMax() {
        return dobMax;
    }

    public void setDobMax(String dobMax) {
        if (dobMax == null || dobMax.trim().isEmpty()) {
            this.dobMax = null;
        } else {
            this.dobMax = LocalDate.parse(dobMax);
        }
    }

    public Integer getSelectCourse() {
        return selectCourse;
    }

    public void setSelectCourse(String selectCourse) {
        if (selectCourse == null || selectCourse.trim().isEmpty()) {
            this.selectCourse = null;
        } else {
            this.selectCourse = Integer.valueOf(selectCourse);
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            this.description = null;
        } else {
            this.description = description;
        }
    }

    public Integer getSelectTeacher() {
        return selectTeacher;
    }

    public void setSelectTeacher(String selectTeacher) {
        if (selectTeacher == null || selectTeacher.trim().isEmpty()) {
            this.selectTeacher = null;
        } else {
            this.selectTeacher = Integer.valueOf(selectTeacher);
        }
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            this.keyword = null;
        } else {
            this.keyword = keyword;
        }
    }

    public boolean isAllFieldsNull() {
        List<Object> allFields = Arrays.asList(studentName, studentSurname, dobMin, dobMax, selectCourse, description, selectTeacher);
        for (Object o : allFields) {
            if (o != null) {
                return false;
            }

        }
        return true;
    }
}

