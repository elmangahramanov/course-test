package com.elman.course.service;

import com.elman.course.model.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> getAllTeacher();
    boolean deleteTeacher(int idTeacher);
    List<Teacher> getTeachersByIdCourse(int idCourse);
    boolean addTeacher(Teacher teacher);
    Teacher getTeacherById(int idTeacher);
    boolean updateTeacher(Teacher teacher);
}
