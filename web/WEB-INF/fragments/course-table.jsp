<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.elman.course.model.Course" %><%--
  Created by IntelliJ IDEA.
  User: Elman
  Date: 07.05.2019
  Time: 16:49
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
        $('#idTableCourse').DataTable();
    });

</script>

<div align="center">
    <h1>Course data</h1>
</div>

<table id="idTableCourse" class="display cell-border " border="1px">
<thead>

<tr>
    <th>Id</th>
    <th>Name</th>
    <th>Description</th>
    <th>Delete</th>
    <th>Edit</th>

</tr>
</thead>


  <c:forEach var="course" items="${courseList}">
      <tbody>
      <tr>
          <td>${course.id}</td>
          <td>${course.courseName}</td>
          <td>${course.description}</td>
          <td><a href="#" onclick="deleteCourse(${course.id})">
              <i class="fas fa-trash-alt"></i>
          </a> </td>
          <td>
              <a href="#" onclick="openEditCourseDialog(${course.id})">
                  <i class="fas fa-edit"></i>
              </a>
          </td>

      </tr>
      </tbody>
  </c:forEach>




</table>


</body>
</html>
