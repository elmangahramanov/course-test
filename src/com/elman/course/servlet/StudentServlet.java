package com.elman.course.servlet;

import com.elman.course.dao.CourseDaoImpl;
import com.elman.course.dao.StudentDaoImpl;
import com.elman.course.dao.TeacherDaoImpl;
import com.elman.course.model.Course;
import com.elman.course.model.Student;
import com.elman.course.model.StudentSearch;
import com.elman.course.model.Teacher;
import com.elman.course.service.*;
import com.elman.course.util.ValidationUtil;
import org.apache.tomcat.jni.Local;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.sql.SQLDataException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "StudentServlet",urlPatterns = "/ss")
public class StudentServlet extends HttpServlet {

    StudentService studentService= new StudentServiceImpl(new StudentDaoImpl());
    TeacherService teacherService = new TeacherServiceImpl(new TeacherDaoImpl());
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

        if (action.equals("getAllStudent")){

            List<Student> list = studentService.getAllStudent();

            request.setAttribute("studentList",list);

            request.getRequestDispatcher("WEB-INF/fragments/student-table.jsp").forward(request,response);

        } else if(action.equals("deleteStudent")){
            int id = Integer.parseInt(request.getParameter("id"));
            String idStudent = String.valueOf(id);
            if (ValidationUtil.Validate(idStudent)){
                boolean result = studentService.deleteStudent(id);
            }
            else {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }
        else if(action.equals("addStudent")){
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String dob = request.getParameter("dob");
            int idTeacher = Integer.parseInt(request.getParameter("idTeacher"));
            if (!ValidationUtil.Validate(firstName,lastName,dob, String.valueOf(idTeacher))){
                throw new ServletException();
            }
            Student student = new Student();
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setDateOfBirth(LocalDate.parse(dob));
            Teacher teacher = new Teacher();
            teacher.setId(idTeacher);
            student.setTeacher(teacher);
            boolean result = studentService.addStudent(student);
            if (!result){
                throw new ServletException();
            }


;        }
        else if(action.equals("editStudent")){
            int idStudent = Integer.parseInt(request.getParameter("idStudent"));
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String dob = request.getParameter("dob");
            int idTeacher = Integer.parseInt(request.getParameter("idTeacher"));
            if (!ValidationUtil.Validate(String.valueOf(idStudent),firstName,lastName,dob, String.valueOf(idTeacher))){
                throw new ServletException();
            }
            Student student = new Student();
            student.setId(idStudent);
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setDateOfBirth(LocalDate.parse(dob));
            Teacher teacher = new Teacher();
            teacher.setId(idTeacher);
            student.setTeacher(teacher);

            boolean result = studentService.updateStudent(student);
            if (!result){
                throw new ServletException();
            }

        }
        else if(action.equals("search")){

            String studentName = request.getParameter("studentName");
            String studentSurname = request.getParameter("studentSurname");
            String dobMin = request.getParameter("dobMin");
            String dobMax = request.getParameter("dobMax");
            String selectCourse = request.getParameter("selectCourse");
            String description = request.getParameter("description");
            String selectTeacher = request.getParameter("selectTeacher");

            StudentSearch studentSearch = new StudentSearch();
            studentSearch.setStudentName(studentName);
            studentSearch.setStudentSurname(studentSurname);
            studentSearch.setDobMin(dobMin);
            studentSearch.setDobMax(dobMax);
            studentSearch.setSelectCourse(selectCourse);
            studentSearch.setDescription(description);
            studentSearch.setSelectTeacher(selectTeacher);

            List<Student> resultList = studentService.advSearchStudent(studentSearch);
            request.setAttribute("resultList",resultList);




            List<Course> courseList = courseService.getAllCourse();
            request.setAttribute("courseList",courseList);

            List<Teacher> teacherList = teacherService.getAllTeacher();
            request.setAttribute("teacherList",teacherList);

            request.getRequestDispatcher("WEB-INF/view/search.jsp").forward(request,response);

        }


    }
}
