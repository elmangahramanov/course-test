package com.elman.course.service;

import com.elman.course.dao.CourseDao;
import com.elman.course.model.Course;

import java.util.List;

public class CourseServiceImpl implements CourseService {
    private CourseDao courseDao;

    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public List<Course> getAllCourse() {
        return courseDao.getAllCourse();
    }

    @Override
    public Course getCourseById(int idCourse) {
        return courseDao.getCourseById(idCourse);
    }

    @Override
    public boolean deleteCourse(int idCourse) {
        return courseDao.deleteCourse(idCourse);
    }

    @Override
    public boolean addCourse(Course course) {
        return courseDao.addCourse(course);
    }

    @Override
    public boolean updateCourse(Course course) {
        return courseDao.updateCourse(course);
    }
}
