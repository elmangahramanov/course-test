<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.elman.course.model.Teacher" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Elman
  Date: 07.05.2019
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<script>

    $(function () {
        $('#idTableTeacher').DataTable();
    });

</script>

<div align="center">
    <h1>Teacher data</h1>
</div>


<table id="idTableTeacher" class="display cell-border " border="1px">
<thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Course</th>
        <th>Delete</th>
        <th>Edit</th>
    </tr>
</thead>

    <c:forEach var="teacher" items="${teacherList}">

        <tbody>
        <tr>
            <td>${teacher.id}</td>
            <td>${teacher.firstName}</td>
            <td>${teacher.lastName}</td>
            <td>${teacher.course.courseName}</td>
            <td><a href="#" onclick="deleteTeacher(${teacher.id})">
                <i class="fas fa-trash-alt"></i>
            </a> </td>
            <td>
                <a href="#" onclick="openEditTeacherDialog(${teacher.id})">
                    <i class="fas fa-edit"></i>
                </a>
            </td>

        </tr>
        </tbody>
    </c:forEach>


</table>

</body>
</html>
