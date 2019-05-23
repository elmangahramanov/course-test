package com.elman.course.dao;

import com.elman.course.model.Course;
import com.elman.course.model.Student;
import com.elman.course.model.StudentSearch;
import com.elman.course.model.Teacher;
import com.elman.course.util.DbUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    private final String GET_ALL_STUDENT = "select s.id_student,s.first_name,s.last_name,s.date_of_birth,t.id_teacher,t.first_name as t_first_name,t.last_name as t_last_name,c.id_course,c.course_name,c.description from student s inner join teacher t on s.id_teacher=t.id_teacher inner join course c on c.id_course=t.id_course";
    private final String DELETE_STUDENT = "delete from student where id_student=?";
    private final String ADD_STUDENT = "insert into student(first_name,last_name,date_of_birth,id_teacher) values(?,?,?,?)";
    private final String GET_STUDENT_BY_ID = "select s.id_student,s.first_name,s.last_name,s.date_of_birth,t.id_teacher,t.first_name as t_first_name,t.last_name as t_last_name,c.id_course,c.course_name,c.description from student s inner join teacher t on s.id_teacher=t.id_teacher inner join course c on c.id_course=t.id_course where s.id_student=?";
    private final String UPDATE_STUDENT  = "update student set first_name=?,last_name=?,date_of_birth=?,id_teacher=? where id_student=?";
    private final String ADV_SEARCH_STUDENT = "select s.id_student,s.first_name,s.last_name,s.date_of_birth,t.id_teacher,t.first_name as t_first_name,t.last_name as t_last_name,c.id_course,c.course_name,c.description from student s inner join teacher t on s.id_teacher=t.id_teacher inner join course c on c.id_course=t.id_course";

    @Override
    public List<Student> getAllStudent() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Student> list = new ArrayList<>();
        try {
            con = DbUtil.connect();
            ps = con.prepareStatement(GET_ALL_STUDENT);
            rs = ps.executeQuery();
            while (rs.next()){
                Student student = new Student();
                student.setId(rs.getInt("id_student"));
                student.setFirstName(rs.getString("first_name"));
                student.setLastName(rs.getString("last_name"));
                student.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                Teacher teacher = new Teacher();
                teacher.setId(rs.getInt("id_teacher"));
                teacher.setFirstName(rs.getString("t_first_name"));
                teacher.setLastName(rs.getString("t_last_name"));
                Course course = new Course();
                course.setId(rs.getInt("id_course"));
                course.setCourseName(rs.getString("course_name"));
                course.setDescription(rs.getString("description"));
                teacher.setCourse(course);
                student.setTeacher(teacher);
                list.add(student);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtil.closeAll(con,ps,rs);
        }
        return list;
    }

    @Override
    public Student getStudentById(int idStudent) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student student = null;
        try {
            con = DbUtil.connect();
            ps = con.prepareStatement(GET_STUDENT_BY_ID);
            ps.setInt(1,idStudent);
            rs = ps.executeQuery();
            while (rs.next()){
                student = new Student();
                student.setId(rs.getInt("id_student"));
                student.setFirstName(rs.getString("first_name"));
                student.setLastName(rs.getString("last_name"));
                student.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                Teacher teacher = new Teacher();
                teacher.setId(rs.getInt("id_teacher"));
                teacher.setFirstName(rs.getString("t_first_name"));
                teacher.setLastName(rs.getString("t_last_name"));
                Course course = new Course();
                course.setId(rs.getInt("id_course"));
                course.setCourseName(rs.getString("course_name"));
                course.setDescription(rs.getString("description"));
                teacher.setCourse(course);
                student.setTeacher(teacher);

            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtil.closeAll(con, ps, rs);
        }
        return student;
    }

    @Override
    public boolean deleteStudent(int idStudent) {
        Connection con = null;
        PreparedStatement ps = null;
        boolean result = false;
        try{
            con = DbUtil.connect();
            ps = con.prepareStatement(DELETE_STUDENT);
            ps.setInt(1,idStudent);
            ps.executeUpdate();
            result = true;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtil.closeAll(con,ps);
        }
        return result;
    }

    @Override
    public boolean addStudent(Student student) {
        Connection con = null;
        PreparedStatement ps = null;
        boolean result = false;

        try {
            con = DbUtil.connect();
            ps = con.prepareStatement(ADD_STUDENT);
            ps.setString(1,student.getFirstName());
            ps.setString(2,student.getLastName());
            ps.setString(3,student.getDateOfBirth().toString());
            ps.setInt(4,student.getTeacher().getId());
            ps.execute();
            result = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateStudent(Student student) {
        Connection con = null;
        PreparedStatement ps = null;
        boolean result = false;

        try {
            con = DbUtil.connect();
            ps = con.prepareStatement(UPDATE_STUDENT);
            ps.setString(1,student.getFirstName());
            ps.setString(2,student.getLastName());
            ps.setString(3,student.getDateOfBirth().toString());
            ps.setInt(4,student.getTeacher().getId());
            ps.setInt(5,student.getId());
            ps.executeUpdate();
            result = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Student> advSearchStudent(StudentSearch studentSearch) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Student> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder(ADV_SEARCH_STUDENT);
        List<Object> parametrsList =  null;
        try {

            if (!studentSearch.isAllFieldsNull()){
                sql.append(" where");
                boolean appendAnd = false;
                parametrsList = new ArrayList<>();
                if (studentSearch.getStudentName()!=null){
                    if (appendAnd){
                        sql.append(" and");
                    }
                    sql.append(" s.first_name=?");
                    parametrsList.add(studentSearch.getStudentName());
                    appendAnd = true;
                }
                if (studentSearch.getStudentSurname()!=null){
                    if (appendAnd){
                        sql.append(" and");
                    }
                    sql.append(" s.last_name=?");
                    parametrsList.add(studentSearch.getStudentSurname());
                    appendAnd = true;
                }
                if (studentSearch.getDobMin()!=null){
                    if (appendAnd){
                        sql.append(" and");
                    }
                    sql.append(" s.date_of_birth<=?");
                    parametrsList.add(studentSearch.getDobMin());
                    appendAnd = true;
                }
                if (studentSearch.getDobMax()!=null){
                    if (appendAnd){
                        sql.append(" and");
                    }
                    sql.append(" s.date_of_birth>=?");
                    parametrsList.add(studentSearch.getDobMax());
                    appendAnd = true;
                }
                if (studentSearch.getSelectCourse()!=null){
                    if (appendAnd){
                        sql.append(" and");
                    }
                    sql.append(" c.id_course=?");
                    parametrsList.add(studentSearch.getSelectCourse());
                    appendAnd = true;
                }
                if (studentSearch.getDescription()!=null){
                    if (appendAnd){
                        sql.append(" and");
                    }
                    studentSearch.setDescription("%"+studentSearch.getDescription()+"%");
                    sql.append(" c.description like ?");
                    parametrsList.add(studentSearch.getDescription());
                    appendAnd = true;
                }
                if (studentSearch.getSelectTeacher()!=null){
                    if (appendAnd){
                        sql.append(" and");
                    }
                    sql.append(" t.id_teacher=?");
                    parametrsList.add(studentSearch.getSelectTeacher());
                    appendAnd = true;
                }

            }
            con = DbUtil.connect();
            ps = con.prepareStatement(sql.toString());
            if (parametrsList!=null) {
                for (int i = 0; i < parametrsList.size(); i++) {
                    ps.setObject(i+1, parametrsList.get(i));
                }
            }
            System.out.println(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Student student = new Student();
                student.setId(rs.getInt("id_student"));
                student.setFirstName(rs.getString("first_name"));
                student.setLastName(rs.getString("last_name"));
                student.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                Teacher teacher = new Teacher();
                teacher.setId(rs.getInt("id_teacher"));
                teacher.setFirstName(rs.getString("t_first_name"));
                teacher.setLastName(rs.getString("t_last_name"));
                Course course = new Course();
                course.setId(rs.getInt("id_course"));
                course.setCourseName(rs.getString("course_name"));
                course.setDescription(rs.getString("description"));
                teacher.setCourse(course);
                student.setTeacher(teacher);
                list.add(student);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtil.closeAll(con,ps,rs);
        }
        return list;
    }
}
