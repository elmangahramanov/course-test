package com.elman.course.servlet;

import com.elman.course.dao.CourseDaoImpl;
import com.elman.course.model.Course;
import com.elman.course.service.CourseService;
import com.elman.course.service.CourseServiceImpl;
import com.elman.course.util.ValidationUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CourseServlet",urlPatterns = "/cs")
public class CourseServlet extends HttpServlet {

    CourseService courseService = new CourseServiceImpl(new CourseDaoImpl());

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
        if (action.equals("getAllCourse")){
            List<Course> list = courseService.getAllCourse();
            request.setAttribute("courseList",list);
            request.getRequestDispatcher("WEB-INF/fragments/course-table.jsp").forward(request,response);
        }else if(action.equals("deleteCourse")){
            int id = Integer.parseInt(request.getParameter("id"));

            try {
                boolean result = courseService.deleteCourse(id);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else if (action.equals("addCourse")){
            String courseName = request.getParameter("courseName");
            String description = request.getParameter("description");

            if (!ValidationUtil.Validate(courseName,description)){
                throw new ServletException();
            }
            Course course = new Course();
            course.setCourseName(courseName);
            course.setDescription(description);

            boolean result  = courseService.addCourse(course);
            if (!result) {
                throw new ServletException();
            }
        }else if(action.equals("editCourse")){
            int idCourse  = Integer.parseInt(request.getParameter("idCourse"));
            String courseName = request.getParameter("courseName");
            String description = request.getParameter("description");

            if (!ValidationUtil.Validate(String.valueOf(idCourse),courseName,description)){

                throw new ServletException();

            }
            Course course = new Course();
            course.setId(idCourse);
            course.setCourseName(courseName);
            course.setDescription(description);

            boolean result = courseService.updateCourse(course);
            if (!result){
                throw new ServletException();
            }

        }

    }

}
