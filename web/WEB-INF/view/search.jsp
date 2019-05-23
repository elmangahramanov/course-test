<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student - Search</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/external/jquery.dataTables.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/external/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/external/jquery.dataTables.min.js"></script>
</head>
<body>

<script>

    $(function () {
        $('#idTableStudent').DataTable();

        $('#idStudentName').val('${param.studentName}');
        $('#idStudentSurname').val('${param.studentSurname}');
        $('#idDobMin').val('${param.dobMin}');
        $('#idDobMax').val('${param.dobMax}');
        $('#idSelectCourse').val('${param.selectCourse}');
        $('#idDescription').val('${param.description}');
        $('#idSelectTeacher').val('${param.selectTeacher}');
    });



</script>




<div class="search-page-content">
    <a href="${pageContext.request.contextPath}/">Home page</a>
<form action="/ss" method="GET">
<table>
<tbody>
<input type="hidden" name="action" value="search">
    <tr>
        <td>
            <span>Student name:</span>
            <br>
            <input type="text" name="studentName" id="idStudentName" >
        </td>
        <td>
            <span>Student surname:</span>
            <br>
            <input type="text" name="studentSurname" id="idStudentSurname">
        </td>
        <td>
            <span>Date of birth(min.):</span>
            <br>
            <input type="date" name="dobMin" id="idDobMin">
        </td>
        <td>
            <span>Date of birth(max.):</span>
            <br>
            <input type="date" name="dobMax" id="idDobMax">
        </td>
    <td>
        <td>
            <span>Course:</span>
            <br>
            <select name="selectCourse" id="idSelectCourse">
                <option value="">All course...</option>
            <c:forEach var="course" items="${courseList}">
                    <option value="${course.id}">${course.courseName}</option>
            </c:forEach>
            </select>

        </td>
        <td>
            <span>Description:</span>
            <br>
            <input type="text" name="description" id="idDescription">
        </td>
        <td>
            <span>Teacher:</span>
            <br>
            <select name="selectTeacher" id="idSelectTeacher" >
                <option value="">All teacher...</option>
            <c:forEach var="teacher" items="${teacherList}">
                    <option value="${teacher.id}">${teacher.firstName} ${teacher.lastName}</option>
            </c:forEach>
            </select>

        </td>
    </tr>
    </tbody>

</table>
    <br>
    <input type="submit" name="Submit" id="idSubmit" >

    <input type="reset" name="Reset">
</form>

<table id="idTableStudent" class="display cell-border " border="1px">

    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Date of birth</th>
        <th>Course name</th>
        <th>Course description</th>
        <th>Teacher</th>

    </tr>
    </thead>

    <c:forEach var="student" items="${resultList}">
        <tbody>
        <tr>
            <td>${student.id}</td>
            <td>${student.firstName}</td>
            <td>${student.lastName}</td>
            <td>${student.dateOfBirth}</td>
            <td>${student.teacher.course.courseName}</td>
            <td>${student.teacher.course.description}</td>
            <td>${student.teacher.firstName} ${student.teacher.lastName}</td>

        </tr>
        </tbody>
    </c:forEach>


</table>
</div>



</body>
</html>
