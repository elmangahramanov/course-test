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

public class CourseDaoImpl implements CourseDao {

    private final String GET_ALL_COURSE = "select * from course";
    private final String DELETE_COURSE = "delete from course where id_course=?";
    private final String ADD_COURSE = "insert into course(course_name,description) values(?,?)";
    private final String GET_COURSE_BY_ID = "select * from course where id_course=?";
    private final String UPDATE_COURSE = "update course set course_name=?,description=? where id_course=?";

    @Override
    public List<Course> getAllCourse() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Course> list = new ArrayList<>();
        try {
            con = DbUtil.connect();
            ps = con.prepareStatement(GET_ALL_COURSE);
            rs = ps.executeQuery();
            while (rs.next()){

                Course course = new Course();
                course.setId(rs.getInt("id_course"));
                course.setCourseName(rs.getString("course_name"));
                course.setDescription(rs.getString("description"));

                list.add(course);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtil.closeAll(con,ps,rs);
        }
        return list;
    }

    @Override
    public Course getCourseById(int idCourse) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Course course = null;
        try {
            con = DbUtil.connect();
            ps = con.prepareStatement(GET_COURSE_BY_ID);
            ps.setInt(1,idCourse);
            rs = ps.executeQuery();
            while (rs.next()){

                course = new Course();
                course.setId(rs.getInt("id_course"));
                course.setCourseName(rs.getString("course_name"));
                course.setDescription(rs.getString("description"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtil.closeAll(con,ps,rs);
        }
        return course;
    }

    @Override
    public boolean deleteCourse(int idCourse) {
        Connection con = null;
        PreparedStatement ps = null;
        boolean result = false;
        try {
            con = DbUtil.connect();
            ps = con.prepareStatement(DELETE_COURSE);
            ps.setInt(1,idCourse);
            ps.executeUpdate();
            result = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean addCourse(Course course) {
        Connection con = null;
        PreparedStatement ps = null;
        boolean result = false;
        try {
            con = DbUtil.connect();
            ps = con.prepareStatement(ADD_COURSE);
            ps.setString(1,course.getCourseName());
            ps.setString(2,course.getDescription());
            ps.execute();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateCourse(Course course) {
        Connection con = null;
        PreparedStatement ps = null;
        boolean result = false;
        try {
            con = DbUtil.connect();
            ps = con.prepareStatement(UPDATE_COURSE);
            ps.setString(1,course.getCourseName());
            ps.setString(2,course.getDescription());
            ps.setInt(3,course.getId());
            ps.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
