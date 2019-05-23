package com.elman.course.service;

import com.elman.course.dao.TeacherDao;
import com.elman.course.model.Teacher;

import java.util.List;

public class TeacherServiceImpl implements TeacherService{
    private TeacherDao teacherDao;

    public TeacherServiceImpl(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public List<Teacher> getAllTeacher() {
        return teacherDao.getAllTeacher();
    }

    @Override
    public boolean deleteTeacher(int idTeacher) {
        return teacherDao.deleteTeacher(idTeacher);
    }

    @Override
    public List<Teacher> getTeachersByIdCourse(int idCourse) {
        return teacherDao.getTeachersByIdCourse(idCourse);
    }

    @Override
    public boolean addTeacher(Teacher teacher) {
        return teacherDao.addTeacher(teacher);
    }

    @Override
    public Teacher getTeacherById(int idTeacher) {
        return teacherDao.getTeacherById(idTeacher);
    }

    @Override
    public boolean updateTeacher(Teacher teacher) {
        return teacherDao.updateTeacher(teacher);
    }
}
