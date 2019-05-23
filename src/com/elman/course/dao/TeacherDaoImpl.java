package com.elman.course.dao;

import com.elman.course.model.Course;
import com.elman.course.model.Student;
import com.elman.course.model.Teacher;
import com.elman.course.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TeacherDaoImpl implements TeacherDao {

    private final String GET_ALL_TEACHER = "select t.id_teacher,t.first_name,t.last_name,c.id_course,c.course_name from teacher t inner join course c on t.id_course=c.id_course";
    private final String DELETE_TEACHER = "delete from teacher where id_teacher=?";
    private final String GET_TEACHERS_BY_ID_COURSE = "select * from teacher where id_course=?";
    private final String ADD_TEACHER = "insert into teacher(first_name,last_name,id_course) values(?,?,?)";
    private final String GET_TEACHER_BY_ID = "select t.id_teacher,t.first_name,t.last_name,c.id_course,c.course_name,c.description from teacher t inner join course c on t.id_course=c.id_course where id_teacher=?";
    private final String UPDATE_TEACHER = "update teacher set first_name=?,last_name=?,id_course=? where id_teacher=?";
    @Override
    public List<Teacher> getAllTeacher() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Teacher> list = new ArrayList<>();
        try {
            con = DbUtil.connect();
            ps = con.prepareStatement(GET_ALL_TEACHER);
            rs = ps.executeQuery();
            while (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(rs.getInt("id_teacher"));
                teacher.setFirstName(rs.getString("first_name"));
                teacher.setLastName(rs.getString("last_name"));
                Course course = new Course();
                course.setId(rs.getInt("id_course"));
                course.setCourseName(rs.getString("course_name"));
                teacher.setCourse(course);
                list.add(teacher);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeAll(con, ps, rs);
        }
        return list;
    }

    @Override
    public Teacher getTeacherById(int idTeacher) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Teacher teacher = null;
        try {
            con = DbUtil.connect();
            ps = con.prepareStatement(GET_TEACHER_BY_ID);
            ps.setInt(1,idTeacher);
            rs = ps.executeQuery();
            while (rs.next()) {
                 teacher = new Teacher();
                teacher.setId(rs.getInt("id_teacher"));
                teacher.setFirstName(rs.getString("first_name"));
                teacher.setLastName(rs.getString("last_name"));
                Course course = new Course();
                course.setId(rs.getInt("id_course"));
                course.setCourseName(rs.getString("course_name"));
                course.setDescription(rs.getString("description"));
                teacher.setCourse(course);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeAll(con, ps, rs);
        }
        return teacher;
    }

    @Override
    public boolean deleteTeacher(int idTeacher) {
        Connection con = null;
        PreparedStatement ps = null;
        boolean result = false;
        try {
            con = DbUtil.connect();
            ps = con.prepareStatement(DELETE_TEACHER);
            ps.setInt(1,idTeacher);
            ps.executeUpdate();
            result = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Teacher> getTeachersByIdCourse(int idCourse) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Teacher> list = new ArrayList<>();

        try {
            con=DbUtil.connect();
            ps = con.prepareStatement(GET_TEACHERS_BY_ID_COURSE);
            ps.setInt(1,idCourse);
            rs = ps.executeQuery();
            while (rs.next()){
                Teacher teacher = new Teacher();
                teacher.setId(rs.getInt("id_teacher"));
                teacher.setFirstName(rs.getString("first_name"));
                teacher.setLastName(rs.getString("last_name"));
                list.add(teacher);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean addTeacher(Teacher teacher) {
        Connection con = null;
        PreparedStatement ps = null;
        boolean result = false;

        try {
            con = DbUtil.connect();
            ps = con.prepareStatement(ADD_TEACHER);
            ps.setString(1,teacher.getFirstName());
            ps.setString(2,teacher.getLastName());
            ps.setInt(3,teacher.getCourse().getId());
            ps.execute();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateTeacher(Teacher teacher) {
        Connection con = null;
        PreparedStatement ps = null;
        boolean result = false;

        try {
            con = DbUtil.connect();
            ps = con.prepareStatement(UPDATE_TEACHER);
            ps.setString(1,teacher.getFirstName());
            ps.setString(2,teacher.getLastName());
            ps.setInt(3,teacher.getCourse().getId());
            ps.setInt(4,teacher.getId());
            ps.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
