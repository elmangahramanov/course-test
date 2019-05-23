package com.elman.course.service;

import com.elman.course.model.Student;
import com.elman.course.model.StudentSearch;

import java.util.List;

public interface StudentService  {
    List<Student> getAllStudent();
    boolean deleteStudent(int idStudent);
    boolean addStudent(Student student);
    Student getStudentById(int idStudent);
    boolean updateStudent(Student student);
    List<Student> advSearchStudent(StudentSearch studentSearch);
}
