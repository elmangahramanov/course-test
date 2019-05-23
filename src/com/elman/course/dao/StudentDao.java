package com.elman.course.dao;

import com.elman.course.model.Student;
import com.elman.course.model.StudentSearch;

import java.util.List;

public interface StudentDao {
    List<Student> getAllStudent();
    Student getStudentById(int idStudent);
    boolean deleteStudent(int idStudent);
    boolean addStudent(Student student);
    boolean updateStudent(Student student);
    List<Student> advSearchStudent(StudentSearch studentSearch);
}
