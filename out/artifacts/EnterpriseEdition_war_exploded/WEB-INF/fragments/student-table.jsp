<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.elman.course.model.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<script>

    $(function () {
        $('#idTableStudent').DataTable();
    });

</script>
<div align="center">
    <h1>Student data</h1>
</div>

<a href="/ss?action=search">Advanced search</a>

<table id="idTableStudent" class="display cell-border " border="1px">

    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Date of birth</th>
        <th>Course</th>
        <th>Teacher</th>
        <th>Delete</th>
        <th>Edit</th>
    </tr>
    </thead>
    <c:forEach var="student" items="${studentList}">
        <tbody>
        <tr>
            <td>${student.id}</td>
            <td>${student.firstName}</td>
            <td>${student.lastName}</td>
            <td>${student.dateOfBirth}</td>
            <td>${student.teacher.course.courseName}</td>
            <td>${student.teacher.firstName} ${student.teacher.lastName}</td>
            <td>
                <a href="#" onclick="deleteStudent(${student.id})">

                    <i class="fas fa-trash-alt"></i>
               </a>
            </td>
            <td>
                <a href="#" onclick="openEditStudentDialog(${student.id})">
                    <i class="fas fa-edit"></i>
                </a>
            </td>

        </tr>
        </tbody>
    </c:forEach>


</table>

</body>
</html>
