package com.elman.course.dao;

import com.elman.course.model.Teacher;

import java.util.List;

public interface TeacherDao {
    List<Teacher>  getAllTeacher();
    Teacher getTeacherById(int idTeacher);
    boolean deleteTeacher(int idTeacher);
    List<Teacher> getTeachersByIdCourse(int idCourse);
    boolean addTeacher(Teacher teacher);
    boolean updateTeacher(Teacher teacher);


}
