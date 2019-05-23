<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<input type="text" id="idInputCourseId"  value="${course.id}" hidden >

<p>Name:</p>
<input type="text" id="idInputCourseName" value="${course.courseName}">
<p>Description:</p>
<input type="text" id="idInputCourseDesc" value="${course.description}">