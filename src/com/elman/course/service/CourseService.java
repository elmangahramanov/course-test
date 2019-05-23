package com.elman.course.service;

import com.elman.course.model.Course;
import com.elman.course.model.Student;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourse();
    Course getCourseById(int idCourse);
    boolean deleteCourse(int idCourse);
    boolean addCourse(Course course);
    boolean updateCourse(Course course);

}
