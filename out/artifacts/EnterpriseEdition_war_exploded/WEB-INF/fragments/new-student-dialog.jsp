<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript">

    $('#idSelectCourse').change(function () {
        var idCourse = $(this).val();
        if (idCourse.trim().length==0){
            $('#idSelectTeacher').hide();
            return;
        }
        $.ajax({
            url:'/ns?action=getTeachersByIdCourse',
            type:'GET',
            data:'id='+idCourse,
            dataType:'json',
            success:function (data) {
                $('#idSelectTeacher').empty();
                $('#idSelectTeacher').append(new Option('Select teacher...',''));
                data.forEach(function (t) {
                    $('#idSelectTeacher').append(new Option(t.firstName+' '+t.lastName,t.id));
                });


            }

        });
        $('#idSelectTeacher').show();
    });

</script>


<span>Name:</span>
<br>
<input type="text" id="idInputStudentName">
<br><br>
<span>Surname:</span>
<br>
<input type="text" id="idInputStudentSurName">
<br><br>
<span>Date of birth:</span>
<br>
<input type="date" id="idInputStudentDob">
<br><br>
<select  id="idSelectCourse">

    <option value="">Select course...</option>

    <c:forEach var="course" items="${courseList}">
        <option value="${course.id}">${course.courseName}</option>
    </c:forEach>

</select>
<br><br>

<select id="idSelectTeacher">

</select>

