package com.elman.course.servlet;

import com.elman.course.dao.TeacherDaoImpl;
import com.elman.course.model.Course;
import com.elman.course.model.Student;
import com.elman.course.model.Teacher;
import com.elman.course.service.TeacherService;
import com.elman.course.service.TeacherServiceImpl;
import com.elman.course.util.ValidationUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "TeacherServlet",urlPatterns = "/ts")
public class TeacherServlet extends HttpServlet {

    TeacherService teacherService = new TeacherServiceImpl(new TeacherDaoImpl());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = null;

        if (request.getParameter("action")!=null){
            action = request.getParameter("action");
        }

        if (action.equals("getAllTeacher")){

            List<Teacher> list = teacherService.getAllTeacher();

            request.setAttribute("teacherList",list);

            request.getRequestDispatcher("WEB-INF/fragments/teacher-table.jsp").forward(request,response);

        }else if(action.equals("deleteTeacher")){
            int id = Integer.parseInt(request.getParameter("id"));

            try {
                boolean result = teacherService.deleteTeacher(id);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else if (action.equals("addTeacher")){

            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            int idCourse = Integer.parseInt(request.getParameter("idCourse"));
            if (!ValidationUtil.Validate(firstName,lastName,String.valueOf(idCourse))){
                throw new ServletException();
            }
            Teacher teacher = new Teacher();
            teacher.setFirstName(firstName);
            teacher.setLastName(lastName);
            Course course = new Course();
            course.setId(idCourse);
            teacher.setCourse(course);


            boolean result = teacherService.addTeacher(teacher);
            if (!result){
                throw new ServletException();
            }

        }
        else if(action.equals("editTeacher")){
            int idTeacher = Integer.parseInt(request.getParameter("idSelectTeacher"));
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            int idCourse = Integer.parseInt(request.getParameter("idCourse"));

            if (!ValidationUtil.Validate(String.valueOf(idTeacher),firstName,lastName, String.valueOf(idCourse))){
                throw new ServletException();

            }
            Teacher teacher = new Teacher();
            teacher.setId(idTeacher);
            teacher.setFirstName(firstName);
            teacher.setLastName(lastName);
            Course course = new Course();
            course.setId(idCourse);
            teacher.setCourse(course);

            boolean result = teacherService.updateTeacher(teacher);
            if (!result){
                throw new ServletException();
            }

        }

    }
}
