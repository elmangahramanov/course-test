<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript">


        $('#idSelectCourse').val('${teacher.course.id}'); // <= avtomatik cixmir!


</script>

<input type="text" id="idInputTeacherId" value="${teacher.id}" hidden >

<p>Name:</p>
<input type="text" id="idInputTeacherName" value="${teacher.firstName}">
<p>Surname:</p>
<input type="text" id="idInputTeacherSurname" value="${teacher.lastName}">

<br><br>

<select  id="idSelectCourse">
    <c:forEach var="course" items="${courseList}">

        <option value="${course.id}">${course.courseName}</option>

    </c:forEach>

</select>
