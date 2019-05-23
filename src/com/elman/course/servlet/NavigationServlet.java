package com.elman.course.servlet;

import com.elman.course.dao.CourseDaoImpl;
import com.elman.course.dao.StudentDaoImpl;
import com.elman.course.dao.TeacherDaoImpl;
import com.elman.course.dao.UserDaoImpl;
import com.elman.course.model.Course;
import com.elman.course.model.Role;
import com.elman.course.model.Student;
import com.elman.course.model.Teacher;
import com.elman.course.service.*;
import com.elman.course.util.ValidationUtil;
import com.mysql.cj.xdevapi.JsonArray;
import org.json.JSONArray;
import org.json.JSONString;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "NavigationServlet", urlPatterns = "/ns")
public class NavigationServlet extends HttpServlet {
    StudentService studentService = new StudentServiceImpl(new StudentDaoImpl());
    TeacherService teacherService = new TeacherServiceImpl(new TeacherDaoImpl());
    CourseService courseService = new CourseServiceImpl(new CourseDaoImpl());
    UserService userService = new UserServiceImpl(new UserDaoImpl());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = null;

        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        }
        if(action.equals("getNewStudentJsp")){

            List<Course> list = courseService.getAllCourse();
            request.setAttribute("courseList",list);
            request.getRequestDispatcher("WEB-INF/fragments/new-student-dialog.jsp").forward(request,response);
        }
        if(action.equals("getNewTeacherJsp")){
            List<Course> list = courseService.getAllCourse();

            request.setAttribute("courseList",list);

            request.getRequestDispatcher("WEB-INF/fragments/new-teacher-dialog.jsp").forward(request,response);

        }
        if (action.equals("getNewCourseJsp")){
            request.getRequestDispatcher("WEB-INF/fragments/new-course-dialog.jsp").forward(request,response);
        }
        // action - edit
        if (action.equals("getEditStudentJsp")){
            int idStudent = Integer.parseInt(request.getParameter("id"));
           Student student = studentService.getStudentById(idStudent);

            request.setAttribute("student",student);

            List<Course> listCourse = courseService.getAllCourse();
            request.setAttribute("courseList",listCourse);

            List<Teacher> listTeacher = teacherService.getAllTeacher();
            request.setAttribute("teacherList",listTeacher);

            request.getRequestDispatcher("WEB-INF/fragments/edit-student-dialog.jsp").forward(request,response);
        }
        if (action.equals("getEditTeacherJsp")){
           int idTeacher = Integer.parseInt(request.getParameter("id"));
            Teacher teacher = teacherService.getTeacherById(idTeacher);
            request.setAttribute("teacher",teacher);

            List<Course> list = courseService.getAllCourse();
            request.setAttribute("courseList",list);
            request.getRequestDispatcher("WEB-INF/fragments/edit-teacher-dialog.jsp").forward(request,response);
        }
        if (action.equals("getEditCourseJsp")){
            int idCourse  = Integer.parseInt(request.getParameter("id"));
            Course course = courseService.getCourseById(idCourse);
            request.setAttribute("course",course);

            request.getRequestDispatcher("WEB-INF/fragments/edit-course-dialog.jsp").forward(request,response);
        }

        if (action.equals("getTeachersByIdCourse")){
            int idCourse = Integer.parseInt(request.getParameter("id"));
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");


            if(!ValidationUtil.Validate(String.valueOf(idCourse),firstName,lastName)){
                throw new ServletException();
            }

            List<Teacher> list = teacherService.getTeachersByIdCourse(idCourse);


            JSONArray jsonArray = new JSONArray(list);


            response.setContentType("application/json");


            response.getWriter().write(jsonArray.toString());


        }
        if(action.equals("search")){

            request.getRequestDispatcher("/WEB-INF/view/search.jsp").forward(request,response);
        }
        if (action.equals("login")){

            request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request,response);
        }
        if (action.equals("register")){
            List<Role> list = userService.getAllRoleType();
            request.setAttribute("roleList",list);
            request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request,response);
        }



    }
}
