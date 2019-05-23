package com.elman.course.servlet;

import com.elman.course.exceptions.DuplicateEmailException;
import com.elman.course.util.MessageConstants;
import com.elman.course.util.UserContants;
import com.elman.course.dao.CourseDaoImpl;
import com.elman.course.dao.StudentDaoImpl;
import com.elman.course.dao.TeacherDaoImpl;
import com.elman.course.dao.UserDaoImpl;
import com.elman.course.model.*;
import com.elman.course.service.*;
import com.elman.course.util.ValidationUtil;
import sun.plugin2.message.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "UserServlet", urlPatterns = "/us")
public class UserServlet extends HttpServlet {
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

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = null;

        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        }
        if (action.equals("doRegister")){

           String email =  request.getParameter("email");
           String password = request.getParameter("pass");
           String rePass = request.getParameter("rePass");
           String idRole = request.getParameter("idRole");


           if (!ValidationUtil.Validate(email,password,rePass,idRole)){
               throw new ServletException();
           }


           User user = new User();
           user.setEmail(email);
           user.setPassword(password);
           Role role = new Role();
           role.setId(Integer.parseInt(idRole));
           user.setRole(role);

           UUID uuid = UUID.randomUUID();
           user.setToken(uuid.toString());

           user.setStatus(UserContants.USER_STATUS_INACTIVE);


            try {
                boolean result = userService.registerUser(user);
                if (result){
                    String s = "OK";
                    request.setAttribute("message1",s);
                    request.getRequestDispatcher("WEB-INF/view/login.jsp").forward(request,response);
                }
                else{
                    request.setAttribute("message2",MessageConstants.ERROR_INTERNAL);
                    request.getRequestDispatcher("WEB-INF/view/register.jsp").forward(request,response);
                }

            } catch (DuplicateEmailException e) {
                request.setAttribute("message3",MessageConstants.DUPLICATE_EMAIL_ERROR);
                request.getRequestDispatcher("WEB-INF/view/register.jsp").forward(request,response);

            }
            System.out.println(request.getAttribute("message1"));
            System.out.println(request.getAttribute("message3"));

        }



        }


    }


