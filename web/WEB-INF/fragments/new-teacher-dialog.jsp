<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<p>Name:</p>
<input type="text" id="idInputTeacherName">
<p>Surname:</p>
<input type="text" id="idInputTeacherSurname">

<br><br>

<select  id="idSelectTeacher">
    <c:forEach var="course" items="${courseList}">

        <option value="${course.id}">${course.courseName}</option>

    </c:forEach>

</select>
