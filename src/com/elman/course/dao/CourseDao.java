package com.elman.course.dao;

import com.elman.course.model.Course;

import java.util.List;

public interface CourseDao {
    List<Course> getAllCourse();
    Course getCourseById(int idCourse);
    boolean deleteCourse(int idCourse);
    boolean addCourse(Course course);
    boolean updateCourse(Course course);


}
